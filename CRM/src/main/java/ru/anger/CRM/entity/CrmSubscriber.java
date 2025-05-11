package ru.anger.CRM.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "crm_users")
public class CrmSubscriber {
    @Id
    @GeneratedValue
    private Long crmSubscriberId;
    private String fullName;
    private String msisdn;
    private LocalDateTime registrationDate;
    private int tariffId;
    private BigDecimal balance;
    private LocalDateTime lastPaymentDate;
    private int includedMinutes;

    public CrmSubscriber(int includedMinutes, LocalDateTime lastPaymentDate, BigDecimal balance, int tariffId, LocalDateTime registrationDate, String msisdn, String fullName) {
        this.includedMinutes = includedMinutes;
        this.lastPaymentDate = lastPaymentDate;
        this.balance = balance;
        this.tariffId = tariffId;
        this.registrationDate = registrationDate;
        this.msisdn = msisdn;
        this.fullName = fullName;
    }
}
