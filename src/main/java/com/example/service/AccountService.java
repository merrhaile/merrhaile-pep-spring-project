package com.example.service;

import com.example.entity.Account;
import com.example.exception.UserCredentialException;
import com.example.exception.UserDuplicateException;
import com.example.exception.UserLogInException;
import com.example.repository.AccountRepository;

import java.util.Optional;

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

    public Account userLogin(Account account) throws UserLogInException {
        Optional<Account> user = accountRepository.findByUsername(account.getUsername()); 
        if(user.isEmpty()) 
            throw new UserLogInException("No such user");
        else if(!user.get().getPassword().equals(account.getPassword()))
            throw new UserLogInException("Inccorect password");
        else return user.get();
    }
}
