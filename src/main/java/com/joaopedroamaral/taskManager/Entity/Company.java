package com.joaopedroamaral.taskManager.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Company(){}

    public Company(String companyName, LocalDateTime createdAt){
        this.companyName = companyName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName(){
        return companyName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
