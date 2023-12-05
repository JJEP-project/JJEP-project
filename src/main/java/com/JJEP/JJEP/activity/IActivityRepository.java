package com.JJEP.JJEP.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

// jpa repository is used to reduce boilerplate code
// as it provides the implementation for the standard CRUD operations
public interface IActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByOrderByActivityDateDesc();
    List<Activity> findAllByOrderByActivityDateAsc();

    List<Activity> findTop5ByOrderByActivityDateDesc();

    // transactional annotation is used to ensure consistency of the database
    @Transactional
    void deleteByUserId(long userId);

    @Transactional
    void deleteByActivityDateBefore(LocalDateTime date);
}
