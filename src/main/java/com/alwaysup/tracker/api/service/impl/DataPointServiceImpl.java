package com.alwaysup.tracker.api.service.impl;

import com.alwaysup.tracker.api.model.DataPoint;
import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.model.dto.DataPointDTO;
import com.alwaysup.tracker.api.repository.DataPointRepository;
import com.alwaysup.tracker.api.service.DataPointService;
import com.alwaysup.tracker.api.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class DataPointServiceImpl implements DataPointService {

    @Autowired
    private DataPointRepository dataPointRepository;

    @Autowired
    private DeviceService deviceService;

    @Override
    public List<DataPointDTO> getAllDataPointsByDevice(
            String imei, int page, int size) {
        return getAllDataPointsByDeviceAndDataType(imei, null, page, size);
    }

    @Override
    public List<DataPointDTO> getAllDataPointsByDeviceAndDataType(
            String imei, String dataType, int page, int size) {
        List<DataPointDTO> dataPoints = new CopyOnWriteArrayList<>();
        if (imei == null) {
            return dataPoints;
        }
        Device device = deviceService.getDeviceByImei(imei);
        if (device == null) {
            return dataPoints;
        }
        if (dataType == null) {
            dataPointRepository.findByDeviceAndDataType(device, dataType,
                    PageRequest.of(page, size)).forEach(dp -> {
                dataPoints.add(convertToDTO(dp));
            });
        } else {
            dataPointRepository.findByDevice(device, PageRequest.of(page,
                    size)).forEach(dp -> {
                dataPoints.add(convertToDTO(dp));
            });
        }
        return dataPoints;
    }

    @Override
    public DataPointDTO addDataPointFromDTO(DataPointDTO dataPoint) {
        if (dataPoint == null || dataPoint.getDeviceDto() == null) {
            return null;
        }
        Device device =
                deviceService
                        .getDeviceByImei(dataPoint.getDeviceDto().getImei());
        if (device == null) {
            return null;
        }
        DataPoint createdDataPoint = new DataPoint.Builder()
                .setDataType(dataPoint.getDataType())
                .setTimestamp(dataPoint.getTimestamp())
                .setLocation(dataPoint.getX(), dataPoint.getY())
                .setDevice(device)
                .build();
        if (createdDataPoint == null ||
                createdDataPoint.getDataType() == null ||
                createdDataPoint.getDevice() == null ||
                createdDataPoint.getTimestamp() == 0) {
            return null;
        }
        return convertToDTO(dataPointRepository.save(createdDataPoint));
    }

    @Override
    public boolean addBatchDataPointsFromDTO(List<DataPointDTO> dataPoints) {
        List<DataPointDTO> addedDataPoints = new CopyOnWriteArrayList<>();
        try {
            for (DataPointDTO dp : dataPoints) {
                addedDataPoints.add(addDataPointFromDTO(dp));
            }
        } catch (Exception e) {
            for (DataPointDTO dp : dataPoints) {
                removeDataPointFromDTO(dp);
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean removeDataPointFromDTO(DataPointDTO dataPointDto) {
        DataPoint dp = getDataPointFromDTO(dataPointDto);
        if (dp == null) {
            return false;
        }
        dataPointRepository.delete(dp);
        return true;
    }

    @Override
    public void removeDataPointsByDevice(Device device) {
        dataPointRepository.deleteByDevice(device);
    }

    private DataPoint getDataPointFromDTO(DataPointDTO dpDto) {
        if (dpDto == null || dpDto.getDataType() == null ||
                dpDto.getDeviceDto() == null ||
                dpDto.getDeviceDto().getImei() == null) {
            return null;
        }
        Device device =
                deviceService.getDeviceByImei(dpDto.getDeviceDto().getImei());
        return dataPointRepository.findByDeviceAndDataTypeAndTimestamp(device
                , dpDto.getDataType(), dpDto.getTimestamp()).orElse(null);
    }

    private DataPointDTO convertToDTO(DataPoint dp) {
        if (dp == null) {
            return null;
        }
        DataPointDTO dpDto = new DataPointDTO();
        dpDto.setX(dp.getX());
        dpDto.setY(dp.getY());
        dpDto.setDataType(dp.getDataType());
        dpDto.setValue(dp.getValue());
        dpDto.setTimestamp(dp.getTimestamp());
        return dpDto;
    }
}
