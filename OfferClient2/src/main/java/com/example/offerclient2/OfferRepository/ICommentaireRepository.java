package com.example.offerclient2.OfferRepository;

import com.example.offerclient2.entity.Offer.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentaireRepository extends JpaRepository<Commentaire,Integer> {
    List<Commentaire> findAll();
}
