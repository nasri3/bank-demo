/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.bank.dao;
import com.example.bank.model.Movement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findAllByAccountId(long accountId);
    
}
