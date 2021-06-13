package com.alwaysup.tracker.api.service;

import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.model.dto.DataPointDTO;

import java.util.List;

public interface DataPointService {
    List<DataPointDTO> getAllDataPointsByDevice(
            String imei, int page, int size);

    List<DataPointDTO> getAllDataPointsByDeviceAndDataType(
            String imei, String dataType, int page, int size);

    DataPointDTO addDataPointFromDTO(DataPointDTO dataPointDto);

    boolean addBatchDataPointsFromDTO(List<DataPointDTO> dataPoints);

    boolean removeDataPointFromDTO(DataPointDTO dataPoint);

    void removeDataPointsByDevice(Device device);
}
