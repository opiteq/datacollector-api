package com.alwaysup.tracker.api.model.dto;

import lombok.Data;

@Data
public class DataPointDTO {
    private double x, y;
    private String dataType;
    private double value;
    private long timestamp;
    private DeviceDTO deviceDto;
}
