package com.virtualgiving.entities;

import com.virtualgiving.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
//import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donations")
public class DonationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    @Column(name = "amount", nullable = false)
    @Min(value = 1)
    @Max(value = 100000)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.PENDING;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private DonationRequestEntity request;

    @ManyToOne
    @JoinColumn(name = "donor_id", nullable = false)
    private AlumniEntity donor;

    @Size(min = 5, max = 50, message = "Message: 5 - 50 characters")
    private String message;

    @PrePersist
    public void prePersist(){
        createdAt = LocalDateTime.now();
    }

}
