package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    public SocialMediaController(AccountService accountService){
        this.accountService = accountService;
    }

    public SocialMediaController(MessageService messageService){
        this.messageService = messageService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Account> postRegisterHandler(@RequestBody Account account) {
        
        Account registeredAccount = accountService.registerAccount(account);
        try{
            return ResponseEntity.ok(registeredAccount);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    
    
    }
    @PostMapping("/login")
    public ResponseEntity<Account> postLoginHandler(@RequestBody Account account) {
        Account validAccount = accountService.validAccount(account);

        if(validAccount != null){
            return ResponseEntity.ok(validAccount);
        }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        
    }
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        

            // Add the message using the service
            Message addedMessage = messageService.addMessage(message);

            if (addedMessage != null) {
                // Convert the added message to JSON string
                return ResponseEntity.ok(addedMessage);
            } else {
                // Return 400 Bad Request if message creation fails
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        
        
    }
    
   
}
