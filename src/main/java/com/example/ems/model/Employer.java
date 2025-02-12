package com.example.ems.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactInfo;

    @OneToMany(mappedBy = "employer")
    private Set<Employee> employees;
}
