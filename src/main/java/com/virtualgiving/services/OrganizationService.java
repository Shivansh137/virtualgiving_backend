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

    public OrganizationEntity add(OrganizationEntity organization){
        return organizationRepository.save(organization);
    }

    public List<OrganizationEntity> getAll(){
        return organizationRepository.findAll();
    }

    public OrganizationEntity getById(Long id){
        return organizationRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public OrganizationEntity updateById(Long id, OrganizationEntity updatedOrganizationEntity){
        OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        organizationEntity.setCity(updatedOrganizationEntity.getCity());
        organizationEntity.setName(updatedOrganizationEntity.getName());
        organizationEntity.setState(updatedOrganizationEntity.getState());
        organizationEntity.setStreet(updatedOrganizationEntity.getStreet());
        organizationEntity.setContact_numbers(updatedOrganizationEntity.getContact_numbers());
        organizationEntity.setProfile_picture(updatedOrganizationEntity.getProfile_picture());
        return organizationRepository.save(organizationEntity);
    }

    public void delete(Long id){
        OrganizationEntity organizationEntity = organizationRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        organizationRepository.delete(organizationEntity);
    }

}
