package com.alwaysup.tracker.api.service;

import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.model.User;
import com.alwaysup.tracker.api.model.dto.DeviceDTO;

public interface DeviceService {
    DeviceDTO getDeviceDTOByImei(String imei);

    DeviceDTO addDeviceFromDTO(DeviceDTO deviceDto);

    DeviceDTO updateDeviceFromDTO(DeviceDTO deviceDto);

    boolean deleteDeviceFromDTO(DeviceDTO deviceDto);

    Device getDeviceByImei(String imei);

    void removeDevicesByUser(User user);
}
