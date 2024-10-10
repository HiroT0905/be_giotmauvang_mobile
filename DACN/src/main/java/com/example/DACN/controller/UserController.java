package com.example.DACN.controller;


import com.example.DACN.dto.request.ApiResponse;
import com.example.DACN.dto.request.UserCreationRequest;
import com.example.DACN.dto.request.UserUpdateRequest;
import com.example.DACN.dto.response.UserResponse;
import com.example.DACN.exception.UserNotFoundException;
import com.example.DACN.model.user;
import com.example.DACN.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j

@RequestMapping("/users/")
public class UserController {
    @Autowired
    private UserService userService;
//    @PostMapping
//    ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
//        return ApiResponse.<UserResponse>builder().data(userService.createUser(request))
//                .build();
//    }
    @PostMapping
   public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
    return ApiResponse.<UserResponse>builder()
            .data(userService.createUser(request))
            .build();
}
}
//public ApiResponse<UserResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
//    ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
//           .code(201)
//           .message("User created successfully")
//           .data(createUser)
//           .build();
//}
//}
//    @GetMapping("")
//    ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
//        List<UserResponse> users = userService.getAllUsers();
//        ApiResponse<List<UserResponse>> response = ApiResponse.<List<UserResponse>>builder()
//                .data(users)
//                .message("Successfully retrieved all users")
//                .code(200)
//                .build();
//        return ResponseEntity.ok(response);
//    }
//
//
//    @GetMapping("/{id}")
//    UserResponse getUserById(@PathVariable long id) {
//       return userService.getUserById(id);
//    }
//
////    @PutMapping("/{id}")
////    ApiResponse<UserResponse> updateUser(@PathVariable long id, @RequestBody UserUpdateRequest request) {
////        return ApiResponse.<UserResponse>builder().data(userService.updateUser(id, request)).build();
////    }
//@PutMapping("/{id}")
//public ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable long id, @RequestBody UserUpdateRequest request) {
//    try {
//        UserResponse updatedUser = userService.updateUser(id, request);
//        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
//                .data(updatedUser)
//                .message("Successfully updated user")
//                .code(200)
//                .build();
//        return ResponseEntity.ok(response);
//    } catch (UserNotFoundException e) {
//        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
//                .message(e.getMessage())
//                .code(404)
//                .build();
//        return ResponseEntity.status(404).body(response);
//    } catch (Exception e) {
//        ApiResponse<UserResponse> response = ApiResponse.<UserResponse>builder()
//                .message("An error occurred while updating the user")
//                .code(500)
//                .build();
//        return ResponseEntity.status(500).body(response);
//    }
//}

//    @DeleteMapping("/{id}")
//    String deleteUser(@PathVariable long id) {
//        userService.DeleteUser(id);
//        return "User has been deleted";
//    }


