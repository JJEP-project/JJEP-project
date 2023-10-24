package com.JJEP.JJEP.application;

import com.JJEP.JJEP.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "user_id", updatable = false)
    private Long userId;

//    intentionally commented out because mapping does not work with jpa relations
//    when mapping dto to entity mapping tries to fill user entity and save to db both user and application
//    I think that we don't need jpa relations and can work either with 2 queries or with joins
//    @JoinColumn(name = "user_id")
//    @OneToOne(fetch = FetchType.LAZY)
//    @Transient
//    private User user;

    @Column(name = "is_blood_protection")
    private boolean isBloodProtection;

    @Column(name = "is_generational_iht")
    private boolean isGenerationIht;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
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

    @Column(name = "total", updatable = false, insertable = false)
    private float total;

    @Column(name = "personal_life_cover")
    private float personalLifeCover;

    @Column(name = "trust")
    private boolean trust;
}
