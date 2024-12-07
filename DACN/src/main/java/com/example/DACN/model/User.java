package com.example.DACN.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Id
    @Column(name = "CCCD", length = 12, unique = true)
    @NotBlank(message = "CCCD is required")
    @Size(min = 12, max = 12, message = "CCCD must be 12 characters")
    private String username;

    String password;

//    @OneToOne
//    private Role roles;

    @ManyToOne
    @JoinColumn(name = "role_id" , referencedColumnName = "id", nullable = false)
    private Role role ;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private UserInfo userInfo;

    @OneToOne (mappedBy =  "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private UserContact userContact;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }
    @Override
    public boolean isEnabled(){
        return true;
    }


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY )
//    private String userID;
//
//    @Column(name="CCCD", length = 12, unique = true)
//    @Size(min=12, max = 12, message = "CCCD must be 12 characters")
//    private String CCCD;
//
//    @Column(name = "password")
//    private String password;
//
//    @ManyToOne
//    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
//    private Role role;

}
