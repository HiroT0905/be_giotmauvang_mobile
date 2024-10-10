package com.example.DACN.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor@NoArgsConstructor
public enum ErrorCode {
    UNCATEGORIZE(999, "Uncategorized"),
    USER_EXISTED(1002, "User already existed"),
    USER_NOTFOUND(1003, "User not found"),
    ;

    private int code;
    private String message;

}
