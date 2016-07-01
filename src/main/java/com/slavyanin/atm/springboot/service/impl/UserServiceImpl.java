package com.slavyanin.atm.springboot.service.impl;

import com.slavyanin.atm.springboot.entity.User;
import com.slavyanin.atm.springboot.repository.UserRepository;
import com.slavyanin.atm.springboot.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger LOG = org.jboss.logging.Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public boolean insert(String userParam) {
        try {
            userRepository.insert(new User(UUID.randomUUID(), userParam));
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void delete(String userEmail) {
        try {
            userRepository.delete(new User(UUID.randomUUID(), userEmail));

        } catch (Exception e) {
            LOG.error("ERROR DELETE DATA: " + e.getMessage(), e);

        }
    }

    @Override
    public Set<String> findAll() {
        return userRepository.findAll();
    }
}
