package com.telran.bank.repository;

import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findAllByCreatDate(LocalDate creatDate);


    List<Transaction> findAllByType(TransactionType type);

    List<Transaction> findAllByTypeAndCreatDate(TransactiocdnType type, LocalDate creatDate);

    List<Transaction> findAll();
}
