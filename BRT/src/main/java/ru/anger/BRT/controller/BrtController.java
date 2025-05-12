package ru.anger.BRT.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anger.BRT.DTO.ChangeTariffRequest;
import ru.anger.BRT.DTO.CreateSubscriberRequest;
import ru.anger.BRT.service.BrtService;

@RestController
@RequestMapping("/api/brt")
@RequiredArgsConstructor
public class BrtController {

    private final BrtService service;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody CreateSubscriberRequest dto) {
        service.registerSubscriber(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-tariff")
    public ResponseEntity<?> changeTariff(@RequestBody ChangeTariffRequest dto) {
        return service.changeTariff(dto);
    }

    @PostMapping("/refill")
    public ResponseEntity<?> refill(@RequestParam String msisdn,
                                    @RequestParam double amount) {
        return service.refillBalance(msisdn, amount);
    }

    @GetMapping("/info")
    public ResponseEntity<?> info(@RequestParam String msisdn) {
        return service.getInfo(msisdn);
    }
}

