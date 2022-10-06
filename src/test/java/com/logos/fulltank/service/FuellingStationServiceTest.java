package com.logos.fulltank.service;

import com.logos.fulltank.dao.FuellingStationDao;
import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.exception.FuellingStationNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
class FuellingStationServiceTest {
    @Autowired
    private FuellingStationService fuellingStationService;
    @MockBean
    FuellingStationDao fuellingStationDao;


    @Test
    @DisplayName("Check saving fuelling station")
    void createFuellingStation() {
        FuellingStation fuellingStation = new FuellingStation("Okko", 2, 11.11, 22.22, "Lviv");
        Mockito.when(fuellingStationDao.save(fuellingStation)).thenReturn(fuellingStation);
        FuellingStation station = fuellingStationService.createFuellingStation(fuellingStation);
        Assertions.assertEquals(station, fuellingStation);
    }


    @Test
    void getAll() {
        FuellingStation fuellingStation = new FuellingStation("Okko", 2, 11.11, 22.22, "Lviv");
        FuellingStation fuellingStation1 = new FuellingStation("Okko", 2, 11.11, 22.22, "Lviv");
        List<FuellingStation> stations= new ArrayList<>();
        stations.add(fuellingStation);
        stations.add(fuellingStation1);

        Mockito.when(fuellingStationDao.findAll()).thenReturn(stations);
        List<FuellingStation> all = fuellingStationService.getAll();
        Assertions.assertEquals(all,stations);
    }

    @Test
    void getById() throws FuellingStationNotFoundException {
        FuellingStation fuellingStation = new FuellingStation("Okko", 2, 11.11, 22.22, "Lviv");
        Mockito.when(fuellingStationDao.findById(fuellingStation.getId())).thenReturn(Optional.of(fuellingStation));
        FuellingStation byId = fuellingStationService.getById(fuellingStation.getId());

        Assertions.assertEquals(fuellingStation,Optional.of(byId).get());
    }

    @Test
    void deleteFuellingStation() {
    }

    @Test
    void getListFuellingStations() {
    }
}