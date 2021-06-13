package com.alwaysup.tracker.api.model.dto;

import lombok.Data;

@Data
public class DeviceDTO {
    private String imei;
    private String name;
    private UserDTO userDto;
}
