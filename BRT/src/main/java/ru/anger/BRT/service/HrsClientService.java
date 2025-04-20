package ru.anger.BRT.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.anger.BRT.DTO.DataForRatingDTO;
import ru.anger.BRT.DTO.ChargeResultDTO;

@Service
public class HrsClientService {

    private final RestTemplate restTemplate;

    public HrsClientService(RestTemplateBuilder builder) {
        this.restTemplate = builder.rootUri("http://localhost:8081").build();
    }

    public ChargeResultDTO sendToHrs(DataForRatingDTO ratingDTO) {
        return restTemplate.postForObject("/api/rating/calculate", ratingDTO, ChargeResultDTO.class);
    }
}
