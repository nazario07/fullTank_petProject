package com.logos.fulltank.service.impl;

import com.logos.fulltank.dao.FuellingStationDao;
import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.exception.FuellingStationNotFoundException;
import com.logos.fulltank.service.FuellingStationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Fuelling Service")
public class FuellingStationServiceImpl implements FuellingStationService {
    private final FuellingStationDao fuellingStationDao;

    public FuellingStationServiceImpl(FuellingStationDao fuellingStationDao) {
        this.fuellingStationDao = fuellingStationDao;
    }

    @Override
    public FuellingStation createFuellingStation(FuellingStation fuellingStation) {
        log.info("Created new fuelling station" + fuellingStation.toString());
        return fuellingStationDao.save(fuellingStation);
    }

    @Override
    public List<FuellingStation> getAll() {
        return fuellingStationDao.findAll();
    }

    @Override
    public FuellingStation getById(int id) throws FuellingStationNotFoundException {
        Optional<FuellingStation> byId = fuellingStationDao.findById(id);
        if (byId.isPresent()) {
            log.info("Information about fuelling station with id " + id + " ready for you");
            return byId.get();
        } else {
            log.error("Fuelling station with id " + id + " is not exist");
            throw new FuellingStationNotFoundException("Fuelling station with id " + id + " is not exist");
        }
    }

    @Override
    public void deleteFuellingStation(int id) throws FuellingStationNotFoundException {
        Optional<FuellingStation> byId = fuellingStationDao.findById(id);
        if (byId.isPresent()) {
            fuellingStationDao.deleteById(id);
            log.info("Fuelling station with id " + id + " was deleted");
        } else {
            log.error("Fuelling station with id " + id + " is not exist");
            throw new FuellingStationNotFoundException("Fuelling station with id " + id + " is not exist");
        }
    }


    @Override
    public List<FuellingStation> getListFuellingStations(double latitude, double longitude, int radius) {
        List<FuellingStation> listFuellingStations = new ArrayList<>();
        if (radius == 0) {
            FuellingStation closestStation = getClosestStation(latitude, longitude);
            listFuellingStations.add(closestStation);
        } else {
            List<FuellingStation> listFuellingStationsInRadius = getListFuellingStationsInRadius
                    (latitude, longitude, radius);
            listFuellingStations.addAll(listFuellingStationsInRadius);
        }
        return listFuellingStations;
    }

    private FuellingStation getClosestStation(double latitude, double longitude) {
        return fuellingStationDao.findClosestStation(latitude,longitude);
    }

    private List<FuellingStation> getListFuellingStationsInRadius(double latitude, double longitude, int radius) {
        return fuellingStationDao.findClosestFuellingStationInRadius(latitude,longitude,radius);


    }

}
