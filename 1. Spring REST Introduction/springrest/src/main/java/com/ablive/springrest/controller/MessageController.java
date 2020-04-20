package com.ablive.springrest.controller;

import com.ablive.springrest.model.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @GetMapping("/message")
    Message send() {
        return new Message("first message");
    }

    @PostMapping("/message")
    Message echo (@RequestBody Message message) {
        return message;
    }

}