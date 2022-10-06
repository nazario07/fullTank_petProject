package com.logos.fulltank.service;

import com.logos.fulltank.dao.PumpDao;
import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.entity.Pump;
import com.logos.fulltank.exception.PumpNotFoundException;
import org.junit.jupiter.api.Assertions;
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

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class PumpServiceTest {
    @Autowired
    private PumpService pumpService;
    @MockBean
    private PumpDao pumpDao;

    @Test
    void createPump() {
        Pump pump = new Pump("Pump1",new FuellingStation());
        Mockito.when(pumpDao.save(pump)).thenReturn(pump);
        Pump pump1 = pumpService.createPump(pump);
        Assertions.assertEquals(pump1, pump);

    }

    @Test
    void getAll() {
        Pump pump = new Pump("Pump1",new FuellingStation());
        Pump pump1 = new Pump("Pump2",new FuellingStation());
        List<Pump> pumpList = new ArrayList<>();
        pumpList.add(pump);
        pumpList.add(pump1);

        Mockito.when(pumpDao.findAll()).thenReturn(pumpList);
        List<Pump> all = pumpService.getAll();
        Assertions.assertEquals(all,pumpList);

    }

    @Test
    void getById() throws PumpNotFoundException {
        Pump pump = new Pump("Pump1",new FuellingStation());
        Mockito.when(pumpDao.findById(pump.getId())).thenReturn(Optional.of(pump));
        Pump byId = pumpService.getById(pump.getId());
        Assertions.assertEquals(pump,Optional.of(byId).get());
    }

    @Test
    void deletePump() {
    }
}