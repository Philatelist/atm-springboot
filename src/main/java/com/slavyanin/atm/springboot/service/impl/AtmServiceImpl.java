package com.slavyanin.atm.springboot.service.impl;

import com.slavyanin.atm.springboot.model.Atm;
import com.slavyanin.atm.springboot.repository.AtmRepository;
import com.slavyanin.atm.springboot.service.AtmService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service("atmService")
public class AtmServiceImpl implements AtmService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("atmRepository")
    private AtmRepository atmRepository;

    @Override
    public boolean insert(List<String> banknotes) {
        try {
            atmRepository.insert(new Atm(UUID.randomUUID(), banknotes.get(0), banknotes.get(1)));
            return true;
        } catch (Exception e) {
            log.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void delete(String banknotes) {

    }

    @Override
    public void fillAtm() {

    }

    @Override
    public void updateBanknotes(String banknotes) {

    }

    @Override
    public Set<Atm> checkBanknotes() {
        return atmRepository.checkBanknotes();
    }
}
