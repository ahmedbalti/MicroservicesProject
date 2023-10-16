package tn.esprit.microservicesnihed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.microservicesnihed.entity.Claim;
import tn.esprit.microservicesnihed.entity.Response;
import tn.esprit.microservicesnihed.entity.State;
import tn.esprit.microservicesnihed.exception.UpdateClaimException;
import tn.esprit.microservicesnihed.repository.ClaimRepository;
import tn.esprit.microservicesnihed.repository.ResponseRepository;

@Service
public class ResponseServiceImpl implements IResponseService{

    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private ResponseRepository responseRepository;


    @Transactional
    public void addResponse(Response rsp, Long clmId){
        Claim clm= claimRepository.findById(clmId).orElseThrow(()->new UpdateClaimException("object not found with id ="+clmId));
        clm.setStateClm(State.RESOLVED);
        clm.setResponse(rsp);
        rsp.setClaim(clm);
        responseRepository.save(rsp);
    }
    public Response getResponseByClaim(Long clmId){
        Claim clm= claimRepository.findById(clmId).orElseThrow(()->new UpdateClaimException("object not found with id ="+clmId));
        return clm.getResponse();
    }

    public void archiveResponse(Long idrsp){
        Response rsp= responseRepository.findById(idrsp).orElseThrow(()->new UpdateClaimException("object not found with id ="+idrsp));
        rsp.setArchiveRsp(true);
        responseRepository.save(rsp);
    }




}
