package com.slavyanin.atm.springboot.repository;

import com.slavyanin.atm.springboot.model.Atm;
import com.slavyanin.atm.springboot.model.DomainObject;

import java.util.Set;

public interface AtmRepository  <V extends DomainObject>{

    void insert(V object);

    void delete(V object);

    void updateBanknotes(V object);

    void fillAtm();

    Set<Atm> checkBanknotes();

}
