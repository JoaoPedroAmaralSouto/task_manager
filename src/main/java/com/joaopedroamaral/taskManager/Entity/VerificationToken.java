package com.joaopedroamaral.taskManager.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class VerificationToken {

    @Id
    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private LocalDateTime localDateTime;

    public VerificationToken(){}

    public VerificationToken(String token, LocalDateTime localDateTime){
        this.token = token;
        this.localDateTime = localDateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getToken() {
        return token;
    }
}
