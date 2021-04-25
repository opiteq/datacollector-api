package com.alwaysup.cartracker.trackingAPI.repository;

import com.alwaysup.cartracker.trackingAPI.model.DataPoint;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPointRepository extends PagingAndSortingRepository<DataPoint, Long> {

}