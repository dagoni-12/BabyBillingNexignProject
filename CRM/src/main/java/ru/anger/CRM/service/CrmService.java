package ru.anger.CRM.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.anger.CRM.DTO.*;
import ru.anger.CRM.repository.CrmManagerRepository;

// service/CrmService.java
// service/CrmService.java
@Service
@RequiredArgsConstructor
public class CrmService {

    private final BrtClient brtClient;
    private final CrmManagerRepository managerRepo;

    public boolean isValidManager(String name, String password) {
        return managerRepo.existsByNameAndPassword(name, password);
    }

    public void registerSubscriber(CreateSubscriberRequest dto) {
        brtClient.registerSubscriber(dto);
    }

    public void changeTariff(ChangeTariffRequest dto) {
        brtClient.changeTariff(dto);
    }

    public void refillBalance(String msisdn, double amount) {
        brtClient.refillBalance(msisdn, amount);
    }

    public SubscriberInfoResponse getInfo(String msisdn) {
        return brtClient.getInfo(msisdn);
    }
}
