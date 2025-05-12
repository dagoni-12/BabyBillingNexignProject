package ru.anger.CRM.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import ru.anger.CRM.DTO.ChangeTariffRequest;
import ru.anger.CRM.DTO.CreateSubscriberRequest;
import ru.anger.CRM.DTO.SubscriberInfoResponse;
import ru.anger.CRM.exceptions.BrtException;

@Service
public class BrtClient {

    @Autowired
    private RestClient restClient;

    public void registerSubscriber(CreateSubscriberRequest dto) {
            restClient.post()
                    .uri("/change-tariff")
                    .body(dto)
                    .retrieve()
                    .toBodilessEntity();
    }

    public void changeTariff(ChangeTariffRequest dto) {
        try {
            restClient.post()
                    .uri("/change-tariff")
                    .body(dto)
                    .retrieve()
                    .toBodilessEntity();
        } catch (HttpClientErrorException e) {
            throw new BrtException((HttpStatus) e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    public void refillBalance(String msisdn, double amount) {
        try {
            restClient.post()
                    .uri(uriBuilder -> uriBuilder
                            .path("/refill")
                            .queryParam("msisdn", msisdn)
                            .queryParam("amount", amount)
                            .build())
                    .retrieve()
                    .toBodilessEntity();
        }  catch (HttpClientErrorException e) {
            throw new BrtException((HttpStatus) e.getStatusCode(), e.getResponseBodyAsString());
        }
    }

    public SubscriberInfoResponse getInfo(String msisdn) {
        try {
            return restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/info")
                            .queryParam("msisdn", msisdn)
                            .build())
                    .retrieve()
                    .body(SubscriberInfoResponse.class);
        }   catch (HttpClientErrorException e) {
            throw new BrtException((HttpStatus) e.getStatusCode(), e.getResponseBodyAsString());
        }
    }
}
