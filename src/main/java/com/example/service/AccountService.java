package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    
    private final AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    
    public Account registerAccount(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        System.out.println(username + " " + password);
        // check fast conditions before hitting database
        if (username == "" || username == null || password.length() < 4) return null;

        if (accountRepository.existsByUsernameAndPassword(username, password) == true) return null;

        return accountRepository.save(account);
    }


    public Account validAccount(Account account) {
      
       String username = account.getUsername();
       String password = account.getPassword();
       return accountRepository.findByUsernameAndPassword(username,password);
        
    }


    
}


