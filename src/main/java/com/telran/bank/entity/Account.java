package com.telran.bank.entity;

import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity// automatic write in DB
@Table(name = "accounts")//Name from table

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 20, nullable = false, columnDefinition = "VARCHAR(255)")
    private String id;

    @NotBlank(message = "Email isn't blank")
    @Column(name = "e_mail", length = 30, unique = true, nullable = false)
    private String eMail;

    @Column(name = "creat_date", length = 50, nullable = false)
    private LocalDateTime creatDate;

    @NotBlank(message = "first_name isn't blank")
    @Column(name = "firstName", length = 50, nullable = false)
    private String firstName;
    @NotBlank(message = "last_name isn't blank")
    @Column(name = "lastName", length = 50, nullable = false)
    private String lastName;
    @NotBlank(message = "Country isn't blank")
    @Column(name = "country", length = 50, nullable = false)
    private String country;

    @NotBlank(message = "City isn't blank")
    @Column(name = "city", length = 50, nullable = false)
    private String city;

    @Column(name = "amount_money", length = 20, nullable = false)
    private double amount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    @JoinTable(name = "accounts_transactions", joinColumns = {@JoinColumn(name = "account_id")}, inverseJoinColumns = {@JoinColumn(name = "transactionId")})
    private Set<Transaction> transactions = new LinkedHashSet<>();

    public void transactionAdd(Transaction transaction) {

        transactions.add(transaction);
    }

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