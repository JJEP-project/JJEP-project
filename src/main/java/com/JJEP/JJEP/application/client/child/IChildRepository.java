package com.JJEP.JJEP.application.client.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IChildRepository extends JpaRepository<Child, Long> {
    @Query("SELECT c FROM Child c JOIN c.clients client WHERE client.id = :clientId")
    List<Child> getAllByClientId(@Param("clientId") long clientId);

    @Modifying
    @Query("""
    UPDATE\s
        Child c\s
    SET\s
        c.dateOfBirth = :#{#child.dateOfBirth},\s
        c.name = :#{#child.name}\s
    WHERE\s
        c.id = :id
    """)
    void updateById(@Param("id") Long id, @Param("child") Child child);
}
