package com.simple.auth.banking.repository;

import com.simple.auth.banking.model.entity.ServiceUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceUserRepository extends JpaRepository<ServiceUser, Long> {
    public ServiceUser findByUsername(String username);
    public ServiceUser findByCustomerId(String customerId);
    public boolean existsByUsername(String username);
    public boolean existsByCustomerId(String customerId);
}
