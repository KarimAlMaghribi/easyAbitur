package de.l.stadtwerke.loga3jobofferservice.service;

import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
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
    private StellenausschreibungRepository sar;

    public void parseJobs(String URL) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File fXmlFile = new File("src/main/resources/logaTest.xml");
            Document doc = builder.parse(fXmlFile);
            //Document doc = builder.parse(URL);

            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("job");

            List<Stellenausschreibung> stellenListe = new ArrayList<Stellenausschreibung>();

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    Stellenausschreibung stelle = new Stellenausschreibung();

                    if (elem.getElementsByTagName("id") != null && elem.getElementsByTagName("id").item(0) != null) {
                        stelle.setStellenId(
                                elem.getElementsByTagName("id").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("referenceNumber") != null && elem.getElementsByTagName("referenceNumber").item(0) != null) {
                        stelle.setReferenceNumber(
                                elem.getElementsByTagName("referenceNumber").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("title") != null && elem.getElementsByTagName("title").item(0) != null) {
                        stelle.setTitle(
                                elem.getElementsByTagName("title").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("url") != null && elem.getElementsByTagName("url").item(0) != null) {
                        stelle.setUrl(
                                elem.getElementsByTagName("url").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("mandant") != null && elem.getElementsByTagName("mandant").item(0) != null) {
                        stelle.setMandant(
                                elem.getElementsByTagName("mandant").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("creationDate") != null && elem.getElementsByTagName("creationDate").item(0) != null) {
                        stelle.setCreationDate(
                                elem.getElementsByTagName("creationDate").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("publishFromDate") != null && elem.getElementsByTagName("publishFromDate").item(0) != null) {
                        stelle.setPublishFromDate(
                                elem.getElementsByTagName("publishFromDate").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("publishToDate") != null && elem.getElementsByTagName("publishToDate").item(0) != null) {
                        stelle.setPublishToDate(
                                elem.getElementsByTagName("publishToDate").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("location") != null && elem.getElementsByTagName("location").item(0) != null) {
                        stelle.setLocation(
                                elem.getElementsByTagName("location").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("country") != null && elem.getElementsByTagName("country").item(0) != null) {
                        stelle.setCountry(
                            elem.getElementsByTagName("country").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("hrConsultant") != null && elem.getElementsByTagName("hrConsultant").item(0) != null) {
                        stelle.setHrConsultant(
                                elem.getElementsByTagName("hrConsultant").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("content") != null && elem.getElementsByTagName("content").item(0) != null) {
                        stelle.setContent(
                                elem.getElementsByTagName("content").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("selection1") != null && elem.getElementsByTagName("selection1").item(0) != null) {
                        stelle.setSection1(
                                elem.getElementsByTagName("selection1").item(0).getTextContent()
                        );
                    }

                    if (elem.getElementsByTagName("selection2") != null && elem.getElementsByTagName("selection2").item(0) != null) {
                        stelle.setSection2(
                                elem.getElementsByTagName("selection2").item(0).getTextContent()
                        );
                    }

//                    if (elem.getElementsByTagName("selection3") != null && elem.getElementsByTagName("selection3").item(0) != null) {
//                        stelle.setSelection(
//                                elem.getElementsByTagName("selection1").item(0).getTextContent()
//                        );
//                    }

                    stellenListe.add(stelle);
                }
            }
            sar.saveAll(stellenListe);
        } catch (Exception ex) {
                System.out.println(ex);
        }
    }

}
