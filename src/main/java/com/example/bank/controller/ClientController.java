/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.controller;

import com.example.bank.dto.ClientDto;
import com.example.bank.service.ClientService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//TODO add security
@RestController
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(value = "/bank/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @PostMapping(value = "/bank/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientDto addClient(@RequestBody @Valid ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @DeleteMapping(value = "/bank/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable long id) {
        clientService.deleteClient(id);

    }


}
