package com.example.DACN.dto.request;


import com.example.DACN.dto.viewModel.UserInfoDTO;
import com.example.DACN.model.UserInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationRequest {
    @Size(min = 12,max = 12, message = "username must be 13 characters")
    @NotNull(message = "username is not null ")
    private String username;
    @Size(min = 6, message = "INVALID_PASSWORD")
    private String password;

    private UserInfoDTO userInfo;
}
