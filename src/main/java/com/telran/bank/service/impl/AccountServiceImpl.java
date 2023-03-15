package com.telran.bank.service.impl;

import com.telran.bank.dto.AccountDto.AccountRequestDto;
import com.telran.bank.dto.AccountDto.AccountResponseDto;
import com.telran.bank.entity.Account;
import com.telran.bank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.mapper.AccountMapper;

import com.telran.bank.service.AccountService;
import com.telran.bank.mapper.AccountMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private TransactionRepository transactionRepository;



    @Override
    public void delete(String id) {
        accountRepository.findAccountById(id);
    }
}
