package com.example.bank.controller;

import com.example.bank.dto.MovementDto;
import com.example.bank.service.IMovementService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MovementController {

    private final IMovementService movementService;

    public MovementController(IMovementService movementService) {
        this.movementService = movementService;
    }

    @PostMapping(value = "/account/{accountId}/movement/deposit", produces = MediaType.APPLICATION_JSON_VALUE)
    MovementDto depositMoney(@PathVariable long accountId, @RequestBody @Valid MovementDto movementDto) {
        return movementService.depositMoney(accountId, movementDto);

    }

    @PostMapping(value = "/account/{accountId}/movement/withdraw", produces = MediaType.APPLICATION_JSON_VALUE)
    MovementDto withdrawMoney(@PathVariable long accountId, @RequestBody @Valid MovementDto movementDto) {
        return movementService.withdrawMoney(accountId, movementDto);

    }

    @GetMapping(value = "/account/{accountId}/movement", produces = MediaType.APPLICATION_JSON_VALUE)
    List<MovementDto> getHistory(@PathVariable long accountId) {
        return movementService.getHistory(accountId);
    }
}
