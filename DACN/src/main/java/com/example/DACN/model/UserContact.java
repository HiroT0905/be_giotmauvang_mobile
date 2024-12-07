package com.example.DACN.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class UserContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String email;
    private String address;

    @OneToOne
    @JoinColumn(name = "user_cccd",referencedColumnName = "CCCD")
    private  User user;
}
