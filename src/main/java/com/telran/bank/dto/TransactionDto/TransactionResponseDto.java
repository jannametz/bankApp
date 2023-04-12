package com.telran.bank.dto.TransactionDto;

import com.telran.bank.entity.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    private String id;
    private String dateTime;
    private String creatDate;
    private TransactionType type;
    private String accountFrom;
    private String accountTo;
    private double amount;

}
