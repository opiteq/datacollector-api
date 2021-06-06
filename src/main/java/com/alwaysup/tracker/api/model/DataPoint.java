package com.alwaysup.tracker.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JoinColumn(name = "device_imei")
    private Device device;
    /**
     * Location/Temperature/Humidity
     * 000 -> 0 (none)
     * 110 -> 6 (Location & Temperature)
     */
    private String dataType;
    private float x, y;
    private float value;
    @JsonFormat(pattern = "yyyyMMddTHHmmssX")
    private Date timestamp;

    public DataPoint(Builder builder) {
        this.x = builder.x;
        this.y = builder.y;
        this.value = builder.value;
        this.device = builder.device;
        this.dataType = builder.dataType;
        this.timestamp = new Date();
    }

    public static class Builder {
        private Device device;
        private String dataType;
        private float x, y;
        private float value;

        private Builder() {
        }

        public Builder setDevice(Device device) {
            this.device = device;
            return this;
        }

        public Builder setDataType(String dataType) {
            this.dataType = dataType;
            return this;
        }

        public Builder setLocation(float x, float y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder setValue(float value) {
            this.value = value;
            return this;
        }

        public DataPoint build() {
            return new DataPoint(this);
        }
    }
}
