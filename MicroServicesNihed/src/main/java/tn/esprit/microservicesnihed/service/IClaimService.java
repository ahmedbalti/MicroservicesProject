package tn.esprit.microservicesnihed.service;

import tn.esprit.microservicesnihed.entity.Claim;
import tn.esprit.microservicesnihed.entity.State;

import java.util.List;

public interface IClaimService {
    public void createClaim(Claim claim);
    public void updateClaim(Claim claim,Long id);
    public void archiveClaim(Long id);
    public List<Claim> getAllClaims();
    public List<Claim> getClaimByStatus(State st);
    public List<Claim> getBefore();
    public List<Claim> getAfter();
    public List<Claim> getAtDate();
    public void blockClaim(Long clmId);
    public void claimInProgress(Long clmId);

    public List<Claim> getActiveClaims();


    void deleteClaim(Long idClaim);
}
