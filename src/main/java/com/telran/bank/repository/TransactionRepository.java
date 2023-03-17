package com.telran.bank.repository;

import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
    List<Transaction> findAll();

    List<Transaction> findByType(TransactionType type);

    List<Transaction> findByTypeAndCreationDate(TransactionType type, LocalDateTime dateTime);

    List<Transaction> findById(List<String> transactionId);

}
