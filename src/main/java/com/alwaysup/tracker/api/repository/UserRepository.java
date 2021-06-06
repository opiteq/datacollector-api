package com.alwaysup.tracker.api.repository;

import com.alwaysup.tracker.api.model.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    List<User> findAll(PageRequest pageRequest);
    @Query("SELECT u FROM User u WHERE u.email LIKE :1")
    User findByEmail(String email);
}
