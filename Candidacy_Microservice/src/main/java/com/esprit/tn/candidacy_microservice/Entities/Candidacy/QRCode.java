package com.esprit.tn.candidacy_microservice.Entities.Candidacy;



import javax.persistence.*;



@Entity
@Table(name = "qr_codes")
public class QRCode {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "link")
    private String link;

    @Column(name = "path")
    private String path;

    private String name;

    public QRCode() {
    }

    public QRCode(String link, String path ) {
        this.link = link;
        this.path = path;

    }

}