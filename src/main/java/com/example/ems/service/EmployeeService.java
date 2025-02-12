package com.example.ems.service;

import com.example.ems.model.Employee;
import com.example.ems.model.Project;
import com.example.ems.model.SkillSet;
import com.example.ems.repository.EmployeeRepository;
import com.example.ems.repository.ProjectRepository;
import com.example.ems.repository.SkillSetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private SkillSetRepository skillSetRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        log.info("Creating employee: {}", employee);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = getEmployeeById(id);
        if (employee != null) {
            employee.setName(employeeDetails.getName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setRole(employeeDetails.getRole());
            employee.setDepartment(employeeDetails.getDepartment());
            return employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee addSkillToEmployee(Long employeeId, Long skillId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            SkillSet skillSet = skillSetRepository.findById(skillId).orElse(null);
            if (skillSet != null) {
                employee.getSkillSets().add(skillSet);
                return employeeRepository.save(employee);
            }
        }
        return null;
    }

    public Employee removeSkillFromEmployee(Long employeeId, Long skillId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            SkillSet skillSet = skillSetRepository.findById(skillId).orElse(null);
            if (skillSet != null) {
                employee.getSkillSets().remove(skillSet);
                return employeeRepository.save(employee);
            }
        }
        return null;
    }

    public Set<SkillSet> getSkillsForEmployee(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        return employee != null ? employee.getSkillSets() : null;
    }

    public Employee assignEmployeeToProject(Long employeeId, Long projectId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            Project project = projectRepository.findById(projectId).orElse(null);
            if (project != null) {
                employee.getProjects().add(project);
                return employeeRepository.save(employee);
            }
        }
        return null;
    }

    public Employee removeEmployeeFromProject(Long employeeId, Long projectId) {
        Employee employee = getEmployeeById(employeeId);
        if (employee != null) {
            Project project = projectRepository.findById(projectId).orElse(null);
            if (project != null) {
                employee.getProjects().remove(project);
                return employeeRepository.save(employee);
            }
        }
        return null;
    }

    public Set<Project> getProjectsForEmployee(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        return employee != null ? employee.getProjects() : null;
    }
}
