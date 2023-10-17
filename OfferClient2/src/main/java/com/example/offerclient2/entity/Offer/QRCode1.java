package com.example.offerclient2.entity.Offer;

import javax.persistence.*;



@Entity
@Table(name = "qr_codes")
public class QRCode1 {

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

    public QRCode1() {
    }

    public QRCode1(String link, String path ) {
        this.link = link;
        this.path = path;

    }

    // getters and setters

}
