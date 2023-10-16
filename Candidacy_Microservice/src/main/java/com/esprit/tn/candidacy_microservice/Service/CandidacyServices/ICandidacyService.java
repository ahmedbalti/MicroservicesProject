package com.esprit.tn.candidacy_microservice.Service.CandidacyServices;


import com.esprit.tn.candidacy_microservice.Entities.Candidacy.Candidacy;
import com.esprit.tn.candidacy_microservice.Entities.Candidacy.DomainCandidacy;
import com.esprit.tn.candidacy_microservice.Entities.Candidacy.StatusCandidacy;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ICandidacyService {

        List<Candidacy> getAllCandidacy();

        List<Candidacy> getAllCandidacy1();

        Candidacy getCandidacyById1(Integer id);

        Candidacy createCandidacy(Candidacy candidacy);

        Candidacy updateCandidacy(Integer id, Candidacy candidacyDetails);

        void deleteCandidacy(Integer id);

        List<Candidacy> getCandidacyByNom(String firstName);

        List<Candidacy> trierParDate();

        void archiveCandidature(Integer id);

        Page<Candidacy> getAllCandidatures(int pageNumber, int pageSize, String sortBy);

        Map<DomainCandidacy, Long> getNombreCandidaturesParDomaine();

        Candidacy getCandidatureById(Integer idCandidacy);

        Candidacy updateCandidacyStatus(Integer idCandidacy);

        List<Candidacy> getCandidatesByStatus(StatusCandidacy statusCandidacy);

        Candidacy updateCandidatureStatus(Integer idCandidacy);

        void accepterOuRefuserCandidature(Integer idCandidacy);


        Candidacy saveCandidature(Candidacy candidature);

        // public List<Candidacy> getCandidacyByUser(Long userId);

        // void createCandidacy(Candidacy candidacy, Long userId);

        Candidacy getCandidacyById(Integer id);

        Optional<Candidacy> findById(Integer id);

        Candidacy save(Candidacy employee);




}