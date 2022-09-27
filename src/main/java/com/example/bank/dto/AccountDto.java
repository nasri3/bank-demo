package com.example.bank.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@Schema(name = "Account DTO", description = "Account object")
public class AccountDto implements Serializable {

    private Long id;

    @Past
    private Instant creationDate;

    @PositiveOrZero
    private BigDecimal amount;
}
