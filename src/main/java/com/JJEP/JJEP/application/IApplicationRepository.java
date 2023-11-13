package com.JJEP.JJEP.application;

import com.JJEP.JJEP.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IApplicationRepository extends JpaRepository<Application, Long> {

    @Modifying
    @Query("""
            UPDATE\s
                Application a\s
            SET\s
                a.isBloodProtection = :#{#application.isBloodProtection},\s
                a.isGenerationIht = :#{#application.isGenerationIht},\s
                a.notes = :#{#application.notes},
                a.primaryProperty = :#{#application.primaryProperty},
                a.ukHolidayHome = :#{#application.ukHolidayHome},
                a.btlProperty = :#{#application.btlProperty},
                a.foreignProperty = :#{#application.foreignProperty},
                a.foreignWill = :#{#application.foreignWill},
                a.investment = :#{#application.investment},
                a.savingsCash = :#{#application.savingsCash},
                a.personalLifeCover = :#{#application.personalLifeCover},
                a.trust = :#{#application.trust}
            WHERE\s
                a.id = :id
            """)
    void updateById(@Param("id") Long id, @Param("application") Application application);


    @Query("SELECT COUNT(a) FROM Application a WHERE date_trunc('week', a.createdAt) = date_trunc('week', current_date)")
    long countNewApplicationsLastWeek();

    List<Application> findTop5ByOrderByCreatedAtDesc();

    List<Application> findAllByOrderByCreatedAtDesc();
    List<Application> findAllByOrderByCreatedAtAsc();
    long count();

}
