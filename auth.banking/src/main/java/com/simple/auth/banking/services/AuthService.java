package com.simple.auth.banking.services;

import com.simple.auth.banking.model.entity.ServiceUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    String createJwtToken(ServiceUser serviceUser);
}
