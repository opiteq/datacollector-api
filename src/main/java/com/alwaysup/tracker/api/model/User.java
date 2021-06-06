package com.alwaysup.tracker.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @Setter(AccessLevel.PROTECTED)
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Device> userDevices;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.userDevices = new ArrayList<>();
    }
}
