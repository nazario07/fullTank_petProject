package com.logos.fulltank.dao;

import com.logos.fulltank.entity.Pump;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PumpDao extends JpaRepository<Pump, Integer> {
}
