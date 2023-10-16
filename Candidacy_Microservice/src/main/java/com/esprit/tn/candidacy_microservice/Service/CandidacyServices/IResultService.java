package com.esprit.tn.candidacy_microservice.Service.CandidacyServices;


import com.esprit.tn.candidacy_microservice.Entities.Candidacy.Result;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public interface IResultService {
    List<Result> getAllResult();
    Result getResultById(Integer id);
    Result createResult(Result result);
    Result updateResult(Integer id, Result resultDetails);
    void deleteResult(Integer id);
    void exportResultToExcel(HttpServletResponse response);

    List<Result> findTop10ByOrderByNoteDesc();

    Result findById(Integer id);
    List<Result> findAll();
    //Result save(Result result);
    void archiveResult(Integer id);
    Optional<Result> findById1(Integer id);




}