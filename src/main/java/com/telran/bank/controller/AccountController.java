package com.telran.bank.controller;

import com.telran.bank.dto.AccountDto.*;
import com.telran.bank.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.telran.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")

public class AccountController {
    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseStatus(CREATED)
    @PostMapping("/accounts")
    public void createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        accountService.createAccount(accountRequestDto);
    }

    @GetMapping("/accounts")
    @ResponseStatus(OK)
    public List<AccountResponseDto> getAllAccounts(@RequestParam(value = "date", required = false, defaultValue = "20") String date,
                                                   @RequestParam(value = "city", required = false, defaultValue = "20") String city) {
        return accountService.getAllAccounts(date, city);
    }

    @ResponseStatus(OK)
    @GetMapping("/accounts/{id}")
    public AccountResponseDto showAccountById(@PathVariable String id) {
        return accountService.getAccountById(id);
    }

    @PutMapping("/accounts/transferGeld")
    @ResponseStatus(OK)
    public void transferGeld(@RequestParam(value = "idFrom", required = false, defaultValue = "0") String fromAccount,
                             @RequestParam(value = "idTo", required = false, defaultValue = "20") String toAccount,
                             @RequestParam(value = "amount", required = false) BigDecimal amount) {
        accountService.makeTransfer(fromAccount, toAccount, amount);
    }

    @DeleteMapping(value = "/{accountDelete}")
    public ResponseEntity<?> delete(@PathVariable("accountDelete") String accountId) {
        accountService.delete(accountId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PatchMapping("/accounts/{id}")
    @ResponseStatus(OK)
    public Account update(@PathVariable String id, AccountRequestDto accountRequestDto) {
        try {
            accountService.update(id, accountRequestDto);
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
        return update(id, accountRequestDto);
    }
}