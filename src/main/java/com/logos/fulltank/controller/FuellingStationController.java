package com.logos.fulltank.controller;

import com.logos.fulltank.entity.*;
import com.logos.fulltank.exception.FuellingStationNotFoundException;
import com.logos.fulltank.exception.ProductNotFoundException;
import com.logos.fulltank.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/fuellingStation")
@Slf4j(topic = "FuellingStation Controller")
public class FuellingStationController {

    private final FuellingStationService fuellingStationService;
    private final PumpService pumpService;
    private final UserService userService;
    private final ReceiptService receiptService;
    private final ProductService productService;


    public FuellingStationController(FuellingStationService fuellingStationService,
                                     PumpService pumpService,
                                     UserService userService,
                                     ReceiptService receiptService,
                                     ProductService productService) {
        this.fuellingStationService = fuellingStationService;
        this.pumpService = pumpService;
        this.userService = userService;
        this.receiptService = receiptService;
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
                              @RequestParam int radius, Authentication authentication) {
        User user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        List<FuellingStation> listFuellingStations = fuellingStationService.getListFuellingStations(latitude, longitude, radius);
        listFuellingStations.forEach(System.out::println);
        model.addAttribute("listFuellingStations", listFuellingStations);
        return "foundFuellingStationPage";
    }

    @GetMapping("/{id}/buy")
    public String buyFuel(Model model, @RequestParam double amount, @RequestParam int productId, @PathVariable int id, Authentication authentication) throws FuellingStationNotFoundException, ProductNotFoundException {
        User user = userService.getUserByEmail(authentication.getName());
        model.addAttribute("user", user);
        FuellingStation fuellingStation = fuellingStationService.getById(id);
        model.addAttribute("fuellingStation", fuellingStation);
        receiptService.createReceipt(new Receipt(new Date(System.currentTimeMillis()),
                productService.getById(productId).getNameOfFuel(),
                productService.getById(productId).getPricePerLiterInHrn(),
                amount,
                productService.getById(productId).getPricePerLiterInHrn() * amount,
                user
        ));
        return "receipt";
    }
}
