package com.esprit.tn.candidacy_microservice.Repository.Candidacy;




import com.esprit.tn.candidacy_microservice.Entities.Candidacy.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, Long> {
    QRCode findByLink(String link);
}
