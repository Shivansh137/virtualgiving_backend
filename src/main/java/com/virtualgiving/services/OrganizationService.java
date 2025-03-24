package com.virtualgiving.services;

import com.virtualgiving.entities.OrganizationEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.OrganizationRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository){
        this.organizationRepository = organizationRepository;
    }

    public OrganizationEntity addNewOrganization(OrganizationEntity organization){
        return organizationRepository.save(organization);
    }

    public List<OrganizationEntity> getAllOrganizations(){
        return organizationRepository.findAll();
    }

    public OrganizationEntity getOrganizationById(Long id){
        return organizationRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public OrganizationEntity updateOrganizationById(Long id, OrganizationEntity updatedOrganizationEntity){
        OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        organizationEntity.setCity(updatedOrganizationEntity.getCity());
        organizationEntity.setName(updatedOrganizationEntity.getName());
        organizationEntity.setState(updatedOrganizationEntity.getState());
        organizationEntity.setStreet(updatedOrganizationEntity.getStreet());
        organizationEntity.setContactNumbers(updatedOrganizationEntity.getContactNumbers());
        organizationEntity.setProfilePicture(updatedOrganizationEntity.getProfilePicture());
        return organizationRepository.save(organizationEntity);
    }

    public void deleteOrganizationById(Long id){
        OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        organizationRepository.delete(organizationEntity);
    }

}
