package ru.anger.BRT.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.anger.BRT.DTO.ChangeTariffRequest;
import ru.anger.BRT.DTO.CreateSubscriberRequest;
import ru.anger.BRT.DTO.SubscriberInfoResponse;
import ru.anger.BRT.entity.Subscriber;
import ru.anger.BRT.repositories.SubscriberRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    public ResponseEntity<?> changeTariff(ChangeTariffRequest dto) {
        Optional<Subscriber> subscriberOpt = repository.findByMsisdn(dto.getMsisdn());

        if (subscriberOpt.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Subscriber Not Found");
            error.put("message", "Абонент с номером " + dto.getMsisdn() + " не найден.");
            error.put("timestamp", LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        Subscriber s = subscriberOpt.get();
        s.setTariff_id(dto.getNewTariffId());
        repository.save(s);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> refillBalance(String msisdn, double amount) {
        Optional<Subscriber> subscriberOpt = repository.findByMsisdn("+" + msisdn.substring(1));
        if (subscriberOpt.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Subscriber Not Found");
            error.put("message", "Абонент с номером " + msisdn + " не найден.");
            error.put("timestamp", LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Subscriber s = subscriberOpt.get();
        s.setBalance(s.getBalance().add(BigDecimal.valueOf(amount)));
        repository.save(s);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getInfo(String msisdn) {
        Optional<Subscriber> subscriberOpt = repository.findByMsisdn("+" + msisdn.substring(1));
        if (subscriberOpt.isEmpty()) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "Subscriber Not Found");
            error.put("message", "Абонент с номером " + msisdn + " не найден.");
            error.put("timestamp", LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        Subscriber s = subscriberOpt.get();

        return ResponseEntity.ok().body(new SubscriberInfoResponse(
                s.getFullName(),
                s.getMsisdn(),
                s.getBalance(),
                s.getTariff_id())
        );
    }
}

