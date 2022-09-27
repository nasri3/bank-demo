package com.example.bank.dto;

import com.example.bank.model.Movement;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(name = "Movement read DTO", description = "Object returned on reading movement")
public class MovementReadDto extends MovementWriteDto {

    private Long id;

    private AccountDto account;

    private Movement.MovementType type;

    private Instant timestamp;
}
