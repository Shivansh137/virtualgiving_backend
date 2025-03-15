package com.virtualgiving.entities;

import com.virtualgiving.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class AdminEntity {

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

    @NotBlank(message = "City cannot be blank")
    private String city;

    @NotBlank(message = "State cannot be blank")
    private String state;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    private String profile_picture;

    @NotNull(message = "Role is a required field")
    @Enumerated(EnumType.STRING)
    private Role role;

    @ElementCollection
    private List<String> contact_numbers;

}
