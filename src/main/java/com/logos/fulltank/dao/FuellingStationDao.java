package com.logos.fulltank.dao;

import com.logos.fulltank.entity.FuellingStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuellingStationDao extends JpaRepository<FuellingStation, Integer> {
}
