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

import static com.telran.bank.service.util.RequestCheck.checkDate;
import static java.time.LocalDate.parse;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

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

    private List<Account> returnAccountsWithoutOrder(String date,
                                                     List<String> cities,
                                                     boolean dateNotNullOrEmpty,
                                                     boolean cityNotNullOrEmpty,
                                                     boolean dateAndCityNotNullOrEmpty) {
        if (dateAndCityNotNullOrEmpty) {
            return accountRepository.findByCityInAndCreationDate(cities, parse(date));
        } else if (cityNotNullOrEmpty) {
            return accountRepository.findByCityIn(cities);
        } else if (dateNotNullOrEmpty) {
            return accountRepository.findByCreationDate(parse(date));
        } else
            return accountRepository.findAll();
    }

    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {
        return accountMapper.mapToAccountResponseDto(accountRepository.save(accountMapper.mapToAccountCreateEntity(accountRequestDto)));
    }


    private List<Account> returnAccountsOrderedByDateDesc(String date,
                                                          List<String> city,
                                                          boolean dateNotNullOrEmpty,
                                                          boolean cityNotNullOrEmpty,
                                                          boolean dateAndCityNotNullOrEmpty) {
        if (dateAndCityNotNullOrEmpty) {
            //return all accounts with given CITY and DATE ordered DESCENDING by DATE
            return accountRepository.findByCityInAndCreationDateOrderByCreationDateDesc(city, parse(date));
        } else if (cityNotNullOrEmpty) {
            //return all accounts with given CITY ordered DESCENDING by DATE
            return accountRepository.findByCityInOrderByCreationDateDesc(city);
        } else if (dateNotNullOrEmpty) {
            //return all accounts with given DATE ordered DESCENDING by DATE
            return accountRepository.findByCreationDateOrderByCreationDateDesc(parse(date));
            //return all accounts ordered DESCENDING by DATE
        } else return accountRepository.findAllOderByDesc();
    }

    @Override
    @Transactional
    public AccountResponseDto saveAccount(AccountRequestDto accountRequestDTO) {
        return accountMapper.mapToAccountResponseDto(accountRepository.save(accountMapper.mapToAccountCreateEntity(accountRequestDTO)));
    }

    @Override
    public List<AccountResponseDto> getAllAccounts(String creatDate, List<String> city, String sort) {
        checkDate(creatDate);
        return accountMapper.accountsToAccountResponseDto(getAccountsWithParameters(creatDate, city, sort));
    }

    @Override
    public AccountResponseDto getAccountById(String id) {
        log.info("Get account with id {}", id);
        return accountMapper.mapToAccountResponseDto(accountRepository.findAccountById(id));
    }

    private List<Account> getAccountsWithParameters(String creatDate, List<String> city, String sort) {
        boolean dateIsNotNullOrEmpty = creatDate != null && !creatDate.isBlank();
        boolean cityIsNotNullOrEmpty = city != null && !city.isEmpty();
        boolean dateAndCityAreNotNullOrEmpty = dateIsNotNullOrEmpty && cityIsNotNullOrEmpty;
        if (sort != null && !sort.isBlank()) {
            if (sort.equalsIgnoreCase("creationDate")) {
                return returnAccountsOrderByDateAsc(creatDate, city, dateIsNotNullOrEmpty, cityIsNotNullOrEmpty, dateAndCityAreNotNullOrEmpty);

            } else if (sort.equalsIgnoreCase("-creationDate")) {
                return returnAccountsOrderedByDateDesc(creatDate, city, dateIsNotNullOrEmpty, cityIsNotNullOrEmpty, dateAndCityAreNotNullOrEmpty);

            } else
                return returnAccountsWithoutOrder(creatDate, city, dateIsNotNullOrEmpty, cityIsNotNullOrEmpty, dateAndCityAreNotNullOrEmpty);

        } else
            return returnAccountsWithoutOrder(creatDate, city, dateIsNotNullOrEmpty, cityIsNotNullOrEmpty, dateAndCityAreNotNullOrEmpty);
    }

    private List<Account> returnAccountsOrderByDateAsc(String date,
                                                       List<String> city,
                                                       boolean dateIsNotNullOrEmpty,
                                                       boolean cityIsNotNullOrEmpty,
                                                       boolean dateAndCityAreNotNullOrEmpty) {
        if (dateAndCityAreNotNullOrEmpty) {
            return accountRepository.findByCityInAndCreationDateOrderByCreationDateAsc(city, parse(date));
        } else if (cityIsNotNullOrEmpty) {
            return accountRepository.findByCityInOrderByCreationDateAsc(city);
        } else if (dateIsNotNullOrEmpty) {
            return accountRepository.findByCreationDateOrderByCreationDateAsc(parse(date));
        } else return accountRepository.findAllOrderByAsc();
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
