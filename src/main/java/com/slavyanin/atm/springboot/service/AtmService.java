package com.slavyanin.atm.springboot.service;

import com.slavyanin.atm.springboot.model.Atm;

import java.util.List;
import java.util.Set;

public interface AtmService {

    boolean insert(List<String> banknotes);

    void delete(String banknotes);

    void fillAtm();

    void updateBanknotes(String banknotes);

    Set<Atm> checkBanknotes();
}
