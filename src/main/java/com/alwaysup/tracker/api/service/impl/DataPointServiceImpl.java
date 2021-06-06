package com.alwaysup.tracker.api.service.impl;

import com.alwaysup.tracker.api.model.DataPoint;
import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.repository.DataPointRepository;
import com.alwaysup.tracker.api.service.DataPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.alwaysup.tracker.api.util.Constants.INVALID_UPDATE_DATA_DOES_NOT_EXIST;

public class DataPointServiceImpl implements DataPointService {

    @Autowired
    private DataPointRepository dataPointRepository;

    //    @Override
    public List<DataPoint> getAllDataPointsByDeviceAndDataTypeBetweenTimes(
            Device device, String dataType, String startTimestamp, String endTimestamp, int page, int size) {
        DateFormat format = new SimpleDateFormat("yyyyMMddTHHmmssX", Locale.ENGLISH);
        try {
            Date startDate = format.parse(startTimestamp);
            Date endDate = format.parse(endTimestamp);
            List<DataPoint> dataPoints = dataPointRepository.findByDeviceAndDataTypeBetweenTimes(device, dataType, startDate, endDate, PageRequest.of(page, size));
            return dataPoints;
        } catch (ParseException e) {
            return null;
        }
    }

    //    @Override
    public DataPoint addDataPoint(DataPoint dataPoint) {
        dataPointRepository.save(dataPoint);
        return dataPoint;
    }

//        @Override
    public DataPoint updateDataPoint(DataPoint dataPoint) {
        if (dataPointRepository.findById(dataPoint.getId()) == null) {
            throw new IllegalArgumentException(INVALID_UPDATE_DATA_DOES_NOT_EXIST);
        }
        dataPointRepository.save(dataPoint);
        return dataPoint;
    }

    //    @Override
    public DataPoint deleteDataPoint(DataPoint dataPoint) {
        if (dataPointRepository.findById(dataPoint.getId()) == null) {
            throw new IllegalArgumentException(INVALID_UPDATE_DATA_DOES_NOT_EXIST);
        }
        dataPointRepository.delete(dataPoint);
        return dataPoint;
    }
}
