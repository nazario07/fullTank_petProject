package com.logos.fulltank.service.impl;

import com.logos.fulltank.dao.PumpDao;
import com.logos.fulltank.entity.Product;
import com.logos.fulltank.entity.Pump;
import com.logos.fulltank.exception.ProductNotFoundException;
import com.logos.fulltank.exception.PumpNotFoundException;
import com.logos.fulltank.service.PumpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Pump Service")
public class PumpServiceImpl implements PumpService {
    private final PumpDao pumpDao;

    public PumpServiceImpl(PumpDao pumpDao) {
        this.pumpDao = pumpDao;
    }

    @Override
    public Pump createPump(Pump pump) {
        return pumpDao.save(pump);
    }

    @Override
    public List<Pump> getAll() {
        return pumpDao.findAll();
    }

    @Override
    public Pump getById(int id) throws PumpNotFoundException {
        Optional<Pump> byId = pumpDao.findById(id);
        if (byId.isPresent()) {
            log.info("Information about pump with id " + id + " ready for you");
            return byId.get();
        } else {
            log.error("Pump with id " + id + " is not exist");
            throw new PumpNotFoundException("Pump with id " + id + " is not exist");
        }
    }

    @Override
    public void deletePump(int id) throws PumpNotFoundException {
        Optional<Pump> byId = pumpDao.findById(id);
        if (byId.isPresent()) {
            pumpDao.deleteById(id);
            log.info("Pump with id " + id + " was deleted");
        } else {
            log.error("Pump with id " + id + " is not exist");
            throw new PumpNotFoundException("Pump with id " + id + " is not exist");
        }
    }
}
