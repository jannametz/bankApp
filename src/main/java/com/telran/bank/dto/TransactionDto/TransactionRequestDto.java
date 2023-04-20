package com.telran.bank.dto.TransactionDto;

import com.telran.bank.entity.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
    private String id;
    private TransactionType type;
    private double amount;
    private Long accountFrom;
    private Long accountTo;
    private LocalDateTime dateTime;
}


