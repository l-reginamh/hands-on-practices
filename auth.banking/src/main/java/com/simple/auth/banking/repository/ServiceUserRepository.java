package com.simple.auth.banking.repository;

import com.simple.auth.banking.model.entity.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceUserRepository extends JpaRepository<ServiceUser, Long> {
    Optional<ServiceUser> findByUsername(String username);
    Optional<ServiceUser> findByCustomerId(String customerId);
    boolean existsByUsername(String username);
    boolean existsByCustomerId(String customerId);
}
