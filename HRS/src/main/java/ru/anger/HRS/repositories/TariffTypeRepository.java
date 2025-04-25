package ru.anger.HRS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anger.HRS.model.TariffType;

public interface TariffTypeRepository extends JpaRepository<TariffType, Integer> {
}
