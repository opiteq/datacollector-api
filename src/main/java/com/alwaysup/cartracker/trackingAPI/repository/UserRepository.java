package com.alwaysup.cartracker.trackingAPI.repository;

import com.alwaysup.cartracker.trackingAPI.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
