package ru.anger.BRT.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ru.anger.BRT.DTO.ChargeResultDTO;
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
    public void applyCharge(String msisdn, ChargeResultDTO result) {
        Subscriber subscriber = subscriberRepository.findByMsisdn(msisdn)
                .orElseThrow(() -> new RuntimeException("Абонент не найден: " + msisdn));

        BigDecimal newBalance = subscriber.getBalance().subtract(result.getChargeAmount());

        System.out.println("new balance " + newBalance);
        System.out.println("msisdn " + msisdn);
        subscriber.setBalance(newBalance);
        subscriberRepository.updateBalanceByMsisdn(subscriber.getMsisdn(), newBalance);

        int newMinutes = Math.max(0, subscriber.getIncludedMinutes() - result.getMinutesSpent());
        subscriber.setIncludedMinutes(Math.max(0, newMinutes));
        subscriberRepository.updateIncludedMinutesByMsisdn(subscriber.getMsisdn(), newMinutes);
        //subscriberRepository.save(subscriber);
    }
}
