package com.ablive.friends.controller;

import com.ablive.friends.model.Friend;
import com.ablive.friends.service.FriendService;
import com.ablive.friends.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.Optional;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    Friend create(@RequestBody Friend friend) {
        if (friend.getId() == 0 && friend.getFirstName() != null && friend.getLastName() != null) {
            return friendService.save(friend);
        } else {
            throw new ValidationException("friend cannot be created");
        }
    }

    /*@ExceptionHandler
    ResponseEntity<String> exceptionHandler(ValidationException e) {
        return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
    }*/

    //  Moved to Controller Exception Handler
/*  @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(ValidationException e) {
        return new ErrorMessage("400", e.getMessage());
    }*/

    @GetMapping("/friend")
    Iterable<Friend> read() {
        return friendService.findAll();
    }

    //  PUT Problem
    //  Check if the data exists in the database, only the update otherwise error
    @PutMapping("/friend")
    ResponseEntity<Friend> update(@RequestBody Friend friend) {
        if (friendService.findById(friend.getId()).isPresent()) {
            return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        } else {
            return new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/friend/{id}")
    void delete(@PathVariable Integer id) {
        friendService.deleteById(id);
    }

    @GetMapping("/friend/{id}")
    Optional<Friend> findById(@PathVariable Integer id) {
        return friendService.findById(id);
    }

    @GetMapping("/friend/search")
    Iterable<Friend> findByQuery(@RequestParam(value = "first", required = false) String firstName, @RequestParam(value = "last", required = false) String lastName) {

        if (null != firstName && null != lastName) {
            return friendService.findByFirstNameAndLastName(firstName, lastName);
        } else if (null != firstName) {
            return friendService.findByFirstName(firstName);
        } else if (null != lastName) {
            return friendService.findByLastName(lastName);
        } else {
            return friendService.findAll();
        }

    }

}