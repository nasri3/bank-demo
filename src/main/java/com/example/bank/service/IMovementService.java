package com.example.bank.service;

import com.example.bank.dto.MovementReadDto;
import com.example.bank.dto.MovementWriteDto;

import java.util.List;

public interface IMovementService {

    MovementReadDto depositMoney(long accountId, MovementWriteDto movementWriteDto);

    MovementReadDto withdrawMoney(long accountId, MovementWriteDto movementWriteDto);

    List<MovementReadDto> getHistory(long accountId);
}
