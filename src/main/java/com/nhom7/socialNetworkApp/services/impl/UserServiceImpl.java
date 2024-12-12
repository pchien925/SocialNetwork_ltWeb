package com.nhom7.socialNetworkApp.services.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.nhom7.socialNetworkApp.dto.request.UpdateUserRequest;
import com.nhom7.socialNetworkApp.dto.response.UserResponse;
import com.nhom7.socialNetworkApp.entity.enumeration.RoleName;
import com.nhom7.socialNetworkApp.mapper.UserMapper;
import com.nhom7.socialNetworkApp.repository.RoleRepository;
import com.nhom7.socialNetworkApp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nhom7.socialNetworkApp.dto.response.ProfileResponse;
import com.nhom7.socialNetworkApp.entity.Role;
import com.nhom7.socialNetworkApp.entity.Status;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.model.UserModel;
import com.nhom7.socialNetworkApp.repository.UserRepository;
import com.nhom7.socialNetworkApp.util.EmailUtil;
import com.nhom7.socialNetworkApp.util.OtpUtil;

import io.micrometer.common.util.StringUtils;
import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private OtpUtil otpUtil = new OtpUtil();
    @Autowired
    private EmailUtil emailUtil;

    @Override
    public ProfileResponse getProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return userMapper.toProfileResponse(user);
    }

    @Override
    public String register(UserModel UserModel) {
        String otp = otpUtil.generateOtp();

        User user = new User();
        user.setFirstName(UserModel.getFirstName());
        user.setLastName(UserModel.getLastName());
        user.setGender(UserModel.getGender());
        user.setPhone(UserModel.getPhone());
        user.setEmail(UserModel.getEmail());
        user.setDateOfBirth(UserModel.getDateOfBirth());
        user.setPassword(passwordEncoder.encode(UserModel.getPassword()));
        user.setUsername(UserModel.getUsername());
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        //user.setOtpGeneratedTime(new Date());

        user.setIsActive(false);
        Role roleUser = roleRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singletonList(roleUser));
        Optional<User> opt1 = findByEmail(user.getEmail());
        Optional<User> opt2 = findByUsername(user.getUsername());
        if (!opt1.isEmpty() || !opt2.isEmpty()) {
            return "Username or Email already exists!";
        }
        userRepository.save(user);
        try {
            emailUtil.sendVerifyOtpEmail(UserModel.getEmail(), otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        return "User registration successful please veriy your account in your email";
    }

    @Override
    public String verifyAccount(String email, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setIsActive(true);
            userRepository.save(user);
            return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
    }

    @Override
    public String ChangePassword(String email, String password, String otp) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setPassword(password);
            userRepository.save(user);
            return "Password changed successfully. Please log in again.";
        }
        return "Please regenerate otp and try again";
    }

    @Override
    public String regenerateOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
        String otp = otpUtil.generateOtp();
        try {
            emailUtil.sendOtpEmail(email, otp);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send otp please try again");
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userRepository.save(user);
        return "Email sent... please verify account within 1 minute";
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        Optional<User> account = findByUsername(username);

        if (account.isPresent() && passwordEncoder.matches(password, account.get().getPassword())) {
            account.get().setPassword(password);
            return account.get();
        }
        return null;
    }

    @Override
    public User getAuthenticatedUser() {
        // Get the username from the SecurityContext
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Retrieve the user by username using the repository
        Optional<User> user = userRepository.findByUsername(username);

        // If user is found, return it, otherwise handle the case (e.g., throw an exception)
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public <S extends User> S save(S entity) {
        // Kiểm tra xem Account đã tồn tại hay chưa
        Optional<User> opt = findByEmail(entity.getEmail());

        if (opt.isPresent()) {
            // Nếu Account đã tồn tại
            if (StringUtils.isEmpty(entity.getAvatar())) {
                // Nếu hình ảnh mới rỗng, lấy hình ảnh cũ
                entity.setAvatar(opt.get().getAvatar());
            } else {
                // Nếu hình ảnh mới không rỗng, giữ nguyên hình ảnh mới
                entity.setAvatar(entity.getAvatar());
            }

            if (StringUtils.isEmpty(entity.getPassword())) {
                // Nếu mật khẩu mới rỗng, lấy mật khẩu cũ
                entity.setPassword(opt.get().getPassword());
            } else {
                // Nếu mật khẩu mới không rỗng, mã hóa mật khẩu mới
                entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            }
        }

        // Lưu Account vào cơ sở dữ liệu
        return userRepository.save(entity);
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String keyword, Pageable pageable) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(keyword, keyword,
                pageable);
    }

    @Override
    public List<User> findFriendsByUser(Long idUser) {
        return userRepository.findFriendsByUser(idUser);
    }

    @Override
    public List<User> findFollowersByUser(Long idUser) {
        return userRepository.findFollowersByUser(idUser);
    }

    @Override
    public List<User> findFollowingByUser(Long idUser) {
        return userRepository.findFollowingByUser(idUser);
    }


    @Override
    public UserResponse getMyProfile() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByUsername(username);
        return userMapper.toUserResponse(user.orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public UserResponse updateMyProfile(UpdateUserRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateMyProfile(user, request);
        userRepository.save(user);
        return userMapper.toUserResponse(user);
    }
}
