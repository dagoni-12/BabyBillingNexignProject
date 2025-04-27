package ru.anger.BRT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.anger.BRT.entity.Subscriber;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByMsisdn(String msisdn);
    Boolean existsByMsisdn(String msisdn);

    @Modifying
    @Query(value = "UPDATE romashka_subscribers s SET balance = :balance WHERE msisdn = :msisdn", nativeQuery = true)
    void updateBalanceByMsisdn(String msisdn, BigDecimal balance);

    @Modifying
    @Query(value = "UPDATE romashka_subscribers s SET included_minutes = :newMinutes WHERE msisdn = :msisdn", nativeQuery = true)
    void updateIncludedMinutesByMsisdn(String msisdn, int newMinutes);
}