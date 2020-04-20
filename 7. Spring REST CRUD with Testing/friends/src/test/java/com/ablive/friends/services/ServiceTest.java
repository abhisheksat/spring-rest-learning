package com.ablive.friends.services;

import com.ablive.friends.model.Friend;
import com.ablive.friends.service.FriendService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceTest {

    @Autowired
    FriendService friendService;

    @Test
    public void testCreateReadUpdate() {

        Friend friend =  new Friend("Gordon", "Ramsay");

        friendService.save(friend);

        Iterable<Friend> friends = friendService.findAll();
        Assertions.assertThat(friends).extracting(Friend::getFirstName).containsOnly("Gordon");

        friendService.deleteAll();
        Assertions.assertThat(friendService.findAll()).isEmpty();
    }
}