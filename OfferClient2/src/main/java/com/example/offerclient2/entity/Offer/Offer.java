package com.example.offerclient2.entity.Offer;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Offer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOffre;

    private String title;
    private String image;
    @CreatedDate
    private LocalDate dateOffre;

    @CreatedDate
    private LocalDateTime offerCreationDate;

    public void setOfferCreationDate(LocalDateTime offerCreationDate) {
        this.offerCreationDate = offerCreationDate;
    }

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



    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ToString.Exclude
    @OneToMany(mappedBy = "offer", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Commentaire> commentaires = new LinkedHashSet<>();

//    @Getter(AccessLevel.NONE)
//    @Setter(AccessLevel.NONE)
//    @ToString.Exclude
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;


    public Set<Commentaire> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(Set<Commentaire> commentaires) {
        this.commentaires = commentaires;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Set<Candidacy> getCandidacies() {
//        return candidacies;
//    }
//
//    public void setCandidacies(Set<Candidacy> candidacies) {
//        this.candidacies = candidacies;
//    }

}
