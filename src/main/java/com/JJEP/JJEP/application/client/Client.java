package com.JJEP.JJEP.application.client;

import com.JJEP.JJEP.application.Application;
import com.JJEP.JJEP.application.client.children.Child;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="application_clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "application_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Application application;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "forename")
    private String forename;

    @Column(name = "marital_status")
    private String maritalStatus;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "is_uk_iht")
    private boolean isUkIht;

    @Column(name = "is_spouse_nrb")
    private boolean isSpouseNrb;

    @Column(name = "is_lpa")
    private boolean isLpa;

    @Column(name = "primary_property")
    private float primaryProperty;

    @Column(name = "uk_holiday_home")
    private float ukHolidayHome;

    @Column(name = "btl_property")
    private float btlProperty;

    @Column(name = "foreign_property")
    private float foreignProperty;

    @Column(name = "foreign_will")
    private boolean foreignWill;

    @Column(name = "brp_assets")
    private float brpAssets;

    @Column(name = "non_brp_assets")
    private float nonBrpAssets;

    @Column(name = "nature_of_business")
    private String natureOfBusiness;

    @Column(name = "investments")
    private float investments;

    @Column(name = "savings_cash")
    private float savingsCash;

    @Column(name = "total", updatable = false, insertable = false)
    private float total;

    @Column(name = "personal_life_cover")
    private float personalLifeCover;

    @Column(name = "trust")
    private boolean trust;

    @Column(name = "death_benefit_trust")
    private boolean deathBenefitTrust;

    @Column(name = "death_in_service")
    private boolean deathInService;

    @Column(name = "pension")
    private boolean pension;

    @ManyToMany
    @JoinTable(
            name = "client_children",
            joinColumns = @JoinColumn(name = "client_id_1"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private Set<Child> childrenAsFirstClient = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "client_children",
            joinColumns = @JoinColumn(name = "client_id_2"),
            inverseJoinColumns = @JoinColumn(name = "child_id")
    )
    private Set<Child> childrenAsSecondClient = new HashSet<>();
}
