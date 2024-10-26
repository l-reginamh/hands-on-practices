package com.simple.auth.banking.controller;

import com.simple.auth.banking.constants.MessageConstants;
import com.simple.auth.banking.constants.StatusFlags;
import com.simple.auth.banking.exception.AlreadyExistsException;
import com.simple.auth.banking.exception.DataNotFoundException;
import com.simple.auth.banking.model.dto.TransactionsDto;
import com.simple.auth.banking.model.entity.Transactions;
import com.simple.auth.banking.model.request.TransactionsRequest;
import com.simple.auth.banking.model.response.ApiResponse;
import com.simple.auth.banking.model.response.StatusResponse;
import com.simple.auth.banking.services.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
public class TransactionsController {
    private final TransactionsService transactionsService;

    @GetMapping("/{accountNo}/all")
    public ResponseEntity<ApiResponse> getAllTransactions(@PathVariable Long accountNo) {
        try {
            List<Transactions> transactions = transactionsService.getTransactionsByAccountNo(accountNo);
            List<TransactionsDto> transactionsDtos = transactions.stream().map(transactionsService::convertToDto).toList();
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), transactionsDtos));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.DATA_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @GetMapping("/{id}/transaction")
    public ResponseEntity<ApiResponse> getTransaction(@PathVariable Long id) {
        try {
            Transactions transactions = transactionsService.getTransactionById(id);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), transactionsService.convertToDto(transactions)));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(new StatusResponse(StatusFlags.DATA_NOT_FOUND, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createTransaction(@RequestBody TransactionsRequest transactionsRequest) {
        try {
            Transactions transactions = transactionsService.createTransaction(transactionsRequest);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), transactionsService.convertToDto(transactions)));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<ApiResponse> updateTransaction(@PathVariable Long id, @RequestBody TransactionsRequest transactionsRequest) {
        try {
            Transactions transactions = transactionsService.updateTransaction(id, transactionsRequest);
            return ResponseEntity.ok(new ApiResponse(new StatusResponse(StatusFlags.SUCCESS, MessageConstants.SUCCESS, MessageConstants.SUCCESS), transactionsService.convertToDto(transactions)));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(new ApiResponse(new StatusResponse(StatusFlags.BAD_REQUEST, MessageConstants.BAD_REQUEST, e.getMessage()), null));
        }
    }

}
