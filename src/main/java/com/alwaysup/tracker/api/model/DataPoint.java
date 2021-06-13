package com.alwaysup.tracker.api.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;


@Entity
@Data
public class DataPoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.PACKAGE)
    private long id;
    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
    /**
     * temp(Temperature) / humd(Humidity)
     */
    private String dataType;
    private double x, y;
    private double value;
    private long timestamp;

    private DataPoint() {

    }

    public DataPoint(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.value = builder.value;
        this.device = builder.device;
        this.dataType = builder.dataType;
        this.timestamp = (builder.timestamp == 0) ?
                new Date().toInstant().toEpochMilli() : builder.timestamp;
    }

    public static class Builder {
        public long timestamp;
        private Device device;
        private String dataType;
        private double x, y;
        private double value;

        public Builder() {
        }

        public Builder setDevice(Device device) {
            this.device = device;
            return this;
        }

        public Builder setDataType(String dataType) {
            this.dataType = dataType;
            return this;
        }

        public Builder setLocation(double x, double y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder setValue(double value) {
            this.value = value;
            return this;
        }

        public Builder setTimestamp(long epoch) {
            this.timestamp = epoch;
            return this;
        }

        public DataPoint build() {
            return new DataPoint(this);
        }
    }
}
