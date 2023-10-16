package tn.esprit.microservicesnihed.service;

import tn.esprit.microservicesnihed.entity.Response;

public interface IResponseService {

    public void addResponse(Response rsp, Long clmId);
}
