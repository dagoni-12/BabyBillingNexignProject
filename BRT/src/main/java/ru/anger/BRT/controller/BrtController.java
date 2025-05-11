package ru.anger.BRT.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anger.BRT.DTO.ChangeTariffRequest;
import ru.anger.BRT.DTO.CreateSubscriberRequest;
import ru.anger.BRT.DTO.SubscriberInfoResponse;
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
    public ResponseEntity<Void> changeTariff(@RequestBody ChangeTariffRequest dto) {
        service.changeTariff(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refill")
    public ResponseEntity<Void> refill(@RequestParam String msisdn,
                                       @RequestParam double amount) {
        service.refillBalance(msisdn, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/info")
    public ResponseEntity<SubscriberInfoResponse> info(@RequestParam String msisdn) {
        return ResponseEntity.ok(service.getInfo(msisdn));
    }
}

