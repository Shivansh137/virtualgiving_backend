package com.virtualgiving.services;

import com.virtualgiving.entities.AdminEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.AdminRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public AdminEntity addNewAdmin(AdminEntity admin) {
        return adminRepository.save(admin);
    }

    public List<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }

    public AdminEntity getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public AdminEntity updateAdminById(Long id, AdminEntity updatedAdminEntity) {
        AdminEntity AdminEntity = adminRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        AdminEntity.setCity(updatedAdminEntity.getCity());
        AdminEntity.setName(updatedAdminEntity.getName());
        AdminEntity.setState(updatedAdminEntity.getState());
        AdminEntity.setStreet(updatedAdminEntity.getStreet());
        AdminEntity.setContactNumbers(updatedAdminEntity.getContactNumbers());
        AdminEntity.setProfilePicture(updatedAdminEntity.getProfilePicture());
        return adminRepository.save(AdminEntity);
    }

    public void deleteAdminById(Long id) {
        AdminEntity AdminEntity = adminRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        adminRepository.delete(AdminEntity);
    }
}
