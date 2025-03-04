package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

/*
 * The role of the interface is to persist user accounts to database
 * It extends JpaRepository and make all the functionality of 
 * JpaRepository to the service class
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    Optional<Account> findByUsername(String username);
}
