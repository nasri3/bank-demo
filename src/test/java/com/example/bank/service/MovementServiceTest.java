package com.example.bank.service;

import com.example.bank.Exception.DeniedOperationException;
import com.example.bank.dto.MovementDto;
import com.example.bank.model.Account;
import com.example.bank.model.Client;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    @Rollback
    void depositMoney() {
        MovementDto movementDto = new MovementDto();
        movementDto.setAmount(BigDecimal.valueOf(40));

        movementService.depositMoney(account.getId(), movementDto);
        Assertions.assertEquals(accountService.findAccount(account.getId()).getAmount().compareTo(BigDecimal.valueOf(40)), 0);
    }

    @Test
    @Order(2)
    @Rollback
    void withdrawMoneyWhenInsufficientBalance() {
        MovementDto movementDto = new MovementDto();
        movementDto.setAmount(BigDecimal.valueOf(50));

        assertThrows(DeniedOperationException.class, () -> {
            movementService.withdrawMoney(account.getId(), movementDto);
        });


    }

    @Test
    @Order(3)
    void withdrawMoneyWhenSufficientBalance() {
        MovementDto depositMovement = new MovementDto();
        depositMovement.setAmount(BigDecimal.valueOf(40));

        movementService.depositMoney(account.getId(), depositMovement);

        MovementDto movementDto = new MovementDto();
        movementDto.setAmount(BigDecimal.valueOf(10));
        movementService.withdrawMoney(account.getId(), movementDto);

        Assertions.assertEquals(accountService.findAccount(account.getId()).getAmount().compareTo(BigDecimal.valueOf(30)), 0);
    }

}
