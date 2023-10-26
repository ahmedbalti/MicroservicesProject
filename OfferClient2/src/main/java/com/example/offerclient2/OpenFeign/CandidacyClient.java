package com.example.offerclient2.OpenFeign;

import com.example.offerclient2.entity.Offer.Candidacy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "Candidacy-service", url = "http://localhost:8083", path = "/api/candidacy")
public interface CandidacyClient {
    @GetMapping("/offer/{offre-id}")
    List<Candidacy> findAllCandidaciesByOffer(@PathVariable("offre-id") Integer offreId);
}