package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;
   
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }

    /*
     * handles account registration with the help of accountService class
     * returns the created account with its new Id
     */

    @PostMapping("register")
    public ResponseEntity<Account> userRegistration(@RequestBody Account account){
        return ResponseEntity.ok(accountService.userRegistration(account));
    }

    /*
     * handles account login with the help of accountService class
     * uses ResponseEntity to respond to the client
     */

    @PostMapping("login")
    public ResponseEntity<Account> userLogin(@RequestBody Account account){
        return ResponseEntity.ok(accountService.userLogin(account));
    }

    /*
     * this handler creates a message in the database and returns
     * a ResponseBody of type message
     */

    @PostMapping(value = "messages")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Message createMessage(@RequestBody Message message){
        return messageService.createMessage(message);
    }

    /**
     * HttpStatus is always 200
     * @return list of messages
     */
    @GetMapping(value = "messages")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }
    /**
     * 
     * @param messageId
     * @return Message
     */
    @GetMapping(value = "messages/{messageId}")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Message getMessageByMessageId(@PathVariable Integer messageId){
        return messageService.getMessageByMessageId(messageId);
    }

    /**
     * 
     * @param messageId
     * @return ResponseEntity
     */
    @DeleteMapping(value = "messages/{messageId}")
    public ResponseEntity<Integer>  deleteMessageByMessageId(@PathVariable Integer messageId){
        return ResponseEntity.ok(messageService.deleteMessageByMessageId(messageId));
    }

    /**
     * 
     * @param messageId
     * @param message
     * @return
     */
    @PatchMapping(value = "messages/{messageId}")
    public ResponseEntity<Integer>  updateMessageByMessageId(@PathVariable Integer messageId, @RequestBody Message message){
        return ResponseEntity.ok(messageService.updateMessageByMessageId(messageId, message));
    }

    /**
     * 
     * @param account_id
     * @return List<Message>
     */
    @GetMapping("accounts/{account_id}/messages")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<Message> getAllMessagesForUser(@PathVariable Integer account_id){
        return messageService.getAllMessagesForUser(account_id);
    }
}
