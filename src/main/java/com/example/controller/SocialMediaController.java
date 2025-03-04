package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    private AccountService accountService;
    @Autowired
    public SocialMediaController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody Account userRegistration(@RequestBody Account account){
        return accountService.userRegistration(account);
    }

    @PostMapping("login")
    public ResponseEntity<Account> userLogin(@RequestBody Account account){
        return ResponseEntity.ok(accountService.userLogin(account));
    }
}
