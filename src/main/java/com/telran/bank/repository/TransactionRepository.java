package com.telran.bank.repository;

import com.telran.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findAllByCreatDate(LocalDate creatDate);
    List<Transaction> findAllOrderedDesc();
    List<Transaction> findByCreationDateOrderByCreationDateDesc(LocalDate creationDate);
    List<Transaction> findByCreationDateOrderByCreationDateAsc(LocalDate dateTime);
    List<Transaction> findAll();
}
