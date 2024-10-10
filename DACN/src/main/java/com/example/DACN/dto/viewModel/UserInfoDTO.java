package com.example.DACN.dto.viewModel;


import com.example.DACN.model.user;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDTO {

    private String fullName;
    private LocalDate dob;
    private String sex;
    private String phone;
    private String address;
    private String email;

    private user user;
}
