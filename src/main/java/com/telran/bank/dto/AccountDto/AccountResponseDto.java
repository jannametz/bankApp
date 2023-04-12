package com.telran.bank.dto.AccountDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//NON_NULL attribute to ignore fields with Null values and Include

public class AccountResponseDto {
    private String id;
    @Email
    private String eMail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdDate;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private double amount;
    @ManyToMany
    private List<Long> transactions;
}


