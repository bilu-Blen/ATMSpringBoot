package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private long account;
    @NotNull
    private long amountInAccount;

    public Customer() {
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public long getAmountInAccount() {
        return amountInAccount;
    }

    public void setAmountInAccount(long amountInAccount) {
        this.amountInAccount = amountInAccount;
    }
}
