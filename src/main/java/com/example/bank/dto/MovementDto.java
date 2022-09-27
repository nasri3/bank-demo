package com.example.bank.dto;

import com.example.bank.model.Movement;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class MovementDto {

    Long id;

    @NotNull
    AccountDto account;

    Movement.MovementType type;

    @PositiveOrZero
    BigDecimal amount;

    @Past
    Instant timestamp;
}
