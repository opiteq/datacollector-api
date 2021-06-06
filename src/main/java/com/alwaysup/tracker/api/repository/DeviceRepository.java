package com.alwaysup.tracker.api.repository;

import com.alwaysup.tracker.api.model.Device;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

}
