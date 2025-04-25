package ru.anger.HRS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.anger.HRS.model.Parameter;

public interface ParameterRepository extends JpaRepository<Parameter, Integer> {
}

