package com.telran.bank.repository;

import com.telran.bank.entity.Account;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Transactional
    @Modifying
    List<Account> findByLocalCreatDate(LocalDateTime creatDate);

    List<Account> findByCityIn(Collection<String> cities);

    List<Account> findAllOderByDesc();

    List<Account> findAllOrderByAsc();

    Account findAccountById(String accountId);

    Account save(Account accountExist);
    @Transactional
    @Modifying
    void updateAccountById(String email, String firstName, String lastName, String country, String city,  @NonNull String id);

}
