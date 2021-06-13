package com.alwaysup.tracker.api.repository;

import com.alwaysup.tracker.api.model.Device;
import com.alwaysup.tracker.api.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends PagingAndSortingRepository<Device,
        Long> {
    List<Device> findByUser(User user);

    Optional<Device> findByImei(String imei);
}
