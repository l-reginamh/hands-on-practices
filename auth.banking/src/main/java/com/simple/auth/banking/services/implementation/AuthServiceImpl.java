package com.simple.auth.banking.services.implementation;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.model.entity.ServiceUser;
import com.simple.auth.banking.repository.ServiceUserRepository;
import com.simple.auth.banking.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    @Value("${security.jwt.secret-key}")
    private String jwtSecretKey;

    @Value("${security.jwt.issuer}")
    private String jwtIssuer;

    @Value("${security.jwt.expiration-time}")
    private String jwtExpirationTime;

    @Value("${security.jwt.refresh-expiration-time}")
    private String jwtRefreshExpirationTime;

    private final ServiceUserRepository serviceUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ServiceUser user = serviceUserRepository.findByUsername(username).orElseThrow(() -> new DataNotFoundException(MessageConstants.USER_NOT_FOUND));

        if (user != null) {
            return User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole().toString())
                    .build();
        }

        return null;
    }

    @Override
    public String createJwtToken(ServiceUser serviceUser) {
        Instant now = Instant.now();

        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer(jwtIssuer)
                .issuedAt(now)
                .expiresAt(now.plusSeconds(Long.parseLong(jwtExpirationTime)))
                .subject(serviceUser.getUsername())
                .claim("role", serviceUser.getRole())
                .build();

        NimbusJwtEncoder encoder = new NimbusJwtEncoder(
                new ImmutableSecret<>(jwtSecretKey.getBytes())
        );

        JwtEncoderParameters params = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claimsSet);

        return encoder.encode(params).getTokenValue();
    }
}
