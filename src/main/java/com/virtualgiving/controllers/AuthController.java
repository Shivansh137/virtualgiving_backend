package com.virtualgiving.controllers;

import com.virtualgiving.dto.LoginRequestDTO;
import com.virtualgiving.dto.LoginResponseDTO;
import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.entities.OrganizationEntity;
import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.services.AlumniService;
import com.virtualgiving.services.OrganizationService;
import com.virtualgiving.services.StudentService;
import com.virtualgiving.utils.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final StudentService studentService;
    private final OrganizationService organizationService;
    private final AlumniService alumniService;
    private final JwtUtil jwtUtil;

    public AuthController(StudentService studentService, OrganizationService organizationService, AlumniService alumniService, JwtUtil jwtUtil) {
        this.studentService = studentService;
        this.organizationService = organizationService;
        this.alumniService = alumniService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login/{userType}")
    public ResponseEntity<LoginResponseDTO> loginStudent(@RequestBody LoginRequestDTO request, @PathVariable String userType) {

        LoginResponseDTO response = switch (userType) {
            case "student" -> studentService.loginStudent(request);
            case "organization" -> organizationService.loginOrganization(request);
            case "alumni" -> alumniService.loginAlumni(request);
            default -> new LoginResponseDTO(null, "BAD REQUEST");
        };
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register/student")
    public LoginResponseDTO addNewStudent(@RequestBody StudentEntity student) {
         studentService.addNewStudent(student);
        String token = jwtUtil.generateToken(student.getEmail());
        return new LoginResponseDTO(token, "Registration successful");
    }

    @PostMapping("/register/organization")
    public LoginResponseDTO addNewOrganization(@Valid @RequestBody OrganizationEntity organizationEntity){
         organizationService.addNewOrganization(organizationEntity);
        String token = jwtUtil.generateToken(organizationEntity.getEmail());
        return new LoginResponseDTO(token, "Registration successful");
    }

    @PostMapping("/register/alumni")
    public LoginResponseDTO addNewAlumni(@RequestBody AlumniEntity alumni) {
        alumniService.addNewAlumni(alumni);
        String token = jwtUtil.generateToken(alumni.getEmail());
        return new LoginResponseDTO(token, "Registration successful");
    }

}
