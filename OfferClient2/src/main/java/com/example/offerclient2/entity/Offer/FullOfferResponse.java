package com.example.offerclient2.entity.Offer;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullOfferResponse {


    private String title;
    private String image;
    @CreatedDate
    private LocalDate dateOffre;

    @CreatedDate
    private LocalDateTime offerCreationDate;
    private Integer nbreCandidats;
    @Enumerated(EnumType.STRING)
    private Profil profil;
    @Enumerated(EnumType.STRING)
    private Destination destination;

    @Enumerated(EnumType.STRING)
    private Duration duration;
    private String conditions;
    @Enumerated(EnumType.STRING)
    private Advantages advantages;

    List<Candidacy> candidacies;

}
