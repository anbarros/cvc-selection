package com.cvc.selection.service;

import com.cvc.selection.entity.Transfer;
import com.cvc.selection.enums.TransactionTypeEnum;
import com.cvc.selection.repository.AccountRepository;
import com.cvc.selection.repository.TransferRepository;
import com.cvc.selection.repository.TypeTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransferTransactionService {


    private final TransferRepository transferRepository;
    private final TypeTransactionRepository typeRepository;
    private final AccountRepository accountRepository;

    private final TransactionHelperService transactionHelperService;


    public TransferTransactionService(TransferRepository transferRepository, TypeTransactionRepository typeRepository, AccountRepository accountRepository, TransactionHelperService transactionHelperService) {
        this.transferRepository = transferRepository;
        this.typeRepository = typeRepository;
        this.accountRepository = accountRepository;
        this.transactionHelperService = transactionHelperService;
    }

    public List<Transfer> listTransfers() {
        return transferRepository.findAll();
    }

    public Transfer scheduling (Transfer transfer) throws Exception {
        transactionHelperService.validateAccount(transfer);
        long durationDays = transactionHelperService.getDiferenceDays(transfer);
        TransactionTypeEnum typeTrasaction = transactionHelperService.loadingTypeTrasaction(transfer,durationDays);
        transfer.setTransactionType(typeTrasaction);
        transfer.setFee(transactionHelperService.generateFeeValue(transfer,durationDays));
        return transferRepository.save(transfer);
    }

}
