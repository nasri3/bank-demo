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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    String firstName;

    @Column(length = 60)
    private String middleName;

    @Column(length = 60)
    String lastName;

    @Column(nullable = false)
    LocalDate dateOfBirth;

    String job;

    BigDecimal averageOfGainPerMonth;

    @Column(nullable = false)
    String email;

    /** Mobile phone number (in international format, without space/-/parens, e.g. "+33670268756"). */
    @Column(length = 30)
    String phoneNumber;

    String address;

    @OneToOne
    Account account;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(firstName, client.firstName) && Objects.equals(middleName, client.middleName) && Objects.equals(lastName, client.lastName) && Objects.equals(dateOfBirth, client.dateOfBirth) && Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, middleName, lastName, dateOfBirth, email);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", FirstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", LastName='" + lastName + '\'' +
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
