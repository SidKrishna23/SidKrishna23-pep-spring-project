package com.example.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
   
     public Message createMessage(Message message){
        if(message.getMessageText() == null || message.getMessageText().length() > 255 || message.getMessageText().isBlank()){
            return null;
        } 
        int postedBy = message.getPostedBy();
        if(accountRepository.existsById(postedBy) == false){
            return null;
        }
        return messageRepository.save(message);
    } 
 

    public Message getMessageById(Integer messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    public List<Message> getMessagesByUser( Integer accountId) {
        return messageRepository.findAllByPostedBy(accountId);
    }
    
    public int deleteMessage (int messageId) {
        if(messageRepository.existsById(messageId) == false){
            return 0;
        } 

        messageRepository.deleteById(messageId);
        return 1;
    }  

    public int updateMessage(int messageId, String newMessageText) {
         if(newMessageText == null || newMessageText.isBlank()){
            throw new IllegalArgumentException("Message text cannot be empty.");
        }
        if(newMessageText.length() > 255) {
            throw new IllegalArgumentException("Message text cannot exceed 255 characters");
        }
        
        
        Message message = messageRepository.findById(messageId).orElseThrow(() -> new IllegalArgumentException("Message not found"));
        
        if(message != null){
            messageRepository.save(message);
            return 1;
        } else{
            return 0;
        }
    }
          
    
}
