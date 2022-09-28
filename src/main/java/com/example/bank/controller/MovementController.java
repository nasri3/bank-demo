package com.example.bank.controller;

import com.example.bank.dto.MovementReadDto;
import com.example.bank.dto.MovementWriteDto;
import com.example.bank.service.IMovementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Movement API")
public class MovementController {

    private final IMovementService movementService;

    public MovementController(IMovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping(value = "/account/{accountId}/movement/deposit", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Realize deposit transaction")
    MovementReadDto depositMoney(@PathVariable @Parameter(name = "accountId", description = "account ID") long accountId, @RequestBody @Valid MovementWriteDto movementWriteDto) {
        return movementService.depositMoney(accountId, movementWriteDto);

    }

    @PostMapping(value = "/account/{accountId}/movement/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Realize withdrawal transaction")
    MovementReadDto withdrawMoney(@PathVariable @Parameter(name = "accountId", description = "account ID") long accountId, @RequestBody @Valid MovementWriteDto movementWriteDto) {
        return movementService.withdrawMoney(accountId, movementWriteDto);

    }

    @GetMapping(value = "/account/{accountId}/movement", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Retrieve all client transactions")
    List<MovementReadDto> getHistory(@PathVariable @Parameter(name = "accountId", description = "account ID") long accountId) {
        return movementService.getHistory(accountId);
    }
}
