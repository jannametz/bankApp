package com.telran.bank.controller;
import com.telran.bank.dto.TransactionDto.TransactionRequestDto;
import com.telran.bank.dto.TransactionDto.TransactionResponseDto;
import com.telran.bank.service.AccountService;
import com.telran.bank.service.TransactionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "controller managing transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @GetMapping("/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponseDto> getAllTransactions(@RequestParam(value = "date", required = false) String date,
                                                           @RequestParam(value = "type", required = false) List<String> type, @RequestParam(value = "sort", required = false) String sort) {
        return transactionService.findAllTransactions(date, type, sort);
    }

    @GetMapping("/transactions/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponseDto findAllTransactions(@PathVariable String id) {
        return transactionService.findTransactionById(id);
    }

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionResponseDto create(@RequestBody TransactionRequestDto transactionRequestDto) {
        return transactionService.createTransaction(transactionRequestDto);
    }
    @ApiResponse(responseCode = "200", description = "Transaction is successful")
        public void makeTransaction(@RequestParam String accountFrom,
                @RequestParam String accountTo,
                @RequestParam BigDecimal amount) {
            accountService.makeTransfer(accountFrom, accountTo, amount);
    }

}
