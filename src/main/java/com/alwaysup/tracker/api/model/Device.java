package com.alwaysup.tracker.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PACKAGE)
    private long id;
    @Column(unique = true)
    private String imei;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Device(String imei) {
        this.imei = imei;
        this.name = "My IoT Device";
    }
}
