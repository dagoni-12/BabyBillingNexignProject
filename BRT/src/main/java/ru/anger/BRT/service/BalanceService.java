package ru.anger.BRT.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.anger.BRT.entity.Subscriber;
import ru.anger.BRT.repositories.SubscriberRepository;

import java.math.BigDecimal;

@Service
public class BalanceService {

    private final SubscriberRepository subscriberRepository;

    public BalanceService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    @Transactional
    public void applyCharge(String msisdn, BigDecimal amount) {
        Subscriber subscriber = subscriberRepository.findByMsisdn(msisdn)
                .orElseThrow(() -> new RuntimeException("Абонент не найден: " + msisdn));

        BigDecimal newBalance = subscriber.getBalance().subtract(amount);

        subscriber.setBalance(newBalance);
        subscriberRepository.save(subscriber);
    }
}
