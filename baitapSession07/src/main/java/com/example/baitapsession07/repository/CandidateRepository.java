package com.example.baitapsession07.repository;

import com.example.baitapsession07.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
