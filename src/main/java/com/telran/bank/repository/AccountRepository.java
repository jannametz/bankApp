package com.telran.bank.repository;

import com.telran.bank.entity.Account;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
    @Transactional
    List<Account> findAll();


    List<Account> findByLocalDateTime(LocalDateTime creatDate);

    List<Account> findByCity(String city);

    List<Account> findByCountryAndCity(String country, String city);

    Account findAccountById(String accountId);
    @Query("update Account a set  a.firstName = ?1, a.lastName = ?2, a.eMail = ?3, a.country = ?4, a.city = ?5 " +
            "where a.id = ?6")
    void updateAccountById(String email, String firstName, String lastName, String country, String city, @NonNull String id);

    List<Account> findAllByCityInIgnoreCaseAndCreationDate(String city, LocalDate parse);

}
