package com.slavyanin.atm.springboot.service.impl;

import com.slavyanin.atm.springboot.model.User;
import com.slavyanin.atm.springboot.repository.UserRepository;
import com.slavyanin.atm.springboot.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public boolean insert(List<String> userParam) {
        try {
            userRepository.insert(new User(UUID.randomUUID(), userParam.get(0), userParam.get(1), userParam.get(2)));
            return true;
        } catch (Exception e) {
            log.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public void delete(String userEmail) {
        try {
            userRepository.delete(new User(UUID.randomUUID(), userEmail));

        } catch (Exception e) {
            log.error("ERROR DELETE DATA: " + e.getMessage(), e);

        }
    }

    @Override
    public Set<User> findAll() {
        return userRepository.findAll();
    }
}
