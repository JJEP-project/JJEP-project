package com.JJEP.JJEP.application;

import com.JJEP.JJEP.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="user_application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "is_blood_protection")
    private boolean isBloodProtection;

    @Column(name = "is_generational_iht")
    private boolean isGenerationIht;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "notes")
    private String notes;

    @Column(name = "primary_property")
    private float primaryProperty;

    @Column(name = "UK_holiday_home")
    private float ukHolidayHome;

    @Column(name = "BTL_property")
    private float btlProperty;

    @Column(name = "foreign_property")
    private float foreignProperty;

    @Column(name = "foreign_will")
    private boolean foreignWill;

    @Column(name = "investment")
    private float investment;

    @Column(name = "savings_cash")
    private float savingsCash;

    @Column(name = "total")
    private float total;

    @Column(name = "personal_life_cover")
    private float personalLifeCover;

    @Column(name = "trust")
    private boolean trust;

    @Column(name = "pensions")
    private float pensions;
}
