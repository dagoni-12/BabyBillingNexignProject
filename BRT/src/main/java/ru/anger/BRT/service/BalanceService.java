package ru.anger.BRT.service;

import org.springframework.stereotype.Service;
import ru.anger.BRT.entity.Subscriber;
import ru.anger.BRT.repositories.SubscriberRepository;

import java.math.BigDecimal;

@Service
public class BalanceService {

    private final SubscriberRepository subscriberRepository;

    public BalanceService(SubscriberRepository repo) {
        this.subscriberRepository = repo;
    }

    public void debitBalance(String msisdn, BigDecimal amount) {
        Subscriber subscriber = subscriberRepository.findByMsisdn(msisdn)
                .orElseThrow(() -> new RuntimeException("Абонент не найден"));
        subscriber.setBalance(subscriber.getBalance().subtract(amount));
        subscriberRepository.save(subscriber);
    }
}
