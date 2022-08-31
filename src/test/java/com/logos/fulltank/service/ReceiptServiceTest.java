package com.logos.fulltank.service;

import com.logos.fulltank.dao.ReceiptDao;
import com.logos.fulltank.entity.FuelName;
import com.logos.fulltank.entity.Receipt;
import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.ReceiptNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;

    @MockBean
    private ReceiptDao receiptDao;

    @Test
    void createReceipt() {
        Receipt receipt = new Receipt(new Date(System.currentTimeMillis()),"pump1", FuelName.A92,44,10,440, new User());
        Mockito.when(receiptDao.save(receipt)).thenReturn(receipt);
        Receipt receipt1 = receiptService.createReceipt(receipt);
        Assertions.assertEquals(receipt1, receipt);

    }

    @Test
    void getReceiptById() throws ReceiptNotFoundException {
        Receipt receipt = new Receipt(new Date(System.currentTimeMillis()),"pump1", FuelName.A92,44,10,440, new User());
        Mockito.when(receiptDao.findById(receipt.getId())).thenReturn(Optional.of(receipt));
        Receipt receiptById = receiptService.getReceiptById(receipt.getId());
        Assertions.assertEquals(Optional.of(receiptById).get(),receipt);

    }

    @Test
    void getReceiptByUserId() {
    }
}