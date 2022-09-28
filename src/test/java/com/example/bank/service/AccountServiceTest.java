package com.example.bank.service;

import com.example.bank.model.Account;
import com.example.bank.model.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @MockBean
    private ClientService clientService;


    @BeforeEach
    void setUp() {
        Client client = new Client();
        client.setId(1L);
        when(clientService.findClient(1L)).thenReturn(client);
    }

    @Test
    void createAccountForClient() {
        Account account = accountService.createAccountForClient(1L);

        assertEquals(account.getAmount().compareTo(BigDecimal.ZERO), 0);

    }

}
