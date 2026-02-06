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

    @Column(nullable = false)
    private Long user_id;

    public VerificationToken(){}

    public VerificationToken(String token, LocalDateTime localDateTime, Long user_id){
        this.token = token;
        this.localDateTime = localDateTime;
        this.user_id = user_id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String getToken() {
        return token;
    }

    public Long getUser_id(){
        return user_id;
    }
}
