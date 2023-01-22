package com.cvc.selection.service;

import com.cvc.selection.entity.Account;
import com.cvc.selection.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AccountService {
    @Autowired
    AccountRepository repository;
    public Account findById(Long id){
        Optional<Account> objAccount = repository.findById(id);
        return objAccount.get();
    }

}
