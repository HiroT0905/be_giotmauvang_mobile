package com.example.DACN.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse <T> {
   @Builder.Default
    private int code =1000;
    private String message;
    private T data;



}
