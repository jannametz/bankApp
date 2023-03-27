package com.telran.bank.service;

import com.telran.bank.dto.AccountDto.AccountRequestDto;
import com.telran.bank.dto.AccountDto.AccountResponseDto;
import com.telran.bank.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
public interface AccountService {
    AccountResponseDto createAccount(AccountRequestDto accountRequestDto);

    @Transactional
    AccountResponseDto saveAccount(AccountRequestDto accountRequestDTO);

    AccountResponseDto getAccountById(String id);

    List<AccountResponseDto> getAllAccounts(String date, String city, String sort);

    void makeTransfer(String fromAccount, String toAccount, BigDecimal amount);

    void delete(String id); //Service to disable an active account in the system.

    Account update(String id, AccountRequestDto accountRequestDto) throws AccountNotFoundException;

}


