package com.virtualgiving.entities;

import com.virtualgiving.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bank_details")
public class BankDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_no", nullable = false, unique = true, length = 18)
    @Size(min = 9, max = 18, message = "Account no must be between 9 to 18 digits")
    @Pattern(regexp = "\\d+", message = "Only numbers are allowed in account number")
    private String accountNo;

    @Column(name = "ifsc", nullable = false, length = 11)
    @Size(min = 11, max = 11, message = "IFSC must be exactly 11 characters")
    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$", message = "Invalid IFSC code format")
    private String ifsc;

    @NotNull(message = "Status is a required field")
    @Enumerated(EnumType.STRING)
    private Status status = Status.UNVERIFIED;

    @OneToOne
    @JoinColumn(name = "student_id", nullable = true)
    private StudentEntity student;

    @OneToOne
    @JoinColumn(name = "organization_id", nullable = true)
    private OrganizationEntity organization;
}
