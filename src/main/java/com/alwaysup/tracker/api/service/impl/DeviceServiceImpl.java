package com.alwaysup.tracker.api.service.impl;

import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.model.User;
import com.alwaysup.tracker.api.repository.DeviceRepository;
import com.alwaysup.tracker.api.repository.UserRepository;
import com.alwaysup.tracker.api.service.DeviceService;
import com.alwaysup.tracker.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Set;

public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserService userService;

    public Device getDeviceById(long id) {
        Device device = deviceRepository.findById(id).orElse(null);
        return device;
    }

    public Device addDevice(String email, Device device) {
        User user = userService.getUser(email);
        if (user == null || device == null) {
            return null;
        }
        Set<Device> userDevices = user.getUserDevices();
        userDevices.add(device);
        return null;
    }

    public Device deleteDevice(Device device) {
        if (device != null) {
            User deviceUser = device.getUser();
            deviceRepository.delete(device);
            userService.removeDevice(deviceUser, device);
        }
        return device;
    }

    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device addDevice(User user, Device device) {
        device.setUser(user);
        userService.addDevice(user, device);
        return deviceRepository.save(device);
    }

    public Device removeDevice(User user, Device device) {
        device.setUser(user);
        userService.removeDevice(user, device);
        deviceRepository.delete(device);
        return device;
    }
}
