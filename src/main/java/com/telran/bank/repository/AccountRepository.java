package com.telran.bank.repository;

import com.telran.bank.entity.Account;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Transactional
    @Modifying
    List<Account> findByCityInOrderByCreationDateDesc(Collection<String> cities);
    List<Account> findByCityInAndCreationDateOrderByCreationDateDesc(Collection<String> cities, LocalDate creationDate);
    List<Account> findByCreationDateOrderByCreationDateDesc(LocalDate creationDate);
    List<Account> findAllOderByDesc();
    List<Account> findAllOrderByAsc();
    List<Account> findByCreationDate(LocalDate creationDate);
    List<Account> findByCityIn(Collection<String> cities);
    List<Account> findByCityInAndCreationDate(Collection<String> cities, LocalDate creationDate);
    Account findAccountById(String accountId);
    Account save(Account accountExist);
    List<Account> findByCreationDateOrderByCreationDateAsc(LocalDate creationDate);
    List<Account> findByCityInOrderByCreationDateAsc(Collection<String> cities);
    List<Account> findByCityInAndCreationDateOrderByCreationDateAsc(Collection<String> cities, LocalDate creationDate);
    @Transactional
    @Modifying
    void updateAccountById(String email, String firstName, String lastName, String country, String city,  @NonNull String id);

    List<Account> findAll();
}
