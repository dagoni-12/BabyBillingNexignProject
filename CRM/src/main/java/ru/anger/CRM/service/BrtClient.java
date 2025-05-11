package ru.anger.CRM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.anger.CRM.DTO.ChangeTariffRequest;
import ru.anger.CRM.DTO.CreateSubscriberRequest;
import ru.anger.CRM.DTO.SubscriberInfoResponse;

@Service
public class BrtClient {

    @Autowired
    private RestClient restClient;

    public ResponseEntity<Void> registerSubscriber(CreateSubscriberRequest dto) {
        return restClient.post()
                .uri("/register")
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }

    public void changeTariff(ChangeTariffRequest dto) {

        restClient.post()
                .uri("/change-tariff")
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }

    public void refillBalance(String msisdn, double amount) {
        restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/refill")
                        .queryParam("msisdn", msisdn)
                        .queryParam("amount", amount)
                        .build())
                .retrieve()
                .toBodilessEntity();
    }

    public SubscriberInfoResponse getInfo(String msisdn) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/info")
                        .queryParam("msisdn", msisdn)
                        .build())
                .retrieve()
                .body(SubscriberInfoResponse.class);
    }
}
