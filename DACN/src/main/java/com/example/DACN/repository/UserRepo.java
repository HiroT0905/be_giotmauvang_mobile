package com.example.DACN.repository;

import com.example.DACN.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<user, Long> {
        user findByUsername(String CCCD);
}
