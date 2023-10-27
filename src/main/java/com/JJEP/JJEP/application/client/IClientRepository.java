package com.JJEP.JJEP.application.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IClientRepository extends JpaRepository<Client, Long> {
    @Modifying
    @Query("""
        UPDATE\s
            Client c\s
        SET\s
            c.familyName = :#{#client.familyName},\s
            c.forename = :#{#client.forename},\s
            c.maritalStatus = :#{#client.maritalStatus},\s
            c.dateOfBirth = :#{#client.dateOfBirth},\s
            c.isUkIht = :#{#client.isUkIht},\s
            c.isSpouseNrb = :#{#client.isSpouseNrb},\s
            c.isLpa = :#{#client.isLpa},\s
            c.primaryProperty = :#{#client.primaryProperty},\s
            c.ukHolidayHome = :#{#client.ukHolidayHome},\s
            c.btlProperty = :#{#client.btlProperty},\s
            c.foreignProperty = :#{#client.foreignProperty},\s
            c.foreignWill = :#{#client.foreignWill},\s
            c.brpAssets = :#{#client.brpAssets},\s
            c.nonBrpAssets = :#{#client.nonBrpAssets},\s
            c.natureOfBusiness = :#{#client.natureOfBusiness},\s
            c.investments = :#{#client.investments},\s
            c.savingsCash = :#{#client.savingsCash},\s
            c.personalLifeCover = :#{#client.personalLifeCover},\s
            c.trust = :#{#client.trust},\s
            c.deathBenefitTrust = :#{#client.deathBenefitTrust},\s
            c.deathInService = :#{#client.deathInService},\s
            c.pension = :#{#client.pension}
        WHERE\s
            c.id = :id
    """)
    void updateById(@Param("id") Long id, @Param("client") Client client);

}
