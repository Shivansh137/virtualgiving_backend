package com.virtualgiving.controllers;

import com.virtualgiving.dto.LoginRequestDTO;
import com.virtualgiving.dto.LoginResponseDTO;
import com.virtualgiving.entities.AlumniEntity;
import com.virtualgiving.services.AlumniService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumni")
@CrossOrigin(origins = "http://localhost:5173")
public class AlumniController {

    private final AlumniService alumniService;

    public AlumniController(AlumniService alumniService){
        this.alumniService = alumniService;
    }

    @GetMapping
    public List<AlumniEntity> getAllAlumni() {
        return alumniService.getAllAlumni();
    }

    @GetMapping("/{id}")
    public AlumniEntity getAlumniById(@PathVariable Long id) {
        return alumniService.getAlumniById(id);
    }

    @PostMapping
    public AlumniEntity addNewAlumni(@RequestBody AlumniEntity alumni) {
        return alumniService.addNewAlumni(alumni);
    }

    @PutMapping("/{id}")
    public AlumniEntity updateAlumniById(@PathVariable Long id, @RequestBody AlumniEntity updatedAlumni) {
        return alumniService.updateAlumniById(id, updatedAlumni);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlumniById(@PathVariable Long id){
        alumniService.deleteAlumniById(id);
        return ResponseEntity.ok("Alumni deleted successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginAlumni(@RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = alumniService.loginAlumni(request);
        return ResponseEntity.ok(response);
    }
}
