package com.hutech.demo.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "helpers")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Helper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "skills", columnDefinition = "TEXT")
    private String skills;

    @Enumerated(EnumType.STRING)
    @Column(name = "availability", length = 20)
    private Availability availability;

    @Column(name = "rate_per_hour")
    private BigDecimal ratePerHour;

    @Column(name = "profile_picture", length = 255)
    private String profilePicture;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public enum Availability {
        AVAILABLE, UNAVAILABLE
    }
}
