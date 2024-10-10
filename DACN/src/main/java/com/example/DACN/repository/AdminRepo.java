package com.example.DACN.repository;

import com.example.DACN.model.admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo  extends JpaRepository<admin, Long> {
}
