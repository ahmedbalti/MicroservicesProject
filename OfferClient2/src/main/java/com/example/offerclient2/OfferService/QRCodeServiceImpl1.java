package com.example.offerclient2.OfferService;


import com.example.offerclient2.OfferRepository.IQRCodeRepository1;
import com.example.offerclient2.entity.Offer.QRCode1;
import com.example.offerclient2.exception.UpdateOfferException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

@Service
public class QRCodeServiceImpl1 implements IQRCodeService1 {

    @Autowired
    private IQRCodeRepository1 qrCodeRepository;

    public QRCode1 createQRCode(String link) throws Exception {
        QRCode1 qrCode = qrCodeRepository.findByLink(link);
        if (qrCode != null) {
            // Si un code QR existe, retourner le code existant
            return qrCode;
        } else {
            // Générer le code QR
            // Créer un Writer de code QR
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            // Créer une matrice de bits pour le code QR
            BitMatrix bitMatrix = qrCodeWriter.encode(link, BarcodeFormat.QR_CODE, 200, 200);

            // Convertir la matrice de bits en une image PNG
            BufferedImage qrCodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

            // Convertir l'image en un tableau de bytes
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qrCodeImage, "png", baos);
            byte[] qrCodea = baos.toByteArray();
            // Enregistrer le code QR dans un fichier sur le disque
            String filePath = "C:/Users/Ahmed/Desktop/mobility final" + UUID.randomUUID().toString() + ".png";
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(qrCodea);
            fos.close();

            // Enregistrer le QRCode dans la base de données
            QRCode1 qrCodez = new QRCode1(link, filePath);
            qrCodeRepository.save(qrCodez);
            return qrCodez;
        }}

    public byte[] getQRCodeImage (Long id) throws IOException {
        QRCode1 qrCode = qrCodeRepository.findById(id)
                .orElseThrow(() -> new UpdateOfferException("QRCode not found"));
        File file = new File(qrCode.getPath());
        FileInputStream fis = new FileInputStream(file);
        byte[] image = Files.readAllBytes(file.toPath());
        fis.close();
        return image;
    }

    @Override
    public QRCode1 findByLink(String link) {
        return null;
    }
}
