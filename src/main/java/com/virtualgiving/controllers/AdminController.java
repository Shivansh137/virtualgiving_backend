package com.virtualgiving.controllers;

import com.virtualgiving.entities.AdminEntity;
import com.virtualgiving.services.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping
    public List<AdminEntity> getAll(){
        return adminService.getAll();
    }

    @GetMapping("/{id}")
    public AdminEntity getById(@PathVariable Long id){
        return adminService.getById(id);
    }

    @PostMapping
    public AdminEntity add(@Valid @RequestBody AdminEntity adminEntity){
        return adminService.add(adminEntity);
    }

    @PutMapping("/{id}")
    public AdminEntity update(@PathVariable Long id, @Valid @RequestBody AdminEntity adminEntity){
        return adminService.updateById(id, adminEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
        adminService.delete(id);
        return ResponseEntity.ok("Admin deleted successfully");
    }

}
