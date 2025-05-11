package ru.anger.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anger.CRM.entity.CrmSubscriber;

import java.util.Optional;

public interface CrmSubscriberRepository extends JpaRepository<CrmSubscriber, Long>{
    public Optional<CrmSubscriber> findByMsisdn(String msisdn);
}
