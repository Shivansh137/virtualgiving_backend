package com.virtualgiving.entities;

import java.util.List;

import com.virtualgiving.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Name cannot be blank")
    @Size(min =  5, max = 50, message = "Name must be between 5 and 50 characters")
    private String name;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid Email format")
    private String email;

    @Column(name = "contact_numbers")
    @ElementCollection
    private List<String> contactNumbers;

    @Column(name = "profile_picture")
    private String profilePicture;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotNull(message = "Role is a required field")
    @Enumerated(EnumType.STRING)
    private Role role = Role.STUDENT;

}
