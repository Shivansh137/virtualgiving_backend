package com.virtualgiving.services;

import com.virtualgiving.entities.AdminEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    public AdminEntity add(AdminEntity admin){
        return adminRepository.save(admin);
    }

    public List<AdminEntity> getAll(){
        return adminRepository.findAll();
    }

    public AdminEntity getById(Long id){
        return adminRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public AdminEntity updateById(Long id, AdminEntity updatedAdminEntity){
        AdminEntity AdminEntity = adminRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        AdminEntity.setCity(updatedAdminEntity.getCity());
        AdminEntity.setName(updatedAdminEntity.getName());
        AdminEntity.setState(updatedAdminEntity.getState());
        AdminEntity.setStreet(updatedAdminEntity.getStreet());
        AdminEntity.setContact_numbers(updatedAdminEntity.getContact_numbers());
        AdminEntity.setProfile_picture(updatedAdminEntity.getProfile_picture());
        return adminRepository.save(AdminEntity);
    }

    public void delete(Long id){
        AdminEntity AdminEntity = adminRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        adminRepository.delete(AdminEntity);
    }

}
