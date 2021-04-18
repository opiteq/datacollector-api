package com.alwaysup.cartracker.trackingAPI.repository;

import com.alwaysup.cartracker.trackingAPI.model.DataPoint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPointRepository extends CrudRepository<DataPoint, Long> {

}