package de.l.stadtwerke.loga3jobofferservice.model;

/*
* Titel	Feld title
Freitext (optional)	(Haken)
Liste mit Aufgaben "Ihre Aufgaben"	(Haken)
Angebote seitens des jeweiligen BU	(Fehler) (sind die ggf. nicht immer vorhanden?)
Profil des Bewerbers - "
Auf diese Aufgaben sind Sie gut vorbereitet, wenn Sie folgende Voraussetzungen

erfüllen"

(Haken)
Footer des jeweiligen BU	(Fehler)
Short Note "Gut zu wissen"	(Fehler) (sind die ggf. nicht immer vorhanden?)
Ansprechpartner	(Haken) (schwer zu parsen)
Link zu Bewerbungsportal des jeweiligen BU
*
* */


import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;


@Entity
@Table(name = "stellenausschreibung")
public class Stellenausschreibung {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private Long id;

    private String name;

    private String type;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pdf", nullable = false)
    private FileDB pdf;

    public Stellenausschreibung(long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Stellenausschreibung() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

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
