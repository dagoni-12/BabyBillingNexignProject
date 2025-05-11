package ru.anger.CRM.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anger.CRM.entity.CrmManager;

@Repository
public interface CrmManagerRepository extends JpaRepository<CrmManager, Long> {
    boolean existsByNameAndPassword(String name, String password);
}
