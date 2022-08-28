package com.logos.fulltank.dao;

import com.logos.fulltank.entity.FuellingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuellingStationDao extends JpaRepository<FuellingStation, Integer> {
    String HAVERSINE_FORMULA = "(6371 * acos(cos(radians(:latitude)) * cos(radians(fs.latitude)) *" +
            " cos(radians(fs.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(fs.latitude))))";

    @Query(value = "SELECT * FROM fuelling_station fs WHERE " + HAVERSINE_FORMULA + " < :radius ORDER BY " + HAVERSINE_FORMULA + " DESC", nativeQuery = true)
    List<FuellingStation> findClosestFuellingStationInRadius(double latitude, double longitude, int radius);

    @Query(value = "SELECT * FROM fuelling_station fs ORDER BY " + HAVERSINE_FORMULA + " DESC LIMIT 1", nativeQuery = true)
    FuellingStation findClosestStation(double latitude, double longitude);

}
