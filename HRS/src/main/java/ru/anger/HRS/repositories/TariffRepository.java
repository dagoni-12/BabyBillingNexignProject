package ru.anger.HRS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anger.HRS.model.Tariff;

public interface TariffRepository extends JpaRepository<Tariff, Integer> {
}
