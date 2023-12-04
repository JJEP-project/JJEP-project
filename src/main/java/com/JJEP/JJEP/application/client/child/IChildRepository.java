package com.JJEP.JJEP.application.client.child;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// jpa repository is used to reduce boilerplate code
// as it provides the implementation for the standard CRUD operations
public interface IChildRepository extends JpaRepository<Child, Long> {
    // custom query is written to get all children by client id using "join"
    // client id is passed as a parameter using @Param annotation, so it can access within query
    @Query("SELECT c FROM Child c JOIN c.clients client WHERE client.id = :clientId")
    List<Child> getAllByClientId(@Param("clientId") long clientId);

    // modifying annotation is used to indicate that the query is not a select query
    // and that we want to update record rather than creating new one
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
