package com.telran.bank.repository;

import com.telran.bank.entity.Account;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Transactional
    @Modifying
    List<Account> findAll();

    List<Account> findByLocalDateTime(LocalDateTime creatDate);

    List<Account> findByCity(String city);

    List<Account> findByCountryAndCity(String country, String city);

    Account findAccountById(String accountId);

    @Transactional
    @Modifying
    @NonNull
    void updateAccountById(String email, String firstName, String lastName, String country, String city, String id);
}
