package com.example.baitapsession07.controller;

import com.example.baitapsession07.dto.CandidateCreateDTO;
import com.example.baitapsession07.model.Candidate;
import com.example.baitapsession07.service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/candidates", "/api/v1/candidates"})
public class CandidateController {
    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@Valid @RequestBody CandidateCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.createCandidate(dto));
    }
}
