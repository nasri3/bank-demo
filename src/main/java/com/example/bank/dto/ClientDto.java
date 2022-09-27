package com.example.bank.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ClientDto {

    @NotNull
    String FirstName;

    private String middleName;

    @NotNull
    String LastName;

    @Past
    LocalDateTime dateOfBirth;

    String job;

    BigDecimal averageOfGainPerMonth;

    @NotNull
    @Email
    String email;

    @NotNull
    String phoneNumber;

    AccountDto accountDto;
}
