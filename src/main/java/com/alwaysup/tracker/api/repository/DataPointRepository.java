package com.alwaysup.tracker.api.repository;

import com.alwaysup.tracker.api.model.DataPoint;
import com.alwaysup.tracker.api.model.Device;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DataPointRepository extends PagingAndSortingRepository<DataPoint, Long> {
    @Query("SELECT dp FROM DataPoint dp WHERE dp.device = ?1 AND dp.dataType = ?2 AND dp.timestamp >= ?3 AND dp.timestamp <= ?4")
    List<DataPoint> findByDeviceAndDataTypeBetweenTimes(Device device, String dataType, Date startDate, Date endDate, Pageable pageable);
}