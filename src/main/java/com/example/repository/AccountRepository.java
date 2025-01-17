package com.example.repository;
import com.example.entity.*;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository <Account, Integer> {
    
    @SuppressWarnings("unchecked")
    Account save(Account newAccount);

    boolean existsById(int id);

    boolean existsByUsernameAndPassword(String username, String password);

    Account findByUsernameAndPassword(String username, String password);

    List<Account> findAll();

    
  
}





