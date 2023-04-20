package com.telran.bank.service.interfaces;

import com.telran.bank.dto.TransactionDto.TransactionResponseDto;
import com.telran.bank.dto.TransactionDto.TransactionRequestDto;

import java.util.List;

public interface TransactionService {
    TransactionResponseDto findTransactionById(String id);

  List  <TransactionResponseDto> createTransaction(TransactionRequestDto transactionRequestDto);

   List<TransactionResponseDto> findAllTransactions(String date, String sort);
    List<TransactionResponseDto> findAllTransactions();
  // List<TransactionResponseDto> getAllTransactions(String date, String type, String sort);
}
