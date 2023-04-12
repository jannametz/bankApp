package com.telran.bank.entity;

import com.telran.bank.entity.enums.TransactionType;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 15, nullable = false)
    private Long id;
    @Column(name = "date_time", length = 50, nullable = false)
    private LocalDateTime dateTime;
    @Column(name = "creat_date", length = 50, nullable = false)
    private LocalDateTime creatDate;
    @Column(name = "type", length = 50, nullable = false)
    @Convert(converter = TransactionType.class)
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "account_from", nullable = false)
    private String accountFrom;
    @Column(name = "account_to", nullable = false)
    private String accountTo;
    @Column(name = "amount", nullable = false)
    @NotNull(message = "Amount expected between -2000 and 5000. Actual stand: NULL!")
    @Max(value = 5000, message = "Amount expected between -2000 and 5000. Actual stand: >5000")
    @Min(value = -2000, message = "Amount expected between -2000 and 5000. Actual: <-2000")
    private double amount;

}
