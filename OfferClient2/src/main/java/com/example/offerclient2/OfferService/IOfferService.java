package com.example.offerclient2.OfferService;



import com.example.offerclient2.entity.Offer.Destination;
import com.example.offerclient2.entity.Offer.Offer;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IOfferService {
    List<Offer> getAllOffers();
    Offer getOfferById(Integer id);
    Offer createOffer(Offer offer);
    Offer updateOffer(Integer id, Offer offerDetails);
    void deleteOffer(Integer id);
    List<Offer> getOffersByTitle(String title);
    List<Offer> trierParDate();
    void archiveOffer(Integer id);
 //   void exportOffersToExcel(HttpServletResponse response);
    Page<Offer> paginationOffers(int pageNumber, int pageSize, String sortBy);
    List<Offer> getOffresDateSuperieur(LocalDate date);
    Map<Destination, Long> getNombreOffresParDestination();
    List<Offer> getOffresEnseignant();
    List<Offer> getOffresEtudiant();
    //    public List<Offer> getAllOffers1();
   // List<Offer> getOfferByUser(Long userId);

//    void createOffer2(Offer offer, Long userId);
   // boolean getUseridByOfferid(User user, int id);

    Optional<Offer> findById(Integer id);
    Offer save(Offer employee);















}