package com.example.bank.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class AccountDto {

    Long id;

    @Past
    Instant creationDate;

    @PositiveOrZero
    BigDecimal amount;
}
