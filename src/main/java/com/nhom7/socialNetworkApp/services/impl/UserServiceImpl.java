package com.nhom7.socialNetworkApp.services.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.nhom7.socialNetworkApp.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
	UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private OtpUtil otpUtil=new OtpUtil();
	@Autowired
	private EmailUtil emailUtil;

	  @Override
	public String register(UserModel UserModel) {
	    String otp = otpUtil.generateOtp();
	    try {
	      emailUtil.sendVerifyOtpEmail(UserModel.getEmail(), otp);
	    } catch (MessagingException e) {
	      throw new RuntimeException("Unable to send otp please try again");
	    }
	    User user = new User();
	    user.setFirstName(UserModel.getFirstName());
	    user.setLastName(UserModel.getLastName());
	    user.setGender(UserModel.getGender());
	    user.setPhone(UserModel.getPhone());
	    user.setEmail(UserModel.getEmail());
	    user.setDateOfBirth(UserModel.getDateOfBirth());
	    user.setPassword(UserModel.getPassword());
	    user.setUsername(UserModel.getUsername());
	    user.setOtp(otp);
	    user.setOtpGeneratedTime(LocalDateTime.now());
	    //user.setOtpGeneratedTime(new Date());
	    
	    user.setIsActive(false);
	    Role roleUser = new Role();
	    roleUser.setId(1);
	    user.getRoles().add(roleUser);

	    Status statusUser = new Status();
	    statusUser.setId(1L);  
	    user.setStatus(statusUser);
	    Optional<User> opt1 = findByEmail(user.getEmail());
	    Optional<User> opt2 = findByUsername(user.getUsername());
	    if(!opt1.isEmpty()||!opt2.isEmpty())
	    {
	    	return "Username or Email already exists!";
	    }
	    userRepository.save(user);
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
		public String ChangePassword(String email,String password, String otp) {
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
	
	
	
	
}
