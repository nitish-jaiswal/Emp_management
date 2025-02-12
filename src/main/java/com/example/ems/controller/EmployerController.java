package com.example.ems.controller;

import com.example.ems.model.Employer;
import com.example.ems.service.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {

    @Autowired
    private EmployerService employerService;

    @GetMapping
    public List<Employer> getAllEmployers() {
        return employerService.getAllEmployers();
    }

    @GetMapping("/{id}")
    public Employer getEmployerById(@PathVariable Long id) {
        return employerService.getEmployerById(id);
    }

    @PostMapping
    public ResponseEntity<Employer> createEmployer(@RequestBody Employer employer) {
        Employer createdEmployer = employerService.createEmployer(employer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployer);
    }

    @PutMapping("/{id}")
    public Employer updateEmployer(@PathVariable Long id, @RequestBody Employer employerDetails) {
        return employerService.updateEmployer(id, employerDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployer(@PathVariable Long id) {
        employerService.deleteEmployer(id);
    }
}
