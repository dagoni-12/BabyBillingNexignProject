package ru.anger.BRT.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubscriberRequest {
    private String fullName;
    private String msisdn;
    private Integer tariffId;
}

