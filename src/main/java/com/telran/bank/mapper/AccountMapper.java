package com.telran.bank.mapper;

import com.telran.bank.dto.AccountDto.AccountRequestDto;
import com.telran.bank.dto.AccountDto.AccountResponseDto;
import org.mapstruct.Mapper;
import com.telran.bank.entity.Account;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;

@Mapper(componentModel = "spring",
        uses = {TransactionMapper.class},
        injectionStrategy = CONSTRUCTOR,
        imports = {BigDecimal.class, LocalDate.class})
public interface AccountMapper {
    AccountResponseDto mapToAccountResponseDto(Account account);
    @Mapping(target = "creatDate", expression = "java(LocalDate.now())")
    @Mapping(target = "amount", expression = "java(BigDecimal.ZERO)")
    Account mapToAccountCreateEntity(AccountRequestDto accountRequestDto);

    // List<AccountResponseDto> accountsToDto(List<Account> accounts);
}
