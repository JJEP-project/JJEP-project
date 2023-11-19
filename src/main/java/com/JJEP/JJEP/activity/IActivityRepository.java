package com.JJEP.JJEP.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// jpa repository is used to reduce boilerplate code
// as it provides the implementation for the standard CRUD operations
public interface IActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByOrderByActivityDateDesc();
    List<Activity> findAllByOrderByActivityDateAsc();
}
