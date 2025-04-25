package ru.anger.HRS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anger.HRS.model.TariffParameter;

public interface TariffParameterRepository extends JpaRepository<TariffParameter, Integer> {
}
