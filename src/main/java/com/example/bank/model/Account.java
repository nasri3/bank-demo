/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    Instant creationDate;

    BigDecimal amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    List<Movement> movements = new ArrayList<>();


    @Transient
    public void addMovement(Movement movement) {
        movement.setAccount(this);
        movements.add(movement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(creationDate, account.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationDate);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", amount=" + amount +
                '}';
    }
}
