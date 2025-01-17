package com.example.controller;
import org.springframework.stereotype.Controller;

import java.util.List;

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
    
    @PostMapping("/register")
    public ResponseEntity<Object> postRegisterHandler(@RequestBody Account account) {
        
        Account registeredAccount = accountService.registerAccount(account);
        if (registeredAccount == null) {
            return ResponseEntity.status(409).build();
         }

         return ResponseEntity.ok(registeredAccount);
    
    
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
    public ResponseEntity<Message> createMessageHandler (@RequestBody Message message) {
        
        Message created  = messageService.createMessage(message);

        if (created == null) {
            
            return ResponseEntity.status(400).build();
        } 
            
            return ResponseEntity.ok(created);      
    }
    @GetMapping("/messages")
    public ResponseEntity <List<Message>> getAllMessage(){
        return ResponseEntity.ok(messageService.getAllMessages());
    }
    @GetMapping ("/messages/{messageId}")
    public ResponseEntity<Message>getMessageById(@PathVariable Integer messageId){
        return ResponseEntity.ok(messageService.getMessageById(messageId));
    } 
    @GetMapping ("accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable Integer accountId){
        return ResponseEntity.ok(messageService.getMessagesByUser(accountId));
    }  
   
    
    @DeleteMapping ("/messages/{messageId}")
    public ResponseEntity<Integer>deleteMessageHandler(@PathVariable int messageId){
        int deleted = messageService.deleteMessage(messageId);
        if( deleted == 0) {
            return ResponseEntity.ok().build();
        }
        
        return ResponseEntity.ok(deleted);

    }
    @PatchMapping ("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessageHandler(@PathVariable int messageId, @RequestBody Message messageText) {
            try {          
                Integer updatedMessage = messageService.updateMessage(messageId,messageText.getMessageText());
                return ResponseEntity.ok(updatedMessage);
            } catch(IllegalArgumentException e) {
                return ResponseEntity.status(400).body(null);
            } 

        }
    
    
   
}
