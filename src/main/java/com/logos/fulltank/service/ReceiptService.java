package com.logos.fulltank.service;

import com.logos.fulltank.entity.Receipt;
import com.logos.fulltank.exception.ReceiptNotFoundException;


public interface ReceiptService {
    Receipt createReceipt(Receipt receipt);
    Receipt getReceiptById(int id) throws ReceiptNotFoundException;

}
