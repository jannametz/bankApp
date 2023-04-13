package com.telran.bank.service.impl;

import com.telran.bank.dto.TransactionDto.*;
import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import com.telran.bank.repository.AccountRepository;
import com.telran.bank.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.mapper.TransactionMapper;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Override
    @Transactional
    public TransactionResponseDto findTransactionById(String id) {
        var transaction = transactionRepository.getById(id);
        return transactionMapper.mapToTransactionDto(transaction);
    }

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {

        return null;
    }

    public List<TransactionResponseDto> findAllTransactions(String date, List<String> type, String sort) {
        List<Transaction> transactions = transactionRepository.findAll.stream()
                .sorted(Comparator.comparing(Transaction::getId)).toList();
        return transactionMapper.transactionsToTransactionDto(transactions);
    }
}