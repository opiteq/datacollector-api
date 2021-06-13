package com.alwaysup.tracker.api.service.impl;

import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.model.User;
import com.alwaysup.tracker.api.model.dto.DeviceDTO;
import com.alwaysup.tracker.api.model.dto.UserDTO;
import com.alwaysup.tracker.api.repository.DeviceRepository;
import com.alwaysup.tracker.api.service.DataPointService;
import com.alwaysup.tracker.api.service.DeviceService;
import com.alwaysup.tracker.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private DataPointService dataPointService;

    @Override
    public DeviceDTO getDeviceDTOByImei(String imei) {
        return convertToDTO(getDeviceByImei(imei));
    }

    @Override
    public DeviceDTO addDeviceFromDTO(DeviceDTO deviceDto) {
        Device device = getDeviceFromDTO(deviceDto);
        User user =
                userService.getUserByUsername(
                        deviceDto.getUserDto().getUsername());
        if (device != null || user == null) {
            return null;
        } else {
            device = new Device(deviceDto.getImei());
            device.setUser(user);
            deviceRepository.save(device);
            return convertToDTO(device);
        }
    }

    @Override
    public DeviceDTO updateDeviceFromDTO(DeviceDTO deviceDto) {
        Device device = getDeviceFromDTO(deviceDto);
        if (device == null) {
            return null;
        }
        device.setName(deviceDto.getName());
        return convertToDTO(device);
    }

    @Override
    public boolean deleteDeviceFromDTO(DeviceDTO deviceDto) {
        Device device = getDeviceFromDTO(deviceDto);
        if (device == null) {
            return false;
        }
        removeDevice(device);
        return true;
    }

    @Override
    public Device getDeviceByImei(String imei) {
        Device device = deviceRepository.findByImei(imei).orElse(null);
        return device;
    }

    @Override
    public void removeDevicesByUser(User user) {
        List<Device> devicesToRemove = deviceRepository.findByUser(user);
        devicesToRemove.forEach(device -> {
            removeDevice(device);
        });
    }

    private void removeDevice(Device device) {
        dataPointService.removeDataPointsByDevice(device);
        deviceRepository.delete(device);
    }

    private Device getDeviceFromDTO(DeviceDTO deviceDto) {
        if (deviceDto == null) {
            return null;
        }
        Device device =
                deviceRepository.findByImei(deviceDto.getImei()).orElse(null);
        return device;
    }

    private DeviceDTO convertToDTO(Device device) {
        if (device == null || device.getUser() == null) {
            return null;
        }
        UserDTO userDto =
                userService
                        .getUserDtoByUsername(device.getUser().getUsername());
        DeviceDTO deviceDto = new DeviceDTO();
        deviceDto.setImei(device.getImei());
        deviceDto.setName(device.getName());
        deviceDto.setUserDto(userDto);
        return deviceDto;
    }
}
