package com.esprit.tn.candidacy_microservice.Entities.Candidacy;


public class CandidatureNotFoundException extends RuntimeException {

    public CandidatureNotFoundException(Integer idCandidacy) {
        super("Candidature not found with id : " + idCandidacy);
    }

}


