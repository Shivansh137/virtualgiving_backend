package com.virtualgiving.services;

import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.AlumniRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumniService {
    private final AlumniRepository AlumniRepository;

    public AlumniService(AlumniRepository AlumniRepository){
        this.AlumniRepository = AlumniRepository;
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
}
