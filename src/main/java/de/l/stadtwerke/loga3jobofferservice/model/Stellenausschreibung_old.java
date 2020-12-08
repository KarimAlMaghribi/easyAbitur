package de.l.stadtwerke.loga3jobofferservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Table(name = "stellen_old")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stellenausschreibung_old {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

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

    @Column(name = "mandant")
    private String mandant;

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

    @Column(name = "workTime")
    private String workTime;

    @Column(name = "befristung")
    private String befristung;

    @Column(name = "verguetung")
    private String verguetung;

    @Column(name = "content1")
    private String content1;

    @Column(name = "content2")
    private String content2;

    @Column(name = "content3")
    private String content3;

    @Column(name = "content4")
    private String content4;

    @Column(name = "content5")
    private String content5;

    @Column(name = "offer1")
    private String offer1;

    @Column(name = "offer2")
    private String offer2;

    @Column(name = "offer3")
    private String offer3;

    @Column(name = "offer4")
    private String offer4;

    @Column(name = "offer5")
    private String offer5;

    @Column(name = "selection1")
    private String selection1;

    @Column(name = "selection2")
    private String selection2;

    @Column(name = "selection3")
    private String selection3;

    @Column(name = "selection4")
    private String selection4;

    @Column(name = "selection5")
    private String selection5;

    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade= CascadeType.ALL)
    @JoinColumn(name = "files_id", nullable = true)
    private FileDB pdf;
}
