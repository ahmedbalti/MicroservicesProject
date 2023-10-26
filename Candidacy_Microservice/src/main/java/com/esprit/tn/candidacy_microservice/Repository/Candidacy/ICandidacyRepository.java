package com.esprit.tn.candidacy_microservice.Repository.Candidacy;



import com.esprit.tn.candidacy_microservice.Entities.Candidacy.Candidacy;
import com.esprit.tn.candidacy_microservice.Entities.Candidacy.StatusCandidacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICandidacyRepository extends JpaRepository<Candidacy, Integer> {
    @Query("SELECT c FROM Candidacy c WHERE c.firstName = :firstName")
    List<Candidacy> findByNom(@Param("firstName") String firstName);

    List<Candidacy> findByStatusCandidacy(StatusCandidacy statusCandidacy);
    List<Candidacy> findAllByOffreId(Integer offreId);


}

