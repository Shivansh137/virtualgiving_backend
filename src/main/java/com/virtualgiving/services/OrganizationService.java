package com.virtualgiving.services;

import com.virtualgiving.dto.LoginRequestDTO;
import com.virtualgiving.dto.LoginResponseDTO;
import com.virtualgiving.entities.OrganizationEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.OrganizationRepository;
import com.virtualgiving.utils.JwtUtil;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final JwtUtil jwtUtil;

    public OrganizationService(OrganizationRepository organizationRepository, JwtUtil jwtUtil){
        this.organizationRepository = organizationRepository;
        this.jwtUtil = jwtUtil;
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


    public LoginResponseDTO loginOrganization(LoginRequestDTO request) {
    Optional<OrganizationEntity> orgOptional = organizationRepository.findByEmail(request.getEmail());

    if (orgOptional.isPresent()) {
        OrganizationEntity organization = orgOptional.get();
        if (organization.getPassword().equals(request.getPassword())) {
            String token = jwtUtil.generateToken(organization.getEmail());
            return new LoginResponseDTO(token, "Login successful");
        } else {
            return new LoginResponseDTO(null, "Invalid password");
        }
    } else {
        return new LoginResponseDTO(null, "Organization not found");
    }
}


}
