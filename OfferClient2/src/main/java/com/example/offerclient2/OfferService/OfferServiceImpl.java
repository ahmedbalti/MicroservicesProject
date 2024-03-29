package com.example.offerclient2.OfferService;


import com.example.offerclient2.OfferRepository.IOfferRepository;
import com.example.offerclient2.OpenFeign.CandidacyClient;
import com.example.offerclient2.entity.Offer.Destination;
import com.example.offerclient2.entity.Offer.FullOfferResponse;
import com.example.offerclient2.entity.Offer.Offer;
import com.example.offerclient2.entity.Offer.Profil;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Service
@Slf4j
public class OfferServiceImpl implements IOfferService {
    @Autowired
    private IOfferRepository offerRepository;

    @Autowired
    private  CandidacyClient client;

    //    @Autowired
//    private UserRepository userRepository;
    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    @Override
    public Offer getOfferById(Integer id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Offer not found with id " + id));
    }

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }


    @Override
    public Offer updateOffer(Integer id, Offer offerDetails) {
        Offer offer = getOfferById(id);
        offer.setTitle(offerDetails.getTitle());
        offer.setImage(offerDetails.getImage());
        offer.setDateOffre(offerDetails.getDateOffre());
        offer.setNbreCandidats(offerDetails.getNbreCandidats());
        offer.setProfil(offerDetails.getProfil());
        offer.setDestination(offerDetails.getDestination());
        offer.setDuration(offerDetails.getDuration());
        offer.setConditions(offerDetails.getConditions());
        offer.setAdvantages(offerDetails.getAdvantages());


        return offerRepository.save(offer);
    }





    @Override
    public void deleteOffer(Integer id) {
        offerRepository.deleteById(id);
    }

    public List<Offer> getOffersByTitle(String title) {
        return offerRepository.findByTitle(title);
    }

    @Override
    public List<Offer> trierParDate() {
        List<Offer> offers = offerRepository.findAll();
        Collections.sort(offers, new Comparator<Offer>() {
            @Override
            public int compare(Offer c1, Offer c2) {
                return c1.getDateOffre().compareTo(c2.getDateOffre());
            }
        });
        return offers;
    }

    @Override
    public void archiveOffer(Integer id) {
        Offer offer = getOfferById(id);
        offerRepository.delete(offer);

        try {
            FileWriter fileWriter = new FileWriter("C:/Users/Ahmed/Desktop/5ème année/offers_archivées.txt", true);
            fileWriter.write(offer.getIdOffre() + "," + offer.getTitle() + "," +
                    offer.getImage() + "," + offer.getNbreCandidats() + "," +
                    offer.getNbreCandidats() + "," +
                    offer.getConditions() + "," +
                    offer.getDestination() + "," +
                    offer.getDuration() + "," +
                    offer.getDateOffre() + "," +
                    offer.getProfil() + "," +
                    offer.getAdvantages() + "\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Override
//    public void exportOffersToExcel(HttpServletResponse response) {
//        try {
//            List<Offer> offers = offerRepository.findAll();
//
//            // Création d'un nouveau classeur Excel
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            // Création d'une nouvelle feuille dans le classeur
//            XSSFSheet sheet = workbook.createSheet("Offers");
//
//            // Création d'une ligne pour les titres des colonnes
//            XSSFRow headerRow = sheet.createRow(0);
//            headerRow.createCell(0).setCellValue("ID");
//            headerRow.createCell(1).setCellValue("Title");
//            headerRow.createCell(2).setCellValue("Image");
//            headerRow.createCell(3).setCellValue("nbreCandidats");
//            headerRow.createCell(4).setCellValue("conditions");
//
//
//
//            // Remplissage des données des offres
//            int rowNum = 1;
//            for (Offer offer : offers) {
//                XSSFRow row = sheet.createRow(rowNum++);
//                row.createCell(0).setCellValue(offer.getIdOffre());
//                row.createCell(1).setCellValue(offer.getTitle());
//                row.createCell(2).setCellValue(offer.getImage());
//                row.createCell(3).setCellValue(offer.getNbreCandidats());
//                row.createCell(4).setCellValue(offer.getConditions());
//
//
//
//
//            }
//
//            // Configuration de l'en-tête de la réponse HTTP
//            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
//            response.setHeader("Content-Disposition", "attachment; filename=\"offers.xlsx\"");
//
//            // Écriture du classeur dans le flux de sortie HTTP
//            ServletOutputStream outputStream = response.getOutputStream();
//            workbook.write(outputStream);
//            outputStream.close();
//            workbook.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } }

    @Override
    public Page<Offer> paginationOffers(int pageNumber, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return offerRepository.findAll(pageable);
    }

    @Override
    public List<Offer> getOffresDateSuperieur(LocalDate date) {
        return offerRepository.getOffresDateSuperieur(date);
    }

    @Override
    public Map<Destination, Long> getNombreOffresParDestination() {
        List<Offer> offres = offerRepository.findAll();
        return offres.stream().collect(Collectors.groupingBy(Offer::getDestination, Collectors.counting()));
    }

    @Override
    public List<Offer> getOffresEnseignant() {
        return offerRepository.findByProfil(Profil.ENSEIGNANT);
    }

    @Override
    public List<Offer> getOffresEtudiant() {
        return offerRepository.findByProfil(Profil.ETUDIANT);
    }

//    @Override
//    public List<Offer> getAllOffers1() {
//        return offerRepository.findAll();
//    }

//    @Override
//    public List<Offer> getOfferByUser(Long userId){
//        return userRepository.findById(userId).get().getOffers().stream().toList();
//    }

//    @Override
//    public void createOffer2(Offer offer, Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new UpdateOfferException("object not found with id =" + userId));
//        user.getOffers().add(offer);
//        //offer.setAlertCreationDate(LocalDateTime.now());
//        offerRepository.save(offer);
//        userRepository.save(user);
//    }

//    public boolean getUseridByOfferid(User user, int id){
//        return user.getOffers().contains(offerRepository.findById(id).get());
//    }

    public Optional<Offer> findById(Integer id) {
        return offerRepository.findById(id);
    }
    public Offer save(Offer employee) {
        return offerRepository.save(employee);
    }

    public FullOfferResponse findOffersWithCandidacies(Integer offreId) {
        var offer = offerRepository.findById(offreId)
                .orElse(
                        Offer.builder()
                                .title("NOT_FOUND")
                                .image("NOT_FOUND")
                                .conditions("NOT_FOUND")

                                .build()
                );
        var candidacies = client.findAllCandidaciesByOffer(offreId);
        return FullOfferResponse.builder()
                .title(offer.getTitle())
                .image(offer.getImage())
                .conditions(offer.getConditions())

                .candidacies(candidacies)
                .build();
    }

}