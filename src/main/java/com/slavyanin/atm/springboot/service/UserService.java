package com.slavyanin.atm.springboot.service;

import com.slavyanin.atm.springboot.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    boolean insert(List<String> user);

    void delete(String userEmail);

    Set<User> findAll();


}
