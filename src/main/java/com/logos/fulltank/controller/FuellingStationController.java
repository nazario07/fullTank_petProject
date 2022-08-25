package com.logos.fulltank.controller;

import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.entity.Pump;
import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.UserAlreadyExistException;
import com.logos.fulltank.exception.UserNotFoundException;
import com.logos.fulltank.service.FuellingStationService;
import com.logos.fulltank.service.ProductService;
import com.logos.fulltank.service.PumpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping
@Slf4j(topic = "FuellingStation Controller")
public class FuellingStationController {

    private final FuellingStationService fuellingStationService;
    private final PumpService pumpService;
    private final ProductService productService;

    public FuellingStationController(FuellingStationService fuellingStationService,
                                     PumpService pumpService,
                                     ProductService productService) {
        this.fuellingStationService = fuellingStationService;
        this.pumpService = pumpService;
        this.productService = productService;
    }

    @GetMapping("/registrationfs")
    public String toRegister() {
        return "registrationFS";
    }

    @PostMapping("/registrationfs")
    public String createFuellingStation(Model model,
                             @RequestParam String providerOrBrand,
                             @RequestParam int numberOfPumps,
                             @RequestParam String latitude,
                             @RequestParam String longitude,
                             @RequestParam String address){
        FuellingStation newFuellingStation = fuellingStationService.createFuellingStation
                (new FuellingStation(providerOrBrand,
                        numberOfPumps,
                        Double.parseDouble(latitude),
                        Double.parseDouble(longitude),
                        address));
        model.addAttribute("fuellingStation", newFuellingStation);
        for(int i=1; i<=numberOfPumps;i++){
            String pumpName= "Pump#"+ i;
            pumpService.createPump(new Pump(pumpName,newFuellingStation));
        }
        return "registrationPumpsAndProducts";
    }


}
