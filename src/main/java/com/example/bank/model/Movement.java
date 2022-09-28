/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne(optional = false)
    Account account;

    @Enumerated(EnumType.STRING)
    MovementType type;

    @Column(nullable = false)
    BigDecimal amount;

    Instant timestamp;


    public enum MovementType {
        DEPOSIT, WITHDREW
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movement movement = (Movement) o;
        return Objects.equals(id, movement.id) && Objects.equals(account, movement.account) && type == movement.type && Objects.equals(amount, movement.amount) && Objects.equals(timestamp, movement.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, type, amount, timestamp);
    }

    @Override
    public String toString() {
        return "Movement{" +
                "id=" + id +
                ", account=" + account +
                ", type=" + type +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
