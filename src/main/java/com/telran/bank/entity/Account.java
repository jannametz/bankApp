package com.telran.bank.entity;

import lombok.*;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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

    @NotBlank(message = "Email isn't blank")
    @Column(name = "eMail", length = 30, unique = true, nullable = false)
    private String eMail;

    @Column(name = "creatDate", length = 50, nullable = false)
    private LocalDateTime creatDate;

    @NotBlank(message = "firstName isn't blank")
    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;
    @NotBlank(message = "lastName isn't blank")
    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;
    @NotBlank(message = "Country isn't blank")
    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @NotBlank(message = "City isn't blank")
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