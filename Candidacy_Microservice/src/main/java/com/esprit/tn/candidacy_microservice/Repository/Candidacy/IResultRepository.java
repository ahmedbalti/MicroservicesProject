package com.esprit.tn.candidacy_microservice.Repository.Candidacy;


import com.esprit.tn.candidacy_microservice.Entities.Candidacy.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IResultRepository extends JpaRepository<Result, Integer> {

}