package com.example.offerclient2.OfferRepository;

import com.example.offerclient2.entity.Offer.QRCode1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQRCodeRepository1 extends JpaRepository<QRCode1, Long> {
    QRCode1 findByLink(String link);
}
