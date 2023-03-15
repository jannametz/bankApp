package com.telran.bank.util;
import com.telran.bank.entity.Account;
import com.telran.bank.entity.Transaction;
import com.telran.bank.entity.enums.TransactionType;
import lombok.NonNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

@NonNull
public class EntityCreator {
    public static Account getAccount() {
        return Account.builder()
                .id("12345678")
                .creatDate(LocalDateTime.parse("2023-02-09 12:30"))
                .eMail("percy@hotmail.com")
                .city("Berlin")
                .country("Germany")
                .firstName("Max")
                .lastName("Bank")
                .amount(BigDecimal.valueOf(500.400))
                .build();
    }
    public static Transaction getTransaction() {
        return Transaction.builder()
                .id(Long.valueOf("123438594-eUtb-1d45-aDse-2309487"))
                .dateTime(LocalDateTime.from(Instant.parse("2023-03-19T90:13:09.00Z")))
                .type(TransactionType.ATM)
                .amount(new BigDecimal(100000))
                .accountFrom("12345678-1234-0000-ef45-2983714690")
                .accountTo("008934657-uz7b-33jk-67ae-01293847263")
                .build();
    }
}
