package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;
    
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account validAccount(Account account) {
        /* Account a = accountRepository.findbyUsername(account.getUsername());
        if (a == null) {
           return null;
        }
        if(a.getPassword().equals(account.getPassword())) {
            return accountRepository.save(a);
        }
        else{
            return null;
        } */
       String username = account.getUsername();
       String password = account.getPassword();
       return accountRepository.findbyUsernameAndPassword(username,password);
        
    }

    public Account registerAccount(Account account) {
        if (account.getUsername().isBlank() || account.getPassword().length() < 4) {
            return null;
        }
        Account existingAccount = accountRepository.findbyUsername(account.getUsername());
        if (existingAccount != null) {
           return null;
        }

        return accountRepository.save(account);
    }

    
}


