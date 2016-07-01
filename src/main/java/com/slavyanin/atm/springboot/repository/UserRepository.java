package com.slavyanin.atm.springboot.repository;

import com.slavyanin.atm.springboot.entity.DomainObject;

import java.util.Set;

public interface UserRepository<V extends DomainObject> {

    void insert(V object);

    void delete(V object);

    Set<String> findAll();
}
