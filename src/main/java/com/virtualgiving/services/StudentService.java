package com.virtualgiving.services;

import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.StudentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public StudentEntity addNewStudent(StudentEntity student){
        return studentRepository.save(student);
    }

    public List<StudentEntity> getAllStudents(){
        return studentRepository.findAll();
    }

    public StudentEntity getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public StudentEntity updateStudentById(Long id, StudentEntity updatedStudentEntity){
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        studentEntity.setCity(updatedStudentEntity.getCity());
        studentEntity.setName(updatedStudentEntity.getName());
        studentEntity.setState(updatedStudentEntity.getState());
        studentEntity.setStreet(updatedStudentEntity.getStreet());
        studentEntity.setContactNumbers(updatedStudentEntity.getContactNumbers());
        studentEntity.setProfilePicture(updatedStudentEntity.getProfilePicture());
        return studentRepository.save(studentEntity);
    }

    public void deleteStudentById(Long id){
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        studentRepository.delete(studentEntity);
    }

}
