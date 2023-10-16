package tn.esprit.microservicesnihed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.microservicesnihed.entity.Claim;
import tn.esprit.microservicesnihed.entity.State;
import tn.esprit.microservicesnihed.exception.UpdateClaimException;
import tn.esprit.microservicesnihed.repository.ClaimRepository;

import java.util.List;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static tn.esprit.microservicesnihed.entity.State.NOT_TRAITED;

@Service
public class ClaimServiceImpl implements IClaimService{

    @Autowired
    private ClaimRepository claimRepository;

    private List<Claim> claims = new ArrayList<>();
    @Override
    public void createClaim(Claim claim) {

        claim.setArchiveClm(false);
        claim.setStateClm(NOT_TRAITED);
        claim.setCreationDateClm(LocalDateTime.now());

        claimRepository.save(claim);

    }


    @Override
    public void updateClaim(Claim claim, Long id) {
        Claim cl = claimRepository.findById(id).orElseThrow(() -> new UpdateClaimException("object not found with id =" + id));
        if (cl.getStateClm() == NOT_TRAITED) {
            cl.setDescriptionClm(claim.descriptionClm);
            cl.setSubjectClm(claim.subjectClm);
            cl.setTypeClm(claim.typeClm);
            cl.setModificationDateClm(LocalDateTime.now());
            claimRepository.save(cl);
        }

    }

    @Override
    public void archiveClaim(Long id) {

    }

    @Override
    public List<Claim> getAllClaims() {

        return claimRepository.findAll();

    }

    @Override
    public List<Claim> getClaimByStatus(State st) {
        return null;
    }

    @Override
    public List<Claim> getBefore() {
        return null;
    }

    @Override
    public List<Claim> getAfter() {
        return null;
    }

    @Override
    public List<Claim> getAtDate() {
        return null;
    }

    @Override
    public void blockClaim(Long clmId) {

        Claim clm = claimRepository.findById(clmId).orElseThrow(() -> new UpdateClaimException("object not found with id =" + clmId));
        clm.setStateClm(State.BLOCKED);
        claimRepository.save(clm);
    }


    @Override
    public void claimInProgress(Long clmId) {

    }

    @Override
    public List<Claim> getActiveClaims() {
        return null;
    }

    @Override
    public void deleteClaim(Long idClaim) {
        claimRepository.deleteById(idClaim);
    }
}
