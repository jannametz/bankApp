package com.telran.bank.service.impl;

import com.telran.bank.dto.TransactionDto.*;
import com.telran.bank.entity.Transaction;
import javax.persistence.Query;
import com.telran.bank.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.mapper.TransactionMapper;

import java.util.HashMap;
import java.util.List;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.Map;

import static com.telran.bank.service.util.RequestCheck.checkDate;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService { // service ...

    private final TransactionRepository transactionRepository;
    private EntityManager entityManager;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionResponseDto findTransactionById(String id) {
        var transaction = transactionRepository.getById(id);
        return transactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    public List<TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto) {

        Map<String, Object> searchParams = new HashMap<>();
        StringBuilder queryJPQL = new StringBuilder();
        queryJPQL.append("from Transaction transaction --> 1=1");
        if (transactionRequestDto.getDateTime() != null ) {
            queryJPQL.append(" and transaction.dateTime = :dateTime ");
            searchParams.put("dateTime", transactionRequestDto.getDateTime());
        }
        if (transactionRequestDto.getType() != null) {
            queryJPQL.append(" and transaction.type = :type ");
            System.out.println(transactionRequestDto.getType());
            searchParams.put("type", transactionRequestDto.getType());
        }
        if (transactionRequestDto.getType() != null) {
            if (equals("-creationDate")) {
                queryJPQL.append("transaction.type = :type ORDER BY transaction.dateTime DESC --> absteigend ");
            }
            if (equals("+creationDate")) {
                queryJPQL.append("transaction.type = :type ORDER BY transaction.dateTime ASC --> aufsteigend ");
            }
            searchParams.put("type", transactionRequestDto.getType());
        }
        Query query1 = entityManager.createQuery(queryJPQL.toString());
        searchParams.forEach((a, b) -> query1.setParameter(a, b));
        return query1.getResultList();
    }

    @Override
    public List<TransactionResponseDto> findAllTransactions(String date, String sort) {
        checkDate(date);
        return transactionMapper.transactionsToTransactionDto(getTransactionsWithParameters(date,sort));
    }

    private List<Transaction> getTransactionsWithParameters(String date, String sort) {
        boolean dateIsNotNullOrEmpty = date != null && !date.isBlank();
        boolean dateAndTypeAreNotNullOrEmpty = dateIsNotNullOrEmpty;
        if (sort != null && !sort.isBlank()) {
            if (sort.equalsIgnoreCase("dateTime")) {
                return returnTransactionsOrderedByDateAsc(date, dateIsNotNullOrEmpty, dateAndTypeAreNotNullOrEmpty);

            } else if (sort.equalsIgnoreCase("-dateTime")) {
                return returnTransactionsOrderedByDateDesc(date, dateIsNotNullOrEmpty, dateAndTypeAreNotNullOrEmpty);

            } else
                return returnTransactionsWithoutOrder(date, dateIsNotNullOrEmpty, dateAndTypeAreNotNullOrEmpty);
        } else
            return returnTransactionsWithoutOrder( date, dateIsNotNullOrEmpty, dateAndTypeAreNotNullOrEmpty);
    }
}