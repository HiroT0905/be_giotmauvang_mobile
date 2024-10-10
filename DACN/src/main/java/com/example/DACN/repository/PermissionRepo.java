package com.example.DACN.repository;

import com.example.DACN.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepo  extends JpaRepository<Permission, String> {
}
