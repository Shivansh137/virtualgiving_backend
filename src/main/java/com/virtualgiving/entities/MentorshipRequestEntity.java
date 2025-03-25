package com.virtualgiving.entities;

import com.virtualgiving.enums.Status;
import jakarta.persistence.*;
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
@Table(name = "mentorship_requests")
public class MentorshipRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 100, message = "Title must be between 5 to 100 characters")
    @NotBlank
    private String title;

    @Size(min = 10, message = "Description must be at least 10 characters")
    @NotBlank
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "Status cannot be null")
    private Status status = Status.PENDING;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = true)
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private OrganizationEntity organization;

    @ManyToOne
    @JoinColumn(name = "alumni_id", nullable = true)
    private AlumniEntity alumni;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
