package com.virtualgiving.controllers;

import com.virtualgiving.entities.AdminEntity;
import com.virtualgiving.services.AdminService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminEntity> getAllAdmins(){
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public AdminEntity getAdminById(@PathVariable Long id){
        return adminService.getAdminById(id);
    }

    @PostMapping
    public AdminEntity addNewAdmin(@Valid @RequestBody AdminEntity adminEntity){
        return adminService.addNewAdmin(adminEntity);
    }

    @PutMapping("/{id}")
    public AdminEntity updateAdminById(@PathVariable Long id, @Valid @RequestBody AdminEntity adminEntity){
        return adminService.updateAdminById(id, adminEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdminById(@PathVariable Long id){
        adminService.deleteAdminById(id);
        return ResponseEntity.ok("Admin deleted successfully");
    }

}
