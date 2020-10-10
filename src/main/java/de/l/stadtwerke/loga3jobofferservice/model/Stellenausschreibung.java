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

    private String name;

    private String type;

    @OneToOne(fetch = FetchType.LAZY, optional = true, cascade=CascadeType.ALL)
    @JoinColumn(name = "files", nullable = true)
    private FileDB pdf;

    public Stellenausschreibung(String name, String type, FileDB pdf) {
        this.name = name;
        this.type = type;
        this.pdf = pdf;
    }

    public Stellenausschreibung() {}



    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public FileDB getPdf() {return pdf;}
    public void setPdf(FileDB pdf) {this.pdf = pdf;}


}
