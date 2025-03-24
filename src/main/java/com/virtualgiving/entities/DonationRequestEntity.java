package com.virtualgiving.entities;

import com.virtualgiving.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donation_requests")
public class DonationRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 50, message = "title must be between 5 to 50 characters")
    @NotBlank
    private String title;

    @Size(min = 5, max = 50, message = "description must be between 5 to 50 characters")
    @NotBlank
    private String description;

    @Min(value = 100, message = "Donation request amount must be >= 500")
    @Column(name = "target_amount", nullable = false)
    private Double targetAmount;

    private LocalDateTime createdAt;

    @Column(name = "collected_amount", nullable = true)
    private Double collectedAmount = 0.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @NotNull(message = "status cannot be null")
    private Status status = Status.UNVERIFIED;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private OrganizationEntity organization;

    @PrePersist
    public void prePersist(){
        collectedAmount = 0.0;
        createdAt = LocalDateTime.now();
    }
}
