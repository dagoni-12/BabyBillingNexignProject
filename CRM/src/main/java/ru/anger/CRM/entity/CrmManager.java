package ru.anger.CRM.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// CrmUser.java
@Entity
@Table(name = "crm_manager")
public class CrmManager {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String password;
}


