package com.alwaysup.cartracker.trackingAPI.repository;

import com.alwaysup.cartracker.trackingAPI.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
