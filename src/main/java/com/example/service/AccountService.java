package com.example.service;

import com.example.entity.Account;
import com.example.exception.UserCredentialException;
import com.example.exception.UserDuplicateException;
import com.example.repository.AccountRepository;

import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account userRegistration(Account account) throws UserCredentialException, UserDuplicateException{
        
        if(account.getUsername().length() == 0 ) 
            throw new UserCredentialException("username should not be empty");
        if(account.getPassword().length() < 4) 
            throw new UserCredentialException("password must be >= 4 characters");
        if(accountRepository.findByUsername(account.getUsername()) != null) 
            throw new UserDuplicateException("User already exists in the database");
        return accountRepository.save(account);
    }

    public Account userLogin(Account account) throws UserCredentialException{
        Account user = accountRepository.findByUsername(account.getUsername());
        if(user == null) 
            throw new UserCredentialException("No such user");
        if(user.getPassword() != account.getPassword())
            throw new UserCredentialException("Inccorect password");
        return user;
    }
}
