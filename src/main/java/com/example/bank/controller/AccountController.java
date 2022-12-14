/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.controller;

import com.example.bank.dto.AccountDto;
import com.example.bank.mapper.AccountMapper;
import com.example.bank.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Account API")
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    public AccountController(AccountService accountService, AccountMapper accountMapper) {
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }


    @GetMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Retrieve all accounts")
    List<AccountDto> findAll() {
        return accountMapper.mapToDtos(accountService.findAll());
    }

    @GetMapping(value = "/account/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Retrieve the account of te given client")
    AccountDto findClientAccount(@PathVariable @Parameter(name = "clientId", description = "client ID") long clientId) {
        return accountMapper.mapToDto(accountService.findClientAccount(clientId));
    }

}
