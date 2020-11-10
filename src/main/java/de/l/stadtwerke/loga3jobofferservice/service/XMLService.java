package de.l.stadtwerke.loga3jobofferservice.service;

import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class XMLService {

    @Autowired
    private StellenausschreibungRepository stellenausschreibungRepository;

    public List<Stellenausschreibung> parseJobs() {

        List<Stellenausschreibung> stellenListe = new ArrayList();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fXmlFile = new File("src/main/resources/logaTest.xml");
            Document doc = builder.parse(fXmlFile);
            //Document doc = builder.parse(URL);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("job");


            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Stellenausschreibung stelle = new Stellenausschreibung();

                    if (elem.getElementsByTagName("id") != null && elem.getElementsByTagName("id").item(0) != null) {
                        stelle.setStellenId(
                                elem.getElementsByTagName("id").item(0).getTextContent()
                        );
                    } else {
                        stelle.setStellenId("");
                    }

                    if (elem.getElementsByTagName("referenceNumber") != null && elem.getElementsByTagName("referenceNumber").item(0) != null) {
                        stelle.setReferenceNumber(
                                elem.getElementsByTagName("referenceNumber").item(0).getTextContent()
                        );
                    } else {
                        stelle.setReferenceNumber("");
                    }

                    if (elem.getElementsByTagName("title") != null && elem.getElementsByTagName("title").item(0) != null) {
                        stelle.setTitle(
                                elem.getElementsByTagName("title").item(0).getTextContent()
                        );
                    } else {
                        stelle.setTitle("");
                    }

                    if (elem.getElementsByTagName("url") != null && elem.getElementsByTagName("url").item(0) != null) {
                        stelle.setUrl(
                                elem.getElementsByTagName("url").item(0).getTextContent()
                        );
                    } else {
                        stelle.setUrl("");
                    }

                    if (elem.getElementsByTagName("mandant") != null && elem.getElementsByTagName("mandant").item(0) != null) {
                        stelle.setMandant(
                                elem.getElementsByTagName("mandant").item(0).getTextContent()
                        );
                    } else {
                        stelle.setMandant("");
                    }

                    if (elem.getElementsByTagName("creationDate") != null && elem.getElementsByTagName("creationDate").item(0) != null) {
                        stelle.setCreationDate(
                                elem.getElementsByTagName("creationDate").item(0).getTextContent()
                        );
                    } else {
                        stelle.setCreationDate("");
                    }

                    if (elem.getElementsByTagName("publishFromDate") != null && elem.getElementsByTagName("publishFromDate").item(0) != null) {
                        stelle.setPublishFromDate(
                                elem.getElementsByTagName("publishFromDate").item(0).getTextContent()
                        );
                    } else {
                        stelle.setPublishFromDate("");
                    }
                    if (elem.getElementsByTagName("shortName") != null && elem.getElementsByTagName("shortName").item(0) != null) {
                        stelle.setShortName(
                                elem.getElementsByTagName("shortName").item(0).getTextContent()
                        );
                    } else {
                        stelle.setPublishFromDate("");
                    }

                    if (elem.getElementsByTagName("publishToDate") != null && elem.getElementsByTagName("publishToDate").item(0) != null) {
                        stelle.setPublishToDate(
                                elem.getElementsByTagName("publishToDate").item(0).getTextContent()
                        );
                    } else {
                        stelle.setPublishToDate("");
                    }

                    if (elem.getElementsByTagName("location") != null && elem.getElementsByTagName("location").item(0) != null) {
                        stelle.setLocation(
                                elem.getElementsByTagName("location").item(0).getTextContent()
                        );
                    } else {
                        stelle.setLocation("");
                    }

                    if (elem.getElementsByTagName("country") != null && elem.getElementsByTagName("country").item(0) != null) {
                        stelle.setCountry(
                                elem.getElementsByTagName("country").item(0).getTextContent()
                        );
                    } else {
                        stelle.setCountry("");
                    }

                    if (elem.getElementsByTagName("hrConsultant") != null && elem.getElementsByTagName("hrConsultant").item(0) != null) {
                        stelle.setHrConsultant(
                                elem.getElementsByTagName("hrConsultant").item(0).getTextContent()
                        );
                    } else {
                        stelle.setHrConsultant("");
                    }

                    if (elem.getElementsByTagName("content") != null && elem.getElementsByTagName("content").item(0) != null) {
                        String content = elem.getElementsByTagName("content").item(0).getTextContent();

                        String contentCopy = new String(content);
                        stelle.setTaskHTML(
                                StringUtils.substringBetween(contentCopy, "<p>Ihre Aufgaben</p>","<p><br></p> <p>Unser Angebot</p> ")
                        );

                        stelle.setOfferHTML(
                                StringUtils.substringBetween(contentCopy, "<p>Unser Angebot</p>","<p>Ihr Profil</p>")
                        );

                        stelle.setProfileHTML(
                                StringUtils.substringBetween(contentCopy, "<p>Ihr Profil</p> <ul>","</ul>")
                        );
                        stelle.setAboutUsHTML(
                                "<p>Leipzigs Energiezukunft liegt uns am Herzen. Deshalb gehen wir bewusst neue Wege, um die Energiewende aktiv mitzugestalten. Mit neuen Ideen, Expertenwissen und viel Engagement sorgen unsere rund 1.200 Mitarbeiterinnen und Mitarbeiter dafür, dass Leipzig die Energie nicht ausgeht – heute nicht und auch nicht in Zukunft. Geben auch Sie Leipzig Energie.</p>"
                        );
                        stelle.setGoodToKnowHTML(
                                StringUtils.substringBetween(contentCopy, "<p>Gut zu wissen</p>","<p><br></p> <p>Ihre Aufgaben</p> ")
                        );

                    } else {
                    }

                    stellenListe.add(stelle);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return stellenListe;
    }

}
