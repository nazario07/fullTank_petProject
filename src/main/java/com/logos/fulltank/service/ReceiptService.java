package com.logos.fulltank.service;

import com.logos.fulltank.entity.Receipt;
import com.logos.fulltank.exception.ReceiptNotFoundException;

import java.util.List;


public interface ReceiptService {
    Receipt createReceipt(Receipt receipt);
    Receipt getReceiptById(int id) throws ReceiptNotFoundException;
    List<Receipt> getReceiptByUserId(int id);

}
