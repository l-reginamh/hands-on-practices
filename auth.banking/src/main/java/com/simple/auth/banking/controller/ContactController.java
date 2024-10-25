package com.simple.auth.banking.controller;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.StatusFlags;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.model.entity.Contact;
import com.simple.auth.banking.model.request.ContactRequest;
import com.simple.auth.banking.model.response.ApiResponse;
import com.simple.auth.banking.model.response.StatusResponse;
import com.simple.auth.banking.services.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    @GetMapping("/{accountNo}/contact")
    public ResponseEntity<ApiResponse> getContact(@PathVariable Long accountNo) {
        try {
            Contact contact = contactService.getContactByAccountNo(accountNo);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), contactService.convertToDto(contact)));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.DATA_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createContact(@RequestBody ContactRequest contactRequest) {
        try {
            Contact contact = contactService.createContact(contactRequest);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), contactService.convertToDto(contact)));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<ApiResponse> updateContact(@PathVariable Long id, @RequestBody ContactRequest contactRequest) {
        try {
            Contact contact = contactService.updateContact(id, contactRequest);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), contactService.convertToDto(contact)));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.DATA_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

}
