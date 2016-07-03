package com.slavyanin.atm.springboot.service;

import java.util.List;
import java.util.Set;

public interface UserService {

    public boolean insert(List<String> user);

    public void delete(String userEmail);

    public Set<String> findAll();
}
