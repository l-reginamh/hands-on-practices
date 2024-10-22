package com.simple.auth.banking.controller;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.StatusFlags;
import com.simple.auth.banking.constants.enums.UserStatus;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.model.dto.ServiceUserDto;
import com.simple.auth.banking.model.entity.ServiceUser;
import com.simple.auth.banking.model.request.LoginRequest;
import com.simple.auth.banking.model.request.ServiceUserRequest;
import com.simple.auth.banking.model.response.ApiResponse;
import com.simple.auth.banking.model.response.ApiTokenResponse;
import com.simple.auth.banking.model.response.StatusResponse;
import com.simple.auth.banking.services.AuthService;
import com.simple.auth.banking.services.ServiceUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final ServiceUserService serviceUserService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ApiTokenResponse> register(@Valid @RequestBody ServiceUserRequest serviceUserRequest, BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> errorsList = result.getAllErrors();
            HashMap<String, String> errorMap = new HashMap<>();
            for (int i=0 ; i<errorsList.size() ; i++) {
                FieldError error = (FieldError) errorsList.get(i);
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new ApiTokenResponse(new StatusResponse(StatusFlags.VALIDATION_FAILED, MessageConstants.BAD_REQUEST, MessageConstants.VALIDATION_FAILED), null, errorMap));
        }

        try {
            ServiceUser serviceUser = serviceUserService.createServiceUser(serviceUserRequest);
            ServiceUserDto serviceUserDto = serviceUserService.convertToDto(serviceUser);

            String jwtToken = authService.createJwtToken(serviceUser);

            return ResponseEntity.ok(new ApiTokenResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.USER_CREATED), jwtToken, serviceUserDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiTokenResponse(new StatusResponse(StatusFlags.USER_ALREADY_EXIST, MessageConstants.BAD_REQUEST, e.getMessage()), null, null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiTokenResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null, null));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ApiTokenResponse> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        if (result.hasErrors()) {
            List<ObjectError> errorsList = result.getAllErrors();
            HashMap<String, String> errorMap = new HashMap<>();
            for (int i=0 ; i<errorsList.size() ; i++) {
                FieldError error = (FieldError) errorsList.get(i);
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(new ApiTokenResponse(new StatusResponse(StatusFlags.VALIDATION_FAILED, MessageConstants.BAD_REQUEST, MessageConstants.VALIDATION_FAILED), null, errorMap));
        }

        try {
            ServiceUser serviceUser = serviceUserService.getServiceUser(loginRequest.getUsername());
            if (serviceUser != null) {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginRequest.getUsername(),
                                loginRequest.getPassword()
                        )
                );

                ServiceUserDto serviceUserDto = serviceUserService.convertToDto(serviceUser);
                String jwtToken = authService.createJwtToken(serviceUser);

                if (serviceUser.getUserStatus().equals(UserStatus.DEACTIVATED)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiTokenResponse(new StatusResponse(StatusFlags.USER_LOCKED, MessageConstants.BAD_REQUEST, MessageConstants.USER_LOCKED), null, null));
                } else if (serviceUser.getUserStatus().equals(UserStatus.CLOSED)) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiTokenResponse(new StatusResponse(StatusFlags.USER_CLOSED, MessageConstants.BAD_REQUEST, MessageConstants.USER_CLOSED), null, null));
                } else {
                    return ResponseEntity.ok(new ApiTokenResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.USER_LOGGED_IN), jwtToken, serviceUserDto));
                }
            }

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiTokenResponse(new StatusResponse(StatusFlags.USER_NOT_FOUND, MessageConstants.BAD_REQUEST, MessageConstants.INVALID_CREDENTIAL), null, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiTokenResponse(new StatusResponse(StatusFlags.USER_INVALID_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null, null));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse> getUserProfile(Authentication authentication) {
        ServiceUser serviceUser = serviceUserService.getServiceUser(authentication.getName());
        return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), serviceUserService.convertToDto(serviceUser)));
    }
}
