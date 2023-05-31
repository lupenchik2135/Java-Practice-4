package com.example.fourthpractice.controllers;

import com.example.fourthpractice.messages.requests.AccountDeleteRequest;
import com.example.fourthpractice.messages.requests.TransactionDeleteRequest;
import com.example.fourthpractice.messages.requests.TransactionExecuteRequest;
import com.example.fourthpractice.messages.responses.AccountDeleteResponse;
import com.example.fourthpractice.messages.responses.TransactionDeleteResponse;
import com.example.fourthpractice.messages.responses.TransactionExecuteResponse;
import com.example.fourthpractice.messages.responses.UserRegisterResponse;
import com.example.fourthpractice.models.TransactionModel;
import com.example.fourthpractice.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    //http://localhost:8080/transaction/execute
    @PostMapping("/execute")
    public TransactionExecuteResponse register(@RequestBody TransactionExecuteRequest transactionExecuteRequest) {
        TransactionModel transactionModel = transactionService.execute(transactionExecuteRequest);
        return new TransactionExecuteResponse(
                transactionModel.getDealSize(),
                transactionModel.getGetOwnerTransactionEmail(),
                transactionModel.getGetTransactionId()
        );
    }
    //http://localhost:8080/transaction/delete
    @DeleteMapping("/delete")
    public TransactionDeleteResponse deleteAccount(@RequestBody TransactionDeleteRequest transactionDeleteRequest) {
        log.info(transactionDeleteRequest.toString());
        return new TransactionDeleteResponse(transactionService.delete(transactionDeleteRequest));
    }
}
