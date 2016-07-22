package com.slavyanin.atm.springboot.model;

import java.util.UUID;


public class Atm implements DomainObject {

    private UUID id;
    private String banknotes;
    private String amount;

    public Atm(UUID id, String banknotes, String amount) {
        this.id = id;
        this.banknotes = banknotes;
        this.amount = amount;
    }

    public Atm(String banknotes, String amount) {
        this.banknotes = banknotes;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBanknotes() {
        return banknotes;
    }

    public void setBanknotes(String banknotes) {
        this.banknotes = banknotes;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Atm atm = (Atm) o;

        if (id != null ? !id.equals(atm.id) : atm.id != null) return false;
        if (banknotes != null ? !banknotes.equals(atm.banknotes) : atm.banknotes != null) return false;
        return amount != null ? amount.equals(atm.amount) : atm.amount == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (banknotes != null ? banknotes.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Atm{" +
                "id=" + id +
                ", banknotes=" + banknotes +
                ", amount=" + amount +
                '}';
    }
}
