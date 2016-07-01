package com.slavyanin.atm.springboot.service;

import java.util.Set;

public interface UserService {

    public boolean insert(String user);

    public void delete(String userEmail);

    public Set<String> findAll();
}
