package com.simple.auth.banking.repository;

import com.simple.auth.banking.model.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByAccountNo(Long accountNo);
    Optional<Contact> findByCustomerId(String customerId);
    String findEmailByAccountNo(Long accountNo);
    String findEmailByCustomerId(String customerId);
    boolean existsByAccountNoAndCustomerId(Long accountNo, String customerId);
}
