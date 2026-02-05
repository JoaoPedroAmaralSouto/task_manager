package com.joaopedroamaral.taskManager.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean enabled = false;

    @Column(nullable = false)
    private boolean admin = false;

    protected User() {} // JPA

    public User(String username, String email, String password, boolean admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public boolean getEnabled(){
        return enabled;
    }

    public boolean getAdmin(){
        return admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
}
