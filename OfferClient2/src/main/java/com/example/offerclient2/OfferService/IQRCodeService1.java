package com.example.offerclient2.OfferService;

import com.example.offerclient2.entity.Offer.QRCode1;

public interface IQRCodeService1 {
    QRCode1 findByLink(String link);

}
