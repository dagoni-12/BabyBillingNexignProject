package ru.anger.BRT.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anger.BRT.entity.CallInfo;

@Repository
public interface CallInfoRepository extends JpaRepository<CallInfo, Integer> {
}