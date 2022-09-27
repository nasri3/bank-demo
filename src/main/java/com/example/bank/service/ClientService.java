/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.service;

import com.example.bank.dao.ClientRepository;
import com.example.bank.dto.ClientDto;
import com.example.bank.mapper.ClientMapper;
import com.example.bank.model.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientDto> findAll() {
        log.info("Searching for all clients");
        return clientMapper.mapToDtos(clientRepository.findAll());
    }

    public Client findClient(long clientId) {
        log.info("Searching for client {}", clientId);
        return clientRepository.findById(clientId).orElseThrow();
    }

    public ClientDto createClient(ClientDto clientDto) {
        log.info("Creating a new account");
        Client client = clientMapper.mapToEntity(clientDto);
        return clientMapper.mapToDto(clientRepository.save(client));
    }

    public ClientDto updateClient(long clientId, ClientDto clientDto) {
        log.info("Update client {}", clientId);
        Client client = findClient(clientId);
        clientMapper.patchEntity(clientDto, client);
        return clientMapper.mapToDto(clientRepository.save(client));
    }

    public void deleteClient(long clientId) {
        log.info("Delete client {}", clientId);
        clientRepository.deleteById(clientId);
    }

}
