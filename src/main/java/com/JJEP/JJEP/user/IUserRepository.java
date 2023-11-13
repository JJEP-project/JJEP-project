package com.JJEP.JJEP.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.username = :#{#user.username}, u.fullName = :#{#user.fullName}, u.email = :#{#user.email} WHERE u.id = :id")
    void updateById(@Param("id") Long id, @Param("user") User user);

    @Query("SELECT COUNT(u) FROM User u WHERE date_trunc('week', u.createdAt) = date_trunc('week', current_date)")
    long countNewUsersLastWeek();

    List<User> findTop5ByOrderByCreatedAtDesc();

    List<User> findAllByOrderByCreatedAtDesc();
    List<User> findAllByOrderByCreatedAtAsc();

    long count();
}
