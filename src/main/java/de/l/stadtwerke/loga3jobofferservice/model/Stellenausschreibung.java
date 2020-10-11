package de.l.stadtwerke.loga3jobofferservice.model;

import lombok.Builder;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.io.File;


@Entity
@Table(name = "stellen")
public class Stellenausschreibung {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String jobbezeichnung;

    private String kategorie;

    private String kontaktperson;

    private String aufgaben;


    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade=CascadeType.ALL)
    @JoinColumn(name = "files", nullable = true)
    private FileDB pdf;

    public Stellenausschreibung(String id, String jobbezeichnung, String kategorie, String kontaktperson, String aufgaben, FileDB pdf) {
        this.id = id;
        this.jobbezeichnung = jobbezeichnung;
        this.kategorie = kategorie;
        this.kontaktperson = kontaktperson;
        this.aufgaben = aufgaben;
        this.pdf = pdf;
    }

    public Stellenausschreibung() {}



    @Column(name = "jobbezeichnung", nullable = false)
    public String getJobbezeichnung() {
        return jobbezeichnung;
    }
    public void setJobbezeichnung(String jobbezeichnung) {
        this.jobbezeichnung = jobbezeichnung;
    }

    @Column(name = "kategorie", nullable = false)
    public String getKategorie() {
        return kategorie;
    }
    public void setKategorie(String type) {
        this.kategorie = type;
    }

    @Column(name = "kontaktperson", nullable = false)
    public String getKontaktperson() {
        return kontaktperson;
    }
    public void setKontaktperson(String kontaktperson) {
        this.kontaktperson = kontaktperson;
    }

    @Column(name = "aufgaben", nullable = false)
    public String getAufgaben() {
        return aufgaben;
    }
    public void setAufgaben(String aufgaben) {
        this.aufgaben = aufgaben;
    }

    public FileDB getPdf() {return pdf;}
    public void setPdf(FileDB pdf) {this.pdf = pdf;}


}
