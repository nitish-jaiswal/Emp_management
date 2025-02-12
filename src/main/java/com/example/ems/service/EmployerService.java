package com.example.ems.service;

import com.example.ems.model.Employer;
import com.example.ems.repository.EmployerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EmployerService {

    @Autowired
    private EmployerRepository employerRepository;

    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    public Employer getEmployerById(Long id) {
        return employerRepository.findById(id).orElse(null);
    }

    public Employer createEmployer(Employer employer) {
        log.info("Creating employer: {}", employer);
        return employerRepository.save(employer);
    }

    public Employer updateEmployer(Long id, Employer employerDetails) {
        Employer employer = getEmployerById(id);
        if (employer != null) {
            employer.setName(employerDetails.getName());
            employer.setContactInfo(employerDetails.getContactInfo());
            return employerRepository.save(employer);
        }
        return null;
    }

    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }
}
