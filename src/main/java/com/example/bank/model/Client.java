/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(length = 60)
    String FirstName;

    @Column(length = 60)
    private String middleName;

    @Column(length = 60)
    String LastName;

    LocalDateTime dateOfBirth;

    String job;

    BigDecimal averageOfGainPerMonth;

    String email;

    /** Mobile phone number (in international format, without space/-/parens, e.g. "+33670268756"). */
    @Column(length = 30)
    String phoneNumber;

    //TODO create address class
    @Column(nullable = false)
    String address;

    @OneToOne
    Account account;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(FirstName, client.FirstName) && Objects.equals(middleName, client.middleName) && Objects.equals(LastName, client.LastName) && Objects.equals(dateOfBirth, client.dateOfBirth) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, FirstName, middleName, LastName, dateOfBirth, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", FirstName='" + FirstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", job='" + job + '\'' +
                ", averageOfGainPerMonth=" + averageOfGainPerMonth +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", account=" + account +
                '}';
    }
}
