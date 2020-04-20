package com.ablive.friends.service;

import com.ablive.friends.model.Friend;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendService extends CrudRepository<Friend, Integer> {

}