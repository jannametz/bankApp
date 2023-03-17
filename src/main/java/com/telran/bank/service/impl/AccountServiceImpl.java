package com.telran.bank.service.impl;

import com.telran.bank.dto.AccountDto.AccountRequestDto;
import com.telran.bank.dto.AccountDto.AccountResponseDto;
import com.telran.bank.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.mapper.AccountMapper;

import com.telran.bank.service.AccountService;
import com.telran.bank.mapper.AccountMapper;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public  class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionServiceImpl transactionServiceImpl;


    @Override
    @Transactional
    public void delete(String id) {
        accountRepository.findAccountById(id);
    }

    @Override
    public Account update(String id, AccountRequestDto accountRequestDto) throws AccountNotFoundException {
        Account accountExist = accountRepository.findAccountById(id)
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + id + " isn't found"));
        accountExist.setEMail(accountRequestDto.getEMail());
        accountExist.setFirstName(accountRequestDto.getFirstName());
        accountExist.setLastName(accountRequestDto.getLastName());
        accountExist.setCountry(accountRequestDto.getCountry());
        accountExist.setCity(accountRequestDto.getCity());
        return accountRepository.save(accountExist);
    }


    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {
        return accountMapper.mapToAccountResponseDto(accountRepository.save(accountMapper.mapToAccountCreateEntity(accountRequestDto)));
    }

    @Override
    public AccountResponseDto getAccountById(String id) {
        return null;
    }

    @Override
    public List<AccountResponseDto> getAllAccounts(String date, String city) {
        return null;
    }

    @Override
    public void makeTransfer(String fromAccount, String toAccount, BigDecimal amount) {

    }

}
