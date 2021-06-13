package com.alwaysup.tracker.api.controller;

import com.alwaysup.tracker.api.model.dto.DeviceDTO;
import com.alwaysup.tracker.api.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @GetMapping("/{imei}")
    public ResponseEntity<DeviceDTO> getDevice(
            @PathVariable("imei") String imei) {
        DeviceDTO deviceDto = deviceService.getDeviceDTOByImei(imei);
        if (deviceDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(deviceDto);
        }
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> addDevice(
            @RequestBody DeviceDTO deviceDto) {
        DeviceDTO createdDeviceDto = deviceService.addDeviceFromDTO(deviceDto);
        if (createdDeviceDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{imei}")
                    .buildAndExpand(createdDeviceDto.getImei())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdDeviceDto);
        }
    }

    @PutMapping
    public ResponseEntity<DeviceDTO> updateDevice(
            @RequestBody DeviceDTO deviceDto) {
        DeviceDTO updatedDeviceDto =
                deviceService.updateDeviceFromDTO(deviceDto);
        if (updatedDeviceDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedDeviceDto);
        }
    }

    @DeleteMapping
    public ResponseEntity<Object> removeDevice(
            @RequestBody DeviceDTO deviceDto) {
        if (deviceService.deleteDeviceFromDTO(deviceDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
