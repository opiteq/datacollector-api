package com.alwaysup.tracker.api.repository;

import com.alwaysup.tracker.api.model.DataPoint;
import com.alwaysup.tracker.api.model.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataPointRepository
        extends PagingAndSortingRepository<DataPoint, Long> {
    List<DataPoint> findByDeviceAndDataType(Device device,
                                            String dataType,
                                            Pageable pageable);

    List<DataPoint> findByDevice(Device device, Pageable pageable);

    Optional<DataPoint> findByDeviceAndDataTypeAndTimestamp(Device device,
                                                            String dataType,
                                                            long timestamp);

    void deleteByDevice(Device device);
}