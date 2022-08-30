package com.logos.fulltank.dao;

import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.entity.Receipt;
import com.logos.fulltank.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceiptDao  extends JpaRepository<Receipt, Integer> {
    @Query(value = "SELECT * FROM receipts r WHERE user_id=:id ", nativeQuery = true)
    List<Receipt> findReceiptByUserId(int id);

}
