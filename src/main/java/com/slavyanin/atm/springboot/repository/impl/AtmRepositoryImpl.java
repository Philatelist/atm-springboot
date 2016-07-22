package com.slavyanin.atm.springboot.repository.impl;

import com.slavyanin.atm.springboot.model.Atm;
import com.slavyanin.atm.springboot.model.DomainObject;
import com.slavyanin.atm.springboot.repository.AtmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository("atmRepository")
public class AtmRepositoryImpl implements AtmRepository {

    @Autowired
    protected JdbcOperations jdbcOperations;

    @Override
    public void insert(DomainObject object) {

    }

    @Override
    public void delete(DomainObject object) {

    }

    @Override
    public void updateBanknotes(DomainObject object) {

    }

    @Override
    public void fillAtm() {

    }

    @Override
    public Set<Atm> checkBanknotes() {
        Set<Atm> result = new HashSet<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM atm ORDER BY banknotes ASC;");

        while (rowSet.next()) {
            Atm atm = new Atm(rowSet.getString("banknotes"), rowSet.getString("amount"));
            result.add(atm);
        }

        return result;
    }
}
