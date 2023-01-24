package com.cvc.selection.resources.utils;

import com.cvc.selection.entity.Account;
import com.cvc.selection.entity.Transfer;
import com.cvc.selection.resources.dto.TransferDTO;
import org.springframework.stereotype.Component;

@Component
public class  Converter {

    public Transfer convertTransfer(TransferDTO objTransfer) {

        Transfer transfer = new Transfer();
        transfer.setTransferValue(objTransfer.getTransferValue());
        transfer.setTransferDate(objTransfer.getTransferDate());
        transfer.setScheduleDate(objTransfer.getScheduleDate());
        transfer.setAccountOrigin(new Account());
        transfer.getAccountOrigin().setNumberAccount(objTransfer.getNumberAccountOrigin());
        transfer.setAccountDestination(new Account());
        transfer.getAccountDestination().setNumberAccount(objTransfer.getNumberAccountDestination());
        return transfer;
    }


}
