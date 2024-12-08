package com.nhom7.socialNetworkApp.controller.api;

import com.nhom7.socialNetworkApp.dto.response.ApiResponse;
import com.nhom7.socialNetworkApp.dto.response.UserListRespone;
import com.nhom7.socialNetworkApp.mapper.UserListMapper;
import com.nhom7.socialNetworkApp.services.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mod")
@Slf4j
public class UserListRestController {

    private final IUserService userService;
    private final UserListMapper userListMapper;


    @GetMapping("/list-users")
    public ApiResponse<List<UserListRespone>> getUserList() {
        // Sử dụng mapper được inject để chuyển đổi User entity sang UserListResponse DTO
        List<UserListRespone> userList = userService.findAll().stream()
                .map(userListMapper::toUserListRespone)
                .collect(Collectors.toList());

        // Trả về API Response
        return ApiResponse.<List<UserListRespone>>builder()
                .status(HttpStatus.OK.value())
                .data(userList)
                .build();
    }


    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteById(id); // Sử dụng phương thức deleteById từ Service
            return ResponseEntity.ok("User deleted successfully.");
        } catch (EmptyResultDataAccessException e) {
            // Xử lý nếu ID không tồn tại
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        } catch (Exception e) {
            // Xử lý các lỗi khác
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }

}
