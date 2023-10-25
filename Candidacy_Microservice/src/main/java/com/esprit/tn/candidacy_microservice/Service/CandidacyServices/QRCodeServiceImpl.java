package com.esprit.tn.candidacy_microservice.Service.CandidacyServices;


import com.esprit.tn.candidacy_microservice.Entities.Candidacy.QRCode;
import com.esprit.tn.candidacy_microservice.Repository.Candidacy.QRCodeRepository;
import com.google.zxing.BarcodeFormat;

import com.esprit.tn.candidacy_microservice.exception.UpdateCandidacyException;
import com.google.zxing.BarcodeFormat;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.QRCodeWriter;
import com.sun.javafx.webkit.UIClientImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class QRCodeServiceImpl implements IQRCodeService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    public QRCode createQRCode(String link) throws Exception {
        QRCode qrCode = qrCodeRepository.findByLink(link);
        if (qrCode != null) {
            // Si un code QR existe, retourner le code existant
            return qrCode;
        } else {

            QRCodeWriter qrCodeWriter = new QRCodeWriter();


            //BitMatrix bitMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, 200, 200);


            UIClientImpl MatrixToImageWriter;
            //BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //ImageIO.write(qrCodeImage, "png", baos);
            byte[] qrCodea = baos.toByteArray();

            String filePath = "C:/Spring Boot/International_Mobility_Management_Plateform" + UUID.randomUUID().toString() + ".png";
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(qrCodea);
            fos.close();


            QRCode qrCodez = new QRCode(link, filePath);
            qrCodeRepository.save(qrCodez);
            return qrCodez;
        }}

    public byte[] getQRCodeImage (Long id) throws IOException {
        QRCode qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new UpdateCandidacyException("QRCode not found"));
        File file = new File(qrCode.getPath());
        FileInputStream fis = new FileInputStream(file);
        byte[] image = Files.readAllBytes(file.toPath());
        fis.close();
        return image;
    }

    @Override
    public QRCode findByLink(String link) {
        return null;
    }
}