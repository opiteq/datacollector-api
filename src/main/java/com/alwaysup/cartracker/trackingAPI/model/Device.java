package com.alwaysup.cartracker.trackingAPI.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Device {
    @Id
    @Setter(AccessLevel.PROTECTED)
    private long id;
    @Column(unique = true)
    private String imei;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device")
    private List<DataPoint> dataPoints;

    public Device(String imei) {
        this.imei = imei;
        this.name = "My IoT Device";
        this.dataPoints = new ArrayList<>();
    }
}
