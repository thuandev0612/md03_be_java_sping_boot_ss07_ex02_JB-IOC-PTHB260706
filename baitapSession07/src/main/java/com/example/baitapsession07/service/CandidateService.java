package com.example.baitapsession07.service;

import com.example.baitapsession07.dto.CandidateCreateDTO;
import com.example.baitapsession07.model.Candidate;
import com.example.baitapsession07.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Transactional
    public Candidate createCandidate(CandidateCreateDTO dto) {
        Candidate candidate = new Candidate();
        candidate.setFullName(dto.getFullName());
        candidate.setEmail(dto.getEmail());
        candidate.setAge(dto.getAge());
        candidate.setYearsOfExperience(dto.getYearsOfExperience());
        return candidateRepository.save(candidate);
    }
}
