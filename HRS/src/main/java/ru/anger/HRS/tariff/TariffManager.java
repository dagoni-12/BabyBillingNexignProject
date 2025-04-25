package ru.anger.HRS.tariff;

import org.springframework.stereotype.Component;
import ru.anger.HRS.DTO.DataForRatingDTO;
import ru.anger.HRS.model.Tariff;
import ru.anger.HRS.repositories.TariffRepository;

@Component
public class TariffManager {

    private final TariffRepository tariffRepository;

    public TariffManager(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public TariffData loadTariffData(DataForRatingDTO dto) {
        Tariff tariff = tariffRepository.findById(dto.getTariffId())
                .orElseThrow(() -> new RuntimeException("Tariff not found"));

        return new TariffData(tariff); // вспомогательный объект с нужными полями
    }
}

