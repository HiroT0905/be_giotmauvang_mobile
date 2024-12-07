package com.example.DACN.repository;

import com.example.DACN.model.User;
import com.example.DACN.model.UserContact;
import com.example.DACN.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<User, String>
{
    Optional<User> findUserByUsername(String cccd);
}
