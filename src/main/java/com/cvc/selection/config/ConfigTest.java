package com.cvc.selection.config;

import com.cvc.selection.entity.Account;
import com.cvc.selection.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ConfigTest implements CommandLineRunner {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void run(String... args) throws Exception {
        Account ac1 = new Account(null, "Ec Bahia", "12345","01464578654");
        Account ac2 = new Account(null, "City Club", "2345678","23464578654");
        Account ac3 = new Account(null, "Salvador Esporte Clube", "212345","51464578654");
        accountRepository.saveAll(Arrays.asList(ac1,ac2,ac3));
    }
}
