package de.l.stadtwerke.loga3jobofferservice.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "stellen")
public class Stellenausschreibung {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String referenceNumber;

    private String title;

    private String url;

    private String mandant;

    private String creationDate;

    private String publishFromDate;

    private String publishToDate;

    private String location;

    private String country;

    private String hrConsultant;

    private String content;

    private String section1;

    private String section2;


    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade=CascadeType.ALL)
    @JoinColumn(name = "files", nullable = true)
    private FileDB pdf;

    public Stellenausschreibung(String id,
                                String referenceNumber,
                                String title,
                                String url,
                                String mandant,
                                String creationDate,
                                String publishFromDate,
                                String publishToDate,
                                String location,
                                String country,
                                String hrConsultant,
                                String content,
                                String section1,
                                String section2,
                                FileDB pdf) {
        this.id = id;
        this.referenceNumber = referenceNumber;
        this.title = title;
        this.url = url;
        this.mandant = mandant;
        this.creationDate = creationDate;
        this.publishFromDate = publishFromDate;
        this.publishToDate = publishToDate;
        this.location = location;
        this.country = country;
        this.hrConsultant = hrConsultant;
        this.content = content;
        this.section1 = section1;
        this.section2 = section2;
        this.pdf = pdf;
    }

    public Stellenausschreibung() {}



    @Column(name = "referenceNumber", nullable = false)
    public String getReferenceNumber() {
        return referenceNumber;
    }
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.url = title;
    }

    @Column(name = "url", nullable = false)
    public String getUrl() {
        return url;
    }
    public void setUrl(String type) {
        this.url = type;
    }

    @Column(name = "mandant", nullable = false)
    public String getMandant() {
        return mandant;
    }
    public void setMandant(String mandant) {
        this.mandant = mandant;
    }

    @Column(name = "creationDate", nullable = false)
    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "publishFromDate", nullable = false)
    public String getPublishFromDate() {
        return publishFromDate;
    }
    public void setPublishFromDate(String publishFromDate) {
        this.publishFromDate = publishFromDate;
    }

    @Column(name = "publishToDate", nullable = false)
    public String getPublishToDate() {
        return publishToDate;
    }
    public void setPublishToDate(String publishToDate) {
        this.publishToDate = publishToDate;
    }

    @Column(name = "location", nullable = false)
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "country", nullable = false)
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    @Column(name = "hrConsultant", nullable = false)
    public String getHrConsultant() {
        return hrConsultant;
    }
    public void setHrConsultant(String hrConsultant) {
        this.hrConsultant = hrConsultant;
    }

    @Column(name = "content", nullable = false)
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "section1", nullable = false)
    public String getSection1() {
        return section1;
    }
    public void setSection1(String section1) {
        this.section1 = section1;
    }

    @Column(name = "section2", nullable = false)
    public String getSection2() {
        return section2;
    }
    public void setSection2(String section2) {
        this.section2 = section2;
    }

    public FileDB getPdf() {return pdf;}
    public void setPdf(FileDB pdf) {this.pdf = pdf;}


}
