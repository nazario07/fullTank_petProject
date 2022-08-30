package com.logos.fulltank.service;

import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.exception.FuellingStationNotFoundException;

import java.util.List;

public interface FuellingStationService {
    FuellingStation createFuellingStation(FuellingStation fuellingStation);
    List<FuellingStation> getAll();
    FuellingStation getById(int id) throws FuellingStationNotFoundException;
    void deleteFuellingStation(int id) throws FuellingStationNotFoundException;

    List<FuellingStation> getListFuellingStations(double latitude, double longitude, int radius);



}
