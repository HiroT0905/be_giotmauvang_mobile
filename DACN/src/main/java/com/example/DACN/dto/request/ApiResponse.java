package com.example.DACN.dto.request;

import com.example.DACN.model.Role;
import com.example.DACN.model.User;
import com.example.DACN.model.UserInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    @Builder.Default
    private int code = 1000;
    private String error;
    private String message;

    private String token;
    String refreshToken;
    String expirationTime;


    String cccd;
    String password;


    String fullName;
    LocalDate dob;
    String sex;
    String phone;
    String email;
    String address;

    User user;
    UserInfo userInfo;
    Role role;
    List<User> userList;
}