package com.JJEP.JJEP.activity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findAllByOrderByActivityDateDesc();
    List<Activity> findAllByOrderByActivityDateAsc();
}
