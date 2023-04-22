package com.telran.bank.service.interfaces;

import com.telran.bank.dto.AccountDto.AccountRequestDto;
import com.telran.bank.dto.AccountDto.AccountResponseDto;
import com.telran.bank.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
public interface AccountService {
    //AccountResponseDto createAccount(AccountRequestDto accountRequestDto);

    @Transactional
    AccountResponseDto saveAccount(AccountRequestDto accountRequestDTO);

    List<AccountResponseDto> getAllAccounts(String creatDate, List<String> city, String sort);

    AccountResponseDto getAccountById(String id);

    Account update(String id, AccountRequestDto accountRequestDto) throws AccountNotFoundException;

    void makeTransaction(String fromId, String toId, double amount,AccountRequestDto account, Long id);
    void delete(String id);
    //Account findById(String id);


}


