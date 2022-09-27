/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.service;

import com.example.bank.dao.AccountRepository;
import com.example.bank.model.Account;
import com.example.bank.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Service
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final ClientService clientService;

    public AccountService(AccountRepository accountRepository, ClientService clientService) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
    }

    public List<Account> findAll() {
        log.info("Searching for all accounts");
        return accountRepository.findAll();
    }

    public Account findClientAccount(long clientId) {
        log.info("Searching for client {} account", clientId);
        return accountRepository.findByClientId(clientId).orElseThrow();
    }

    public Account findAccount(long accountId) {
        log.info("Searching for account {}", accountId);
        return accountRepository.findById(accountId).orElseThrow();
    }

    public Account createAccountForClient(long clientId) {
        log.info("Create a new account  for client {}", clientId);
        Client client = clientService.findClient(clientId);
        Account account = new Account();
        account.setCreationDate(Instant.now());
        account.setAmount(BigDecimal.ZERO);
        account = accountRepository.save(account);
        client.setAccount(account);
        return account;

    }

    public Account saveAccount(Account account){
       return accountRepository.save(account);
    }

}

