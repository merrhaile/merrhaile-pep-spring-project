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


    /*
     * the handler uses the accountRepository object to save a user details in the database
     * It throws two exceptions if the user credentials does not meet certain constraints
     * if successfully executed, the account is saved and the method returns the
     * newly created account
     */

    public Account userRegistration(Account account) throws UserCredentialException, UserDuplicateException{
        
        if(account.getUsername().length() == 0 ) 
            throw new UserCredentialException("username should not be empty");
        else if(account.getPassword().length() < 4) 
            throw new UserCredentialException("password must be >= 4 characters");
        else if(accountRepository.findByUsername(account.getUsername()).isPresent()) 
            throw new UserDuplicateException("User already exists in the database");
        else return accountRepository.save(account);
    }

    /*
     * The userLogin method checks if an account is in the database
     * if present the account is authorized and the method returns the account
     * If not, it throws UserLogInException
     */
    
    public Account userLogin(Account account) throws UserLogInException {
        Optional<Account> user = accountRepository.findByUsername(account.getUsername()); 
        if(user.isEmpty()) 
            throw new UserLogInException("No such user");
        else if(!user.get().getPassword().equals(account.getPassword()))
            throw new UserLogInException("Inccorect password");
        else return user.get();
    }
}
