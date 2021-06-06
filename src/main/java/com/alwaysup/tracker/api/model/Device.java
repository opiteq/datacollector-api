package com.alwaysup.tracker.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "device", fetch = FetchType.LAZY)
    private List<DataPoint> dataPoints;

    public Device(String imei) {
        this.imei = imei;
        this.name = "My IoT Device";
        this.dataPoints = new ArrayList<>();
    }
}
