package com.JJEP.JJEP.application;

import com.JJEP.JJEP.application.client.Client;
import com.JJEP.JJEP.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
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

    @Column(name = "is_blood_protection")
    private Boolean isBloodProtection;

    @Column(name = "is_generational_iht")
    private Boolean isGenerationIht;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "notes")
    private String notes;

    @Column(name = "primary_property")
    private Float primaryProperty;

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

    @Column(name = "status")
    private Integer status = ApplicationStatus.REQUESTED.status;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude private User user;

    @OneToMany(mappedBy = "application", fetch = FetchType.LAZY)
    @ToString.Exclude private java.util.List<Client> clients;
}
