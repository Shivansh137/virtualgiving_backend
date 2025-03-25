package com.virtualgiving.entities;

import com.virtualgiving.enums.Status;
import jakarta.persistence.*;
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
@Table(name = "applications")
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id", nullable = false)
    @NotNull(message = "Applicant cannot be null")
    private StudentEntity applicant;  

    @ManyToOne
    @JoinColumn(name = "opportunity_id", nullable = false)
    @NotNull(message = "Internship opportunity cannot be null")
    private InternshipOpportunityEntity opportunity;  

    @ManyToOne
    @JoinColumn(name = "alumni_id", nullable = false)
    @NotNull(message = "Alumni cannot be null")
    private AlumniEntity alumni;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotNull(message = "Cover Letter cannot be null")
    @Size(min = 50, max = 5000, message = "Cover letter must be between 50 and 5000 characters")
    private String coverLetter;

    @Column(length = 255, nullable = false)
    @NotNull(message = "Resume link cannot be null")
    @Size(max = 255, message = "Resume link must be at most 255 characters")
    private String resumeLink;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
