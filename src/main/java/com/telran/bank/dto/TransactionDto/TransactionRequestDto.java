package com.telran.bank.dto.TransactionDto;

import com.telran.bank.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class TransactionRequestDto {

        private String id;

        private TransactionType type;

        private BigDecimal amount;

        private String accountFrom;

        private String accountTo;
    }


