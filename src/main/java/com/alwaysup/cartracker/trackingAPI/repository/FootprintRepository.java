package com.alwaysup.cartracker.trackingAPI.repository;


import org.springframework.data.repository.CrudRepository;

import com.alwaysup.cartracker.trackingAPI.model.Footprint;

public interface FootprintRepository extends CrudRepository<Footprint, Integer> {
    public Footprint[] findByuseridLike(String userid);
}