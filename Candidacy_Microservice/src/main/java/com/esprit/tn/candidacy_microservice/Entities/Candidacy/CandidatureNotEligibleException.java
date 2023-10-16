package com.esprit.tn.candidacy_microservice.Entities.Candidacy;

public class CandidatureNotEligibleException extends RuntimeException {

    public CandidatureNotEligibleException(String message) {
        super(message);
    }
}