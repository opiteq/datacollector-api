package com.alwaysup.tracker.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PACKAGE)
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
