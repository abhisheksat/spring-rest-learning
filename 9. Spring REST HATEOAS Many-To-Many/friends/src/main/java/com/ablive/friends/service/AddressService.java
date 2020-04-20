package com.ablive.friends.service;

import com.ablive.friends.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressService extends CrudRepository<Address, Integer> {

}