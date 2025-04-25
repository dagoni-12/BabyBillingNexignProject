package ru.anger.BRT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anger.BRT.entity.Subscriber;

import java.util.Optional;

@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Optional<Subscriber> findByMsisdn(String msisdn);
    Boolean existsByMsisdn(String msisdn);
}