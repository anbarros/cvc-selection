package com.cvc.selection.config;

import com.cvc.selection.entity.Account;
import com.cvc.selection.entity.TypeTransaction;
import com.cvc.selection.repository.AccountRepository;
import com.cvc.selection.repository.TypeTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class ConfigTest implements CommandLineRunner {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TypeTransactionRepository typeTransactionRepository;

    @Override
    public void run(String... args) throws Exception {
        Account ac1 = new Account(12345L, "Ec Bahia","01464578654");
        Account ac2 = new Account(2345678L, "City Club", "23464578654");
        Account ac3 = new Account(212345L, "Salvador Esporte Clube", "51464578654");
        accountRepository.saveAll(Arrays.asList(ac1,ac2,ac3));
        TypeTransaction t1 = new TypeTransaction(null,"Dia Atual","A");
        TypeTransaction t2 = new TypeTransaction(null,"Menor que 10 dias","B");
        TypeTransaction t3 = new TypeTransaction(null,"Taxas Regressiva","C");
        typeTransactionRepository.saveAll(Arrays.asList(t1,t2,t3));


    }
}
