package com.cvc.selection.service;

import com.cvc.selection.entity.Account;
import com.cvc.selection.entity.Transfer;
import com.cvc.selection.repository.AccountRepository;
import com.cvc.selection.repository.TransferRepository;
import com.cvc.selection.repository.TypeTransactionRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;


@Service
public class TransferTransactionService {


    private TransferRepository transferRepository;
    private TypeTransactionRepository typeRepository;
    private AccountService accountService;

    public TransferTransactionService(TransferRepository transferRepository, TypeTransactionRepository typeRepository) {
        this.transferRepository = transferRepository;
        this.typeRepository = typeRepository;
    }

    public Transfer scheduling (Transfer transfer) throws Exception {
        validateAccount(transfer);
        return transferRepository.save(transfer);
    }

    private void validateAccount(Transfer transfer) throws Exception {
        Account origin = accountService.findById(transfer.getAccountOrigin().getNumberAccount());
        Account destination = accountService.findById(transfer.getAccountOrigin().getNumberAccount());
        if(origin == null){
            throw new Exception("Account not found "+ transfer.getAccountOrigin().getNumberAccount());
        }
        if(destination == null){
            throw new Exception("Account not found "+ transfer.getAccountDestination().getNumberAccount());
        }


    }

    public static long getDiferenceDays(Transfer transfer) {
        return Duration.between(transfer.getTransferDate().atStartOfDay(), transfer.getScheduleDate().atStartOfDay()).toDays();
    }
}
