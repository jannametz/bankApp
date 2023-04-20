package com.telran.bank.controller;

import com.telran.bank.dto.AccountDto.AccountRequestDto;
import com.telran.bank.dto.TransactionDto.TransactionRequestDto;
import com.telran.bank.dto.TransactionDto.TransactionResponseDto;
import com.telran.bank.service.interfaces.AccountService;
import com.telran.bank.service.interfaces.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@Validated
@RestController
@RequiredArgsConstructor
@Tag(name = "controller managing transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @Operation(summary = "Returns a filtered list of transactions", description = "Filter is possible for date, type(transfer, withdraw, deposit). It's possible to sort by dateTime. If there is no params - return all transactions")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of transactions", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TransactionRequestDto.class)))
    })
    @GetMapping("/transactions")
    @ResponseStatus(OK)
    public TransactionResponseDto findAllTransactions(@RequestParam(value = "date", required = false) String date,
                                                     @RequestParam(value = "type", required = false) List<String> type,
                                                     @RequestParam(value = "sort", required = false) String sort) {
        //return transactionService.findAllTransactions(date, type, sort);
        return (TransactionResponseDto) transactionService.findAllTransactions();
    }

   //// @GetMapping("/transactions/{id}")
   // @ResponseStatus(OK)
    /*public TransactionResponseDto findAllTransactions(@PathVariable String id) {
        return transactionService.findTransactionById(id);
    }*/

    @Operation(summary = "Returns a filtered list of transactions", description = "Filter is possible for date, type(transfer, withdraw, deposit). It's possible to sort by dateTime. If there is no params - return all transactions")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of transactions", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TransactionRequestDto.class)))
    })

    @PostMapping("/transactions")
    @ResponseStatus(HttpStatus.CREATED)
    public List<TransactionResponseDto> create(@RequestBody TransactionRequestDto transactionRequestDto) {
        return transactionService.createTransaction(transactionRequestDto);
    }

    @Operation(summary = "Returns a filtered list of transactions", description = "Filter is possible for date, type(transfer, withdraw, deposit). It's possible to sort by dateTime. If there is no params - return all transactions")
    @ApiResponse(responseCode = "200", description = "Successfully returned list of transactions", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TransactionRequestDto.class)))
    })

    @ApiResponse(responseCode = "200", description = "Transaction is successful")
    public void makeTransaction(@RequestParam String accountFrom,
                                @RequestParam String accountTo,
                                @RequestParam double amount,
                                @RequestParam AccountRequestDto account,
                                @RequestParam Long id) {
        accountService.makeTransaction(accountFrom, accountTo, amount, account, id);
    }
}
