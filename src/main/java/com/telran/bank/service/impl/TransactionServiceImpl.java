package com.telran.bank.service.impl;

import com.telran.bank.dto.TransactionDto.*;
import com.telran.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.telran.bank.repository.TransactionRepository;
import com.telran.bank.mapper.TransactionMapper;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public TransactionResponseDto findTransactionById(String id) {

        return null;
    }

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        return null;
    }

    @Override
    public List<TransactionResponseDto> findAllTransactions(String date, List<String> type, String sort) {
        return null;
    }
}