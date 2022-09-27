package com.example.bank.mapper;

import com.example.bank.model.Account;
import com.example.bank.model.Client;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ReferenceMapper {

    @PersistenceContext
    private final EntityManager entityManager;

    public ReferenceMapper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Account getAccountById(Long accountId) {
        return accountId != null ? entityManager.find(Account.class, accountId) : null;
    }

    public Client getClientById(Long clientId) {
        return clientId != null ? entityManager.find(Client.class, clientId) : null;
    }
}
