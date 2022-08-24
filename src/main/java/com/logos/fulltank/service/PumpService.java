package com.logos.fulltank.service;

import com.logos.fulltank.entity.Pump;
import com.logos.fulltank.exception.PumpNotFoundException;

import java.util.List;

public interface PumpService {
    Pump createPump(Pump pump);
    List<Pump> getAll();
    Pump getById(int id) throws PumpNotFoundException;
    void deletePump(int id) throws PumpNotFoundException;
}
