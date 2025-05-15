package com.virtualgiving.services;

import com.virtualgiving.dto.LoginRequestDTO;
import com.virtualgiving.dto.LoginResponseDTO;
import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.StudentRepository;
import com.virtualgiving.utils.JwtUtil;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final JwtUtil jwtUtil;
   


    public StudentService(StudentRepository studentRepository, JwtUtil jwtUtil) {
        this.studentRepository = studentRepository;
        this.jwtUtil = jwtUtil;

    }

    public StudentEntity addNewStudent(StudentEntity student) {
        return studentRepository.save(student);
    }

    public List<StudentEntity> getAllStudents() {
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public StudentEntity updateStudentById(Long id, StudentEntity updatedStudentEntity) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        studentEntity.setCity(updatedStudentEntity.getCity());
        studentEntity.setName(updatedStudentEntity.getName());
        studentEntity.setState(updatedStudentEntity.getState());
        studentEntity.setStreet(updatedStudentEntity.getStreet());
        studentEntity.setContactNumbers(updatedStudentEntity.getContactNumbers());
        studentEntity.setProfilePicture(updatedStudentEntity.getProfilePicture());
        return studentRepository.save(studentEntity);
    }

    public void deleteStudentById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        studentRepository.delete(studentEntity);
    }

     public LoginResponseDTO loginStudent(LoginRequestDTO request) {
        Optional<StudentEntity> studentOptional = studentRepository.findByEmail(request.getEmail());

        if (studentOptional.isPresent()) {
            StudentEntity student = studentOptional.get();
            if (student.getPassword().equals(request.getPassword())) {
                String token = jwtUtil.generateToken(student.getEmail());
                return new LoginResponseDTO(token, "Login successful");
            } else {
                return new LoginResponseDTO(null, "Invalid password");
            }
        } else {
            return new LoginResponseDTO(null, "Student not found");
        }
    }
}

    

