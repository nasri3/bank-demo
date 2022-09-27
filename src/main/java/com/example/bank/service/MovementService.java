/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.service;

import com.example.bank.Exception.DeniedOperationException;
import com.example.bank.dao.MovementRepository;
import com.example.bank.dto.MovementReadDto;
import com.example.bank.dto.MovementWriteDto;
import com.example.bank.mapper.MovementMapper;
import com.example.bank.model.Account;
import com.example.bank.model.Movement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;


@Service
@Slf4j
public class MovementService implements IMovementService {

    private final MovementRepository movementRepository;
    private final AccountService accountService;
    private final MovementMapper movementMapper;

    public MovementService(MovementRepository movementRepository, AccountService accountService, MovementMapper movementMapper) {
        this.movementRepository = movementRepository;
        this.accountService = accountService;
        this.movementMapper = movementMapper;
    }

    /**
     * Deposit money in a client account
     *
     * @param accountId        account ID
     * @param movementWriteDto deposit money data
     * @return movement transaction
     */
    @Override
    @Transactional
    public MovementReadDto depositMoney(long accountId, MovementWriteDto movementWriteDto) {
        log.info("New deposit operation for the account {} with amount {}", accountId, movementWriteDto.getAmount());

        Account account = accountService.findAccount(accountId);
        Movement movement = movementMapper.mapToEntity(movementWriteDto);
        movement.setTimestamp(Instant.now());
        movement.setType(Movement.MovementType.DEPOSIT);

        account.addMovement(movement);
        account.setAmount(account.getAmount().add(movement.getAmount()));
        accountService.saveAccount(account);
        return movementMapper.mapToDto(movement);
    }

    /**
     * Withdrew money from client account
     *
     * @param accountId        account ID
     * @param movementWriteDto withdrew data
     * @return movement transaction
     */
    @Override
    @Transactional
    public MovementReadDto withdrawMoney(long accountId, MovementWriteDto movementWriteDto) {
        log.info("New withdraw operation for the account {} with amount {}", accountId, movementWriteDto.getAmount());

        Account account = accountService.findAccount(accountId);
        Movement movement = movementMapper.mapToEntity(movementWriteDto);

        movement.setTimestamp(Instant.now());
        movement.setType(Movement.MovementType.WITHDREW);

        if (account.getAmount().compareTo(movement.getAmount()) < 0) {
            throw new DeniedOperationException("Insufficient balance");

        }

        account.addMovement(movement);
        account.setAmount(account.getAmount().subtract(movement.getAmount()));
        accountService.saveAccount(account);
        return movementMapper.mapToDto(movement);
    }

    /**
     * Get account movements
     *
     * @param accountId account ID
     * @return movement list
     */
    @Override
    public List<MovementReadDto> getHistory(long accountId) {
        log.info("Searching for all account {} history ", accountId);
        return movementMapper.mapToDtos(movementRepository.findAllByAccountId(accountId));
    }
}
