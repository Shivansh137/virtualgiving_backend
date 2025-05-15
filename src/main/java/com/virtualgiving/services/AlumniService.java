package com.virtualgiving.services;

import com.virtualgiving.dto.LoginRequestDTO;
import com.virtualgiving.dto.LoginResponseDTO;
import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.AlumniRepository;
import com.virtualgiving.utils.JwtUtil;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumniService {
    private final AlumniRepository AlumniRepository;
    private final JwtUtil jwtUtil;

    public AlumniService(AlumniRepository AlumniRepository, JwtUtil jwtUtil){
        this.AlumniRepository = AlumniRepository;
        this.jwtUtil = jwtUtil;
    }

    public AlumniEntity addNewAlumni(AlumniEntity Alumni){
        return AlumniRepository.save(Alumni);
    }

    public List<AlumniEntity> getAllAlumni(){
        return AlumniRepository.findAll();
    }

    public AlumniEntity getAlumniById(Long id){
        return AlumniRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public AlumniEntity updateAlumniById(Long id, AlumniEntity updatedAlumniEntity){
        AlumniEntity AlumniEntity = AlumniRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        AlumniEntity.setCity(updatedAlumniEntity.getCity());
        AlumniEntity.setName(updatedAlumniEntity.getName());
        AlumniEntity.setState(updatedAlumniEntity.getState());
        AlumniEntity.setStreet(updatedAlumniEntity.getStreet());
        AlumniEntity.setContactNumbers(updatedAlumniEntity.getContactNumbers());
        AlumniEntity.setProfilePicture(updatedAlumniEntity.getProfilePicture());
        return AlumniRepository.save(AlumniEntity);
    }

    public void deleteAlumniById(Long id){
        AlumniEntity AlumniEntity = AlumniRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        AlumniRepository.delete(AlumniEntity);
    }

    public LoginResponseDTO loginAlumni(LoginRequestDTO request) {
        Optional<AlumniEntity> alumniOptional = AlumniRepository.findByEmail(request.getEmail());

        if (alumniOptional.isPresent()) {
            AlumniEntity alumni = alumniOptional.get();
            if (alumni.getPassword().equals(request.getPassword())) {
                String token = jwtUtil.generateToken(alumni.getEmail());
                return new LoginResponseDTO(token, "Login successful");
            } else {
                return new LoginResponseDTO(null, "Invalid password");
            }
        } else {
            return new LoginResponseDTO(null, "Alumni not found");
        }
    }
}
