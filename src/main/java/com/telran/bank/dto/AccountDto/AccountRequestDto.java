package com.telran.bank.dto.AccountDto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    private String id;
    @Email
    private String eMail;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private double amount;
}

