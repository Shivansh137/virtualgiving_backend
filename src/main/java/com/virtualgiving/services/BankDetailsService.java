package com.virtualgiving.services;

import com.virtualgiving.entities.BankDetailsEntity;
import com.virtualgiving.entities.OrganizationEntity;
import com.virtualgiving.entities.StudentEntity;
import com.virtualgiving.exceptions.UserNotFoundException;
import com.virtualgiving.repositories.BankDetailsRepository;
import com.virtualgiving.repositories.OrganizationRepository;
import com.virtualgiving.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankDetailsService {

    @Autowired
    private final BankDetailsRepository bankDetailsRepository;
    private final StudentRepository studentRepository;
    private final OrganizationRepository organizationRepository;

    public BankDetailsService(BankDetailsRepository bankDetailsRepository, StudentRepository studentRepository, OrganizationRepository organizationRepository){
        this.bankDetailsRepository = bankDetailsRepository;
        this.studentRepository = studentRepository;
        this.organizationRepository = organizationRepository;
    }

    public BankDetailsEntity addNewBankDetails(BankDetailsEntity bankDetailsEntity){
        if(bankDetailsEntity.getStudent() != null){
            StudentEntity studentEntity = studentRepository.findById(bankDetailsEntity.getStudent().getId()).orElseThrow(()-> new RuntimeException("Student not found"));
            bankDetailsEntity.setStudent(studentEntity);
        }else if(bankDetailsEntity.getOrganization() != null){
            OrganizationEntity organizationEntity = organizationRepository.findById(bankDetailsEntity.getOrganization().getId()).orElseThrow(()-> new RuntimeException("Organization not found"));
            bankDetailsEntity.setOrganization(organizationEntity);
        }
        return bankDetailsRepository.save(bankDetailsEntity);
    }

    public List<BankDetailsEntity> getAllBankDetails(){
        return bankDetailsRepository.findAll();
    }

    public BankDetailsEntity getBankDetailsById(Long id){
        return bankDetailsRepository.findById(id).orElseThrow(() -> new RuntimeException("Bank Details Not Found"));
    }

    public void deleteBankDetailsById(Long id){
        BankDetailsEntity bankDetailsEntity = bankDetailsRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        bankDetailsRepository.delete(bankDetailsEntity);
    }
}
