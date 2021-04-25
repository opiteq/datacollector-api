package com.alwaysup.cartracker.trackingAPI.repository;

import com.alwaysup.cartracker.trackingAPI.model.Device;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

}
