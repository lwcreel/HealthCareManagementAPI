package com.example.healthcaremanagement.Repositories;

import java.util.Optional;

import com.example.healthcaremanagement.Models.ERole;
import com.example.healthcaremanagement.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}