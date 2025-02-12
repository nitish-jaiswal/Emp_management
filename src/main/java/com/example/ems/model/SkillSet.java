package com.example.ems.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;

@Entity
@Data
public class SkillSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skillName;

    @ManyToMany(mappedBy = "skillSets")
    private Set<Employee> employees;
}
