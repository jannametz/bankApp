package com.telran.bank.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity// automatisch wird in DB geschrieben
@Table(name = "accounts")//Name von Tabelle

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 15, nullable = false, columnDefinition = "VARCHAR(255)")
    private String id;

    @Column(name = "eMail", length = 30, unique = true, nullable = false)
    private String eMail;

    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;

    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;

    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @Column(name = "creatDate", length = 50, nullable = false)
    private LocalDateTime creatDate;

    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "amount", length = 20, nullable = false)
    private BigDecimal amount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "accountTransactions", joinColumns = {@JoinColumn(name = "accountId")}, inverseJoinColumns = {@JoinColumn(name = "transactionId")})
    private Set<Transaction> transactions = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(eMail, account.eMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, eMail);

    }
}