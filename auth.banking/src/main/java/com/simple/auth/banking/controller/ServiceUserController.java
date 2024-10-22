package com.simple.auth.banking.controller;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.StatusFlags;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.exception.InvalidRequestException;
import com.simple.auth.banking.model.entity.ServiceUser;
import com.simple.auth.banking.model.request.ServiceUserRequest;
import com.simple.auth.banking.model.response.ApiResponse;
import com.simple.auth.banking.model.response.StatusResponse;
import com.simple.auth.banking.services.ServiceUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class ServiceUserController {
    private final ServiceUserService serviceUserService;

    @PostMapping("/update/{serviceUserId}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long serviceUserId, @RequestBody ServiceUserRequest serviceUserRequest) {
        try {
            ServiceUser serviceUser = serviceUserService.updateServiceUser(serviceUserId, serviceUserRequest);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), serviceUserService.convertToDto(serviceUser)));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.USER_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @PostMapping("/activate/{serviceUserId}")
    public ResponseEntity<ApiResponse> activateUser(@PathVariable Long serviceUserId) {
        try {
            ServiceUser serviceUser = serviceUserService.activateServiceUser(serviceUserId);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), serviceUserService.convertToDto(serviceUser)));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.USER_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (InvalidRequestException e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.USER_INVALID_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @PostMapping("/deactivate/{serviceUserId}")
    public ResponseEntity<ApiResponse> deactivateUser(@PathVariable Long serviceUserId) {
        try {
            ServiceUser serviceUser = serviceUserService.activateServiceUser(serviceUserId);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), serviceUserService.convertToDto(serviceUser)));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.USER_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (InvalidRequestException e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.USER_INVALID_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }
}
