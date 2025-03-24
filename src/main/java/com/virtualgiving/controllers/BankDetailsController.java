package com.virtualgiving.controllers;

import com.virtualgiving.entities.BankDetailsEntity;
import com.virtualgiving.services.BankDetailsService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank_details")
public class BankDetailsController {
    private final BankDetailsService bankDetailsService;

    public BankDetailsController(BankDetailsService bankDetailsService){
        this.bankDetailsService = bankDetailsService;
    }

    @GetMapping
    public List<BankDetailsEntity> getAllBankDetails(){
        return bankDetailsService.getAllBankDetails();
    }

    @GetMapping("/{id}")
    public BankDetailsEntity getBankDetailsById(@PathVariable Long id){
        return bankDetailsService.getBankDetailsById(id);
    }

    @PostMapping
    public BankDetailsEntity addNewBankDetails(@Valid @RequestBody BankDetailsEntity bankDetailsEntity){
        return bankDetailsService.addNewBankDetails(bankDetailsEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankDetailsById(@PathVariable Long id){
        bankDetailsService.deleteBankDetailsById(id);
        return ResponseEntity.ok("Bank Details deleted successfully");
    }
}
