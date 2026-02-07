package com.joaopedroamaral.taskManager.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Tasks")
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    Status status;

    @Column(nullable = false)
    Long userID;

    protected Task(){
    }

    public Task(String title, String description, Status status){
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUserID() {
        return userID;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Status getStatus() {
        return status;
    }
}
