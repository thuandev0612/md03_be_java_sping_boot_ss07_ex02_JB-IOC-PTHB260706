package com.example.baitapsession07.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "candidates")
@Getter
@Setter
@NoArgsConstructor
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "full_name", nullable = false, length = 50)
    private String fullName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "years_of_experience", nullable = false)
    private Integer yearsOfExperience;
}
