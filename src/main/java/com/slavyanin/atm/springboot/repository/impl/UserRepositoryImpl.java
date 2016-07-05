package com.slavyanin.atm.springboot.repository.impl;

import com.slavyanin.atm.springboot.entity.User;
import com.slavyanin.atm.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository<User> {

    @Autowired
    protected JdbcOperations jdbcOperations;

    @Override
    public void insert(User user) {

        Object[] params = new Object[] { user.getId(), user.getName(), user.getEmail(), user.getPassword()};
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};

        jdbcOperations.update("INSERT INTO users(\n" +
                "            users_id, name, email, password)\n" +
                "    VALUES (cast(? as UUID), ?, ?, ?);", params, types);
    }

    @Override
    public void delete(User user) {
        jdbcOperations.update("DELETE FROM users\n" +
                " WHERE name = '" + user.getName().toString() + "';");
    }

    @Override
    public Set<String> findAll() {
        Set<String> result = new HashSet<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM users ORDER BY name DESC;");
        while (rowSet.next()) {
            result.add(rowSet.getString("name") + " " + rowSet.getString("email") + " " + rowSet.getString("password"));
        }
        return result;
    }

    @Override
    public Set<User> findAllUsers() {
        Set<User> result = new HashSet<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM users ORDER BY name DESC;");

        while (rowSet.next()) {
            User user = new User(rowSet.getString("name"), rowSet.getString("email"), rowSet.getString("password"));
            result.add(user);
        }

        return result;
    }
}