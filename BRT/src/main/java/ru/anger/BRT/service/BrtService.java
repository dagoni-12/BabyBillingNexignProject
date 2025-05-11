package ru.anger.BRT.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.anger.BRT.DTO.ChangeTariffRequest;
import ru.anger.BRT.DTO.CreateSubscriberRequest;
import ru.anger.BRT.DTO.SubscriberInfoResponse;
import ru.anger.BRT.entity.Subscriber;
import ru.anger.BRT.repositories.SubscriberRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BrtService {

    private final SubscriberRepository repository;
    @Value("${default_balance}")
    private int balance;

    public void registerSubscriber(CreateSubscriberRequest dto) {
        Subscriber subscriber = new Subscriber();
        subscriber.setFullName(dto.getFullName());
        subscriber.setMsisdn(dto.getMsisdn());
        subscriber.setTariff_id(dto.getTariffId());
        subscriber.setBalance(BigDecimal.valueOf(balance));
        LocalDateTime now = LocalDateTime.now();
        subscriber.setRegistration_date(now);
        subscriber.setLast_sync(now);
        subscriber.setLastPaymentDate(LocalDate.from(now));
        repository.save(subscriber);
    }

    public void changeTariff(ChangeTariffRequest dto) {
        Subscriber s = repository.findByMsisdn(dto.getMsisdn())
                .orElseThrow(() -> new RuntimeException("Абонент не найден"));
        s.setTariff_id(dto.getNewTariffId());
        repository.save(s);
    }

    public void refillBalance(String msisdn, double amount) {
        Subscriber s = repository.findByMsisdn(msisdn)
                .orElseThrow(() -> new RuntimeException("Абонент не найден"));
        s.setBalance(s.getBalance().add(BigDecimal.valueOf(amount)));
        repository.save(s);
    }

    public SubscriberInfoResponse getInfo(String msisdn) {
        Subscriber s = repository.findByMsisdn(msisdn)
                .orElseThrow(() -> new RuntimeException("Абонент не найден"));

        return new SubscriberInfoResponse(
                s.getFullName(),
                s.getMsisdn(),
                s.getBalance(),
                s.getTariff_id()
        );
    }
}

