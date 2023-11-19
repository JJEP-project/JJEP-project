package com.JJEP.JJEP.activity;

import com.JJEP.JJEP.user.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

// annotations from lombok to remove boilerplate code
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// annotations from JPA to map Java objects to database tables
@Entity(name="Activity")
@Table(name="activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "activity_message", nullable = false)
    private String activityMessage;

    @Column(name = "activity_date", nullable = false, insertable = false)
    @CreationTimestamp
    private LocalDateTime activityDate;

    // many-to-one relationship with user table
    // lazy fetch to avoid fetching all users when fetching an activity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ToString.Exclude private User user;
}
