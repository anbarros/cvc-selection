package com.cvc.selection.service;

import com.cvc.selection.entity.Account;
import com.cvc.selection.entity.Transfer;
import com.cvc.selection.enums.TransactionTypeEnum;
import com.cvc.selection.repository.AccountRepository;
import com.cvc.selection.repository.TransferRepository;
import com.cvc.selection.repository.TypeTransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class TransferTransactionService {


    private final TransferRepository transferRepository;
    private final TypeTransactionRepository typeRepository;
    private final AccountRepository accountRepository;


    public TransferTransactionService(TransferRepository transferRepository, TypeTransactionRepository typeRepository, AccountRepository accountRepository) {
        this.transferRepository = transferRepository;
        this.typeRepository = typeRepository;
        this.accountRepository = accountRepository;
    }

    public List<Transfer> listTransfers() {
        return transferRepository.findAll();
    }

    public Transfer scheduling (Transfer transfer) throws Exception {
        validateAccount(transfer);
        long durationDays = getDiferenceDays(transfer);
        TransactionTypeEnum typeTrasaction = loadingTypeTrasaction(transfer,durationDays);
        transfer.setTransactionType(typeTrasaction);
        transfer.setFee(generateFeeValue(transfer,durationDays));
        return transferRepository.save(transfer);
    }

    private BigDecimal generateFeeValue(Transfer transfer, long durationDays) {
    if(transfer.getTransactionType().equals(TransactionTypeEnum.A)){
        return BigDecimal.valueOf(3L).add(transfer.getTransferValue().multiply(BigDecimal.valueOf(0.03)));
    }
    if(transfer.getTransactionType().equals(TransactionTypeEnum.B)){
        return BigDecimal.valueOf(12L).multiply(BigDecimal.valueOf(durationDays));
    }
        if(transfer.getTransactionType().equals(TransactionTypeEnum.C)) {
            if (durationDays > 10 && durationDays <= 20) {
                return transfer.getTransferValue().multiply(BigDecimal.valueOf(0.08));
            }
            if (durationDays > 20 && durationDays <= 30) {
                return transfer.getTransferValue().multiply(BigDecimal.valueOf(0.06));
            }
            if (durationDays > 30 && durationDays <= 40) {
                return transfer.getTransferValue().multiply(BigDecimal.valueOf(0.04));
            }
            if (durationDays > 40 && transfer.getTransferValue().longValue() > 100000) {
                return transfer.getTransferValue().multiply(BigDecimal.valueOf(0.02));
            }
        }


        return null;
    }

    private TransactionTypeEnum loadingTypeTrasaction(Transfer transfer, long durationDays) throws Exception {

        if(transfer.getTransferDate().isBefore(LocalDate.now())){
            throw new Exception("transfer date cannot be less than the current date "+ LocalDate.now());
        }
        if(transfer.getTransferDate().isEqual(LocalDate.now())){
            return TransactionTypeEnum.A;
        }
        if (durationDays < 10){
            return TransactionTypeEnum.B;
        }
        if (durationDays > 10){
            return TransactionTypeEnum.C;
        }

        return null;
    }

    private void validateAccount(Transfer transfer) throws Exception {
        Optional<Account> origin = accountRepository.findById(transfer.getAccountOrigin().getNumberAccount());
        Optional<Account> destination = accountRepository.findById(transfer.getAccountOrigin().getNumberAccount());
        if(origin == null){
            throw new Exception("Account not found "+ transfer.getAccountOrigin().getNumberAccount());
        }
        if(destination == null){
            throw new Exception("Account not found "+ transfer.getAccountDestination().getNumberAccount());
        }


    }

    public static long getDiferenceDays(Transfer transfer) {
        return Duration.between(transfer.getScheduleDate().atStartOfDay(),transfer.getTransferDate().atStartOfDay()).toDays();
    }
}
