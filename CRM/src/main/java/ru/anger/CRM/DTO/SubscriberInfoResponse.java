package ru.anger.CRM.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriberInfoResponse {
    private String fullName;
    private String msisdn;
    private double balance;
    private int tariffId;
}