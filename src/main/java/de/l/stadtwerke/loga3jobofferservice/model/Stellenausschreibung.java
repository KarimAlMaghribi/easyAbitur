package de.l.stadtwerke.loga3jobofferservice.model;

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

    @Column(name = "taskHTML", length = 2048)
    private String taskHTML;

    @Column(name = "offerHTML", length = 2048)
    private String offerHTML;

    @Column(name = "profileHTML", length = 2048)
    private String profileHTML;

    @Column(name = "goodToKnowHTML", length = 2048)
    private String goodToKnowHTML;

    @Column(name = "aboutUsHTML", length = 2048)
    private String aboutUsHTML;

    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade=CascadeType.ALL)
    @JoinColumn(name = "files", nullable = true)
    private FileDB pdf;
}
