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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import java.util.Set;

/**
 * Created by Arpit Khandelwal.
 */
@Getter
@Setter
@NoArgsConstructor
//@Accessors(chain = true)
@Document(collection = "stellenausschreibung")
public class Stellenausschreibung {
}
