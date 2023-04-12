package com.telran.bank.service.impl;

import com.telran.bank.dto.AccountDto.AccountRequestDto;
import com.telran.bank.dto.AccountDto.AccountResponseDto;
import com.telran.bank.entity.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.mapper.AccountMapper;

import com.telran.bank.service.interfaces.AccountService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final TransactionServiceImpl transactionServiceImpl;

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


    @Override
    @Transactional
    public AccountResponseDto saveAccount(AccountRequestDto accountRequestDTO) {
        return accountMapper.mapToAccountResponseDto(accountRepository.save(accountMapper.mapToAccountCreateEntity(accountRequestDTO)));
    }

    @Override

    public AccountResponseDto getAccountById(String id) {
        log.info("Get account with id {}", id);
        return accountMapper.mapToAccountResponseDto(accountRepository.findAccountById(id));
        //Account account = accountRepository.findAccountById(id);
        //return accountMapper.mapToAccountResponseDto(account);
    }

    @Override
    public List<AccountResponseDto> getAllAccounts(String creatDate, String city, String sort) {
        log.info("Get all account");
        return null ;
    }

    @Override
    @Transactional
    public void makeTransaction(String idFrom, String idTo, double amount, AccountRequestDto account, Long id) {
        accountRepository.findAllOderByDesc();
    }

    @Override
    @Transactional
    public void delete(String id) {
        accountRepository.findAccountById(id);
    }
}
