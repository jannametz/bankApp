package com.telran.bank.dto.TransactionDto;

import com.telran.bank.entity.enums.TransactionType;
import lombok.*;

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
    private Long accountFrom;
    private Long accountTo;
    private double amount;

}
