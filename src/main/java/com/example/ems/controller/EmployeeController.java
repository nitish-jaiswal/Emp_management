package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.model.SkillSet;
import com.example.ems.model.Project;
import com.example.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    @PostMapping("/{employeeId}/skills/{skillId}")
    public ResponseEntity<Employee> addSkillToEmployee(@PathVariable Long employeeId, @PathVariable Long skillId) {
        Employee updatedEmployee = employeeService.addSkillToEmployee(employeeId, skillId);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{employeeId}/skills/{skillId}")
    public ResponseEntity<Employee> removeSkillFromEmployee(@PathVariable Long employeeId, @PathVariable Long skillId) {
        Employee updatedEmployee = employeeService.removeSkillFromEmployee(employeeId, skillId);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{employeeId}/skills")
    public Set<SkillSet> getSkillsForEmployee(@PathVariable Long employeeId) {
        return employeeService.getSkillsForEmployee(employeeId);
    }

    @PostMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<Employee> assignEmployeeToProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        Employee updatedEmployee = employeeService.assignEmployeeToProject(employeeId, projectId);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{employeeId}/projects/{projectId}")
    public ResponseEntity<Employee> removeEmployeeFromProject(@PathVariable Long employeeId, @PathVariable Long projectId) {
        Employee updatedEmployee = employeeService.removeEmployeeFromProject(employeeId, projectId);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{employeeId}/projects")
    public Set<Project> getProjectsForEmployee(@PathVariable Long employeeId) {
        return employeeService.getProjectsForEmployee(employeeId);
    }


}
