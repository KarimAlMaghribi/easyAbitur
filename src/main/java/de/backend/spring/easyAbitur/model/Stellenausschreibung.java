package de.backend.spring.easyAbitur.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stellen")
public class Stellenausschreibung {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn (name = "mandant")
    Mandant mandant;

    @Column(name = "stellenId", nullable = false)
    private String stellenId;

    @Column(name = "referenceNumber")
    private String referenceNumber;

    @Column(name = "shortName")
    private String shortName;

    @Column(name = "title")
    private String title;

    @Column(name = "url")
    private String url;

    @Column(name = "mandantName")
    private String mandantName;

    @Column(name = "creationDate")
    private String creationDate;

    @Column(name = "publishFromDate")
    private String publishFromDate;

    @Column(name = "publishToDate")
    private String publishToDate;

    @Column(name = "location")
    private String location;

    @Column(name = "country")
    private String country;

    @Column(name = "hrConsultant")
    private String hrConsultant;

    @Column(name = "ansprechpartner")
    private String ansprechpartner;

    @Column(name = "workTime")
    private String workTime;

    @Column(name = "befristung")
    private String befristung;

    @Column(name = "verguetung")
    private String verguetung;

    @Column(name = "aboutUsHTML", length = 2048)
    private String aboutUsHTML;

    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade=CascadeType.ALL)
    @JoinColumn(name = "files_id", nullable = true)
    private FileDB pdf;

    @Column(name = "titlePicture", length =2000000)
    private String titlePicture;

    @Column(name = "logoPicture", length =2000000)
    private String logoPicture;

    @Column(name = "gutZuWissen", length = 2048)
    private String gutZuWissen;

    @Column(name = "ihreAufgaben", length = 2048)
    private String ihreAufgaben;

    @Column(name = "unserAngebot", length = 2048)
    private String unserAngebot;

    @Column(name = "ihrProfil", length = 2048)
    private String ihrProfil;

}
