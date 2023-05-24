package com.telran.bank.controller;

import com.telran.bank.dto.AccountDto.*;
import com.telran.bank.entity.Account;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.telran.bank.service.interfaces.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Tag(name = "Controller on managing accounts")
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {

        this.accountService = accountService;
    }

    @ResponseStatus(CREATED)
    @PostMapping("/accounts")
    public void createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        accountService.saveAccount(accountRequestDto);
    }

    @Operation(summary = "Returns a filtered list of accounts", description = "Filter is possible for date, city. It's possible to sort by creationDate. If there is no params - return all account")
    @ApiResponse(responseCode = "200", description = "Returned the list of accounts", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AccountRequestDto.class)))
    })
    @GetMapping("/accounts")
    @ResponseStatus(OK)
    public List<AccountResponseDto> getAllAccounts(@RequestParam(value = "date", required = false, defaultValue = "20") String date,
                                                   @RequestParam(value = "city", required = false, defaultValue = "20") List<String> city,
                                                   @RequestParam(value = "sort", required = false, defaultValue = "100") String sort) {
        return accountService.getAllAccounts(date, city, sort);
    }

    @Operation(summary = "Return account by id", description = "Getting an existing account by id")
    @ApiResponse(responseCode = "200", description = "Account returned", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountRequestDto.class))
    })

    @ResponseStatus(OK)
    @GetMapping("/accounts/{id}")
    public AccountResponseDto getAccountById(@PathVariable String id) {

        return accountService.getAccountById(id);
    }

    @PutMapping("/accounts/makeTransaction")
    @ResponseStatus(OK)
    public void makeTransaction(@RequestParam(value = "idFrom", required = false, defaultValue = "0") String fromAccount,
                                @RequestParam(value = "idTo", required = false, defaultValue = "20") String toAccount,
                                @RequestParam(value = "amount", required = false) double amount) {
        accountService.makeTransaction(fromAccount, toAccount, amount);
    }

    @DeleteMapping(value = "/{accountDelete}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@PathVariable("accountDelete") String accountId) {
        accountService.delete(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Operation(summary = "Update account", description = "The body content email, firstname, lastname, country and city to update an account. Parameter - account ID")
    @ApiResponse(responseCode = "201", description = "Account is created", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = AccountRequestDto.class))
    })

    @SneakyThrows
    @PatchMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account update(@PathVariable String id, @RequestParam AccountRequestDto accountRequestDto) {
        return accountService.update(id, accountRequestDto);

    }
}