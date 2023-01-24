package com.cvc.selection.resources;

import com.cvc.selection.entity.Transfer;
import com.cvc.selection.resources.dto.TransferDTO;
import com.cvc.selection.resources.utils.Converter;
import com.cvc.selection.service.TransferTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value ="/schedule")
public class TransferResource {
    @Autowired
    private TransferTransactionService transaction;
    @Autowired
    private Converter converter;
    @GetMapping
    public ResponseEntity<List<Transfer>> findAll(){
        List<Transfer> transfers = transaction.listTransfers();
        return ResponseEntity.ok().body(transfers);
    }


    @PostMapping
public TransferDTO insert (@RequestBody TransferDTO objTransfer) throws Exception {
        Transfer transfer = converter.convertTransfer(objTransfer);
        Transfer  transferTransaction = transaction.scheduling(transfer);
        TransferDTO objTransferResponse =  TransferDTO.builder()
                .numberAccountOrigin(transferTransaction.getAccountOrigin().getNumberAccount())
                .transferDate(transferTransaction.getTransferDate())
                .fee(transferTransaction.getFee())
                .scheduleDate(transferTransaction.getScheduleDate())
                .numberAccountDestination(transferTransaction.getAccountDestination().getNumberAccount())
                .transferValue(transferTransaction.getTransferValue())
                .id(transferTransaction.getId()).build();
        return objTransferResponse;
    }



}
