package com.example.bank.service;

import com.example.bank.Exception.DeniedOperationException;
import com.example.bank.dto.MovementWriteDto;
import com.example.bank.model.Account;
import com.example.bank.model.Client;
import com.example.bank.model.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
class MovementServiceTest {

    @Autowired
    private MovementService movementService;

    @Autowired
    private AccountService accountService;

    @MockBean
    private ClientService clientService;

    private Account account;

    @BeforeEach
    void setUp() {
        Client client = new Client();
        client.setId(1L);
        when(clientService.findClient(1L)).thenReturn(client);
        account = accountService.createAccountForClient(1L);
    }

    @Test
    @Rollback
    void depositMoney() {
        movementService.depositMoney(account.getId(), new MovementWriteDto(BigDecimal.valueOf(40)));
        Assertions.assertEquals(accountService.findAccount(account.getId()).getAmount().compareTo(BigDecimal.valueOf(40)), 0);
    }

    @Test
    @Rollback
    void withdrawMoneyWhenInsufficientBalance() {
        assertThrows(DeniedOperationException.class, () -> {
            movementService.withdrawMoney(account.getId(), new MovementWriteDto(BigDecimal.valueOf(50)));
        });


    }

    @Test
    @Rollback
    void withdrawMoneyWhenSufficientBalance() {

        movementService.depositMoney(account.getId(), new MovementWriteDto(BigDecimal.valueOf(40)));

        movementService.withdrawMoney(account.getId(), new MovementWriteDto(BigDecimal.valueOf(10)));

        Assertions.assertEquals(accountService.findAccount(account.getId()).getAmount().compareTo(BigDecimal.valueOf(30)), 0);
    }

    @Test
    void getHistory() {
        movementService.depositMoney(account.getId(), new MovementWriteDto(BigDecimal.valueOf(40)));
        movementService.depositMoney(account.getId(), new MovementWriteDto(BigDecimal.valueOf(60)));
        movementService.withdrawMoney(account.getId(), new MovementWriteDto(BigDecimal.valueOf(30)));

        var movements = movementService.getHistory(account.getId());

        assertEquals(movements.size(), 3);
        assertEquals(movements.stream().filter(m -> m.getType().equals(Movement.MovementType.DEPOSIT)).count(), 2);
    }
}
