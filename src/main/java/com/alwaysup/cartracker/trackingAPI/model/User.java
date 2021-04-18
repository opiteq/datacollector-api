package com.alwaysup.cartracker.trackingAPI.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    private String username;
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Device> userDevices;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.userDevices = new ArrayList<>();
    }
}
