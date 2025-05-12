package ru.anger.CRM.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.anger.CRM.DTO.*;
import ru.anger.CRM.service.CrmService;

@RestController
@RequestMapping("/api/crm")
@RequiredArgsConstructor
public class CrmController {

    private final CrmService crmService;

    @PostMapping("/{manager}/{password}/register")
    public ResponseEntity<?> register(@PathVariable String manager,
                                         @PathVariable String password,
                                         @RequestBody CreateSubscriberRequest request) {
        if (!crmService.isValidManager(manager, password)) return ResponseEntity.status(403).build();
        crmService.registerSubscriber(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{manager}/{password}/change-tariff")
    public ResponseEntity<?> changeTariff(@PathVariable String manager,
                                             @PathVariable String password,
                                             @RequestBody ChangeTariffRequest request) {
        if (!crmService.isValidManager(manager, password)) return ResponseEntity.status(403).build();
        crmService.changeTariff(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{manager}/{password}/refill")
    public ResponseEntity<Void> refill(@PathVariable String manager,
                                       @PathVariable String password,
                                       @RequestParam String msisdn,
                                       @RequestParam double amount) {
        if (!crmService.isValidManager(manager, password)) return ResponseEntity.status(403).build();
        crmService.refillBalance(msisdn, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{manager}/{password}/info")
    public ResponseEntity<SubscriberInfoResponse> getInfo(@PathVariable String manager,
                                                          @PathVariable String password,
                                                          @RequestParam String msisdn) {
        if (!crmService.isValidManager(manager, password)) return ResponseEntity.status(403).build();
        SubscriberInfoResponse s = crmService.getInfo(msisdn);
        return ResponseEntity.ok(s);
    }

    @PostMapping("/{phone}/refill")
    public ResponseEntity<Void> userRefill(@PathVariable String phone,
                                           @RequestParam double amount) {
        crmService.refillBalance(phone, amount);
        return ResponseEntity.ok().build();
    }
}
