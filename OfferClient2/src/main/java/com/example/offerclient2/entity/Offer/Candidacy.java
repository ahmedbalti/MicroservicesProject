package com.example.offerclient2.entity.Offer;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Candidacy {

    private String cv;
    private String coverLetter;
    private String certificate;
    private String firstName;
    private String lastName;

    @Email
    private String email;

    @CreatedDate
    private LocalDate dateCandidacy;

    private int telephoneNumber;

    private String address;

    private int postalCode;

    private float moyenneGenerale;

    private float scoree;

    private int anneeExperience;

    @Enumerated(EnumType.STRING)
    private DomainCandidacy domainCandidacy;

    @Enumerated(EnumType.STRING)
    private StatusCandidacy statusCandidacy;

    @Enumerated(EnumType.STRING)
    private Disponibilite disponibilite;
}
