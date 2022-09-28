/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.controller;

import com.example.bank.dto.ClientDto;
import com.example.bank.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import  org.springframework.web.bind.annotation.PatchMapping;
import  org.springframework.web.bind.annotation.RequestBody;
import  org.springframework.web.bind.annotation.DeleteMapping;



import javax.validation.Valid;
import java.util.List;

@RestController
@Tag(name = "Client API")
public class ClientController {


    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping(value = "/bank/client", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Retrieve all clients")
    public List<ClientDto> findAll() {
        return clientService.findAll();
    }

    @GetMapping(value = "/bank/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Retrieve an existing client")
    public void findClient(@PathVariable @Parameter(name = "clientId", description = "client ID") long clientId) {
        clientService.findClient(clientId);
    }

    @PostMapping(value = "/bank/client", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Create a new client")
    public ClientDto createClient(@RequestBody @Valid ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }

    @PatchMapping(value = "/bank/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Update an existing client")
    public ClientDto updateClient(@PathVariable @Parameter(name = "clientId", description = "client ID") long clientId, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(clientId, clientDto);
    }

    @DeleteMapping(value = "/bank/client/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Delete an existing client")
    public void deleteClient(@PathVariable @Parameter(name = "clientId", description = "client ID") long clientId) {
        clientService.deleteClient(clientId);

    }


}
