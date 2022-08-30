package com.logos.fulltank.dao;

import com.logos.fulltank.entity.Receipt;
import com.logos.fulltank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptDao  extends JpaRepository<Receipt, Integer> {

}
