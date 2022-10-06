package com.logos.fulltank.service.impl;

import com.logos.fulltank.dao.ReceiptDao;
import com.logos.fulltank.entity.Receipt;
import com.logos.fulltank.exception.ReceiptNotFoundException;
import com.logos.fulltank.service.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j(topic = "Receipt Service")
public class ReceiptServiceImpl implements ReceiptService {

    private  final ReceiptDao receiptDao;

    public ReceiptServiceImpl(ReceiptDao receiptDao) {
        this.receiptDao = receiptDao;
    }

    @Override
    public Receipt createReceipt(Receipt receipt) {
        return receiptDao.save(receipt);
    }

    @Override
    public Receipt getReceiptById(int id) throws ReceiptNotFoundException {
        Optional<Receipt> byId = receiptDao.findById(id);
        if (byId.isPresent()) {
            log.info("Information about receipt with id " + id + " ready for you");
            return byId.get();
        } else {
            log.error("Receipt with id " + id + " is not exist");
            throw new ReceiptNotFoundException("Receipt with id " + id + " is not exist");
        }
    }

    @Override
    public List<Receipt> getReceiptByUserId(int id) {
        return receiptDao.findReceiptByUserId(id);
    }
}
