package com.esprit.tn.candidacy_microservice.Service.CandidacyServices;

import com.esprit.tn.candidacy_microservice.Entities.Candidacy.QRCode;


public interface IQRCodeService {
    QRCode findByLink(String link);

}