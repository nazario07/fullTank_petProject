package com.logos.fulltank.controller;

import com.logos.fulltank.entity.FuellingStation;
import com.logos.fulltank.entity.Pump;
import com.logos.fulltank.entity.User;
import com.logos.fulltank.exception.FuellingStationNotFoundException;
import com.logos.fulltank.exception.UserAlreadyExistException;
import com.logos.fulltank.exception.UserNotFoundException;
import com.logos.fulltank.service.FuellingStationService;
import com.logos.fulltank.service.ProductService;
import com.logos.fulltank.service.PumpService;
import com.logos.fulltank.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fuellingStation")
@Slf4j(topic = "FuellingStation Controller")
public class FuellingStationController {

    private final FuellingStationService fuellingStationService;
    private final PumpService pumpService;
    private final UserService userService;
    private final ProductService productService;

    public FuellingStationController(FuellingStationService fuellingStationService,
                                     PumpService pumpService,
                                     UserService userService,
                                     ProductService productService) {
        this.fuellingStationService = fuellingStationService;
        this.pumpService = pumpService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/registration")
    public String toRegister() {
        return "registrationFS";
    }

    @PostMapping("/registration")
    public String createFuellingStation(Model model,
                                        @RequestParam String providerOrBrand,
                                        @RequestParam int numberOfPumps,
                                        @RequestParam String latitude,
                                        @RequestParam String longitude,
                                        @RequestParam String address) {
        FuellingStation newFuellingStation = fuellingStationService.createFuellingStation
                (new FuellingStation(providerOrBrand,
                        numberOfPumps,
                        Double.parseDouble(latitude),
                        Double.parseDouble(longitude),
                        address));
        model.addAttribute("fuellingStation", newFuellingStation);
        for (int i = 1; i <= numberOfPumps; i++) {
            String pumpName = "Pump#" + i;
            pumpService.createPump(new Pump(pumpName, newFuellingStation));
        }
        return "registrationPumpsAndProducts";
    }

    @GetMapping("/{id}")
    public String getById(Model model, @PathVariable int id) throws FuellingStationNotFoundException {
        FuellingStation byId = fuellingStationService.getById(id);
        model.addAttribute("fuellingStation", byId);
        return "fuellingStation";
    }
    @GetMapping("/stations")
    public String getStations(Model model, @RequestParam double latitude, @RequestParam double longitude,
                              @RequestParam int radius) throws UserNotFoundException {
        User userById = userService.getUserById(1);
        model.addAttribute("user", userById);
        System.out.println(latitude + " " + longitude);
        List<FuellingStation> listFuellingStations = new ArrayList<>();
        if (radius == 0) {
            FuellingStation closestStation = fuellingStationService.getClosestStation(latitude, longitude);
            listFuellingStations.add(closestStation);
        } else {
            List<FuellingStation> listFuellingStationsInRadius = fuellingStationService.getListFuellingStationsInRadius
                    (latitude, longitude, radius);
            listFuellingStations.addAll(listFuellingStationsInRadius);
        }
        listFuellingStations.forEach(System.out::println);
        model.addAttribute("listFuellingStations", listFuellingStations);
        return "foundFuellingStationPage";
    }
}
