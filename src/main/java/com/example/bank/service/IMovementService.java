package com.example.bank.service;

import com.example.bank.dto.MovementDto;

import java.util.List;

public interface IMovementService {

    MovementDto depositMoney(long accountId, MovementDto movementDto);

    MovementDto withdrawMoney(long accountId, MovementDto movementDto);

    List<MovementDto> getHistory(long accountId);
}
