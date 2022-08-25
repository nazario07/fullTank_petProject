package com.logos.fulltank.service;

import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.exception.FuellingStationNotFoundException;

import java.util.List;

public interface FuellingStationService {
    FuellingStation createFuellingStation(FuellingStation fuellingStation);
    List<FuellingStation> getAll();
    FuellingStation getById(int id) throws FuellingStationNotFoundException;
    void deleteFuellingStation(int id) throws FuellingStationNotFoundException;

    FuellingStation getClosestStation(double latitude, double longitude);
    List<FuellingStation> getListFuellingStationsInRadius(double latitude, double longitude, double radius);

}
