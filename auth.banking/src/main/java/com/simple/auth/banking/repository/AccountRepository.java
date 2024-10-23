package com.simple.auth.banking.repository;

import com.simple.auth.banking.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNo(Long accountNo);
    List<Account> findByCustomerId(String customerId);
    boolean existsByAccountNo(Long accountNo);
    boolean existsByCustomerId(String customerId);
    boolean existsByAccountNoAndCustomerId(Long accountId, String customerId);
    Optional<Account> findFirstByOrderByCreatedDateDesc();
}
