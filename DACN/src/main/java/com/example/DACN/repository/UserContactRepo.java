package com.example.DACN.repository;

import com.example.DACN.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserContactRepo extends JpaRepository<UserContact, Long> {
}
