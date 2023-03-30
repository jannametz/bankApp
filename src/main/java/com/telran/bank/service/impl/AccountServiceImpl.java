package com.telran.bank.service.impl;

import com.telran.bank.dto.AccountDto.AccountRequestDto;
import com.telran.bank.dto.AccountDto.AccountResponseDto;
import com.telran.bank.entity.Account;
import com.telran.bank.entity.enums.TransactionType;
import com.telran.bank.exception.TransactionNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.mapper.AccountMapper;

import com.telran.bank.service.AccountService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static com.telran.bank.entity.enums.TransactionType.*;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionServiceImpl transactionServiceImpl;



    @Override
    @Transactional
    public void delete(String id) {
        accountRepository.findAccountById(id);
    }

    @Override
    public Account update(String id, AccountRequestDto accountRequestDto) {
        Account accountExist = accountRepository.findAccountById(id);
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

    public TransactionType checkingTransactionType(Long fromId, Long toId) {
        TransactionType transactionType = ATM;
        if (fromId == null && toId == null) throw new TransactionNotFoundException();
        if (fromId == null) {
            transactionType = POS;
        }
        if (fromId == null) {
            transactionType = ONLINE;
        }

        if (fromId == null) {
            transactionType = DEPOSIT;
        }

        if (toId == null) {
            transactionType = WITHDRAW;
        }
        return transactionType;
    }

    @Override
    @Transactional
    public AccountResponseDto saveAccount(AccountRequestDto accountRequestDTO) {
        return accountMapper.mapToAccountResponseDto(accountRepository.save(accountMapper.mapToAccountCreateEntity(accountRequestDTO)));
    }

    @Override
    public AccountResponseDto getAccountById(String id) {
        Account account = accountRepository.findAccountById(id);
        return accountMapper.mapToAccountResponseDto(account);
    }

    @Override
    public List<AccountResponseDto> getAllAccounts(String date, String city, String sort) {
        return null;
    }

    @Override
    public void makeTransfer(String fromAccount, String toAccount, BigDecimal amount) {

    }
}
