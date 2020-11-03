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

                    if (elem.getElementsByTagName("befristung") != null && elem.getElementsByTagName("befristung").item(0) != null) {
                        stelle.setBefristung(
                                elem.getElementsByTagName("befristung").item(0).getTextContent()
                        );
                    } else {
                        stelle.setBefristung("");
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

                    if (elem.getElementsByTagName("content1") != null && elem.getElementsByTagName("content1").item(0) != null) {
                        stelle.setContent1(
                                elem.getElementsByTagName("content1").item(0).getTextContent()
                        );
                    } else {
                        stelle.setContent1("");
                    }

                    if (elem.getElementsByTagName("content2") != null && elem.getElementsByTagName("content2").item(0) != null) {
                        stelle.setContent2(
                                elem.getElementsByTagName("content2").item(0).getTextContent()
                        );
                    } else {
                        stelle.setContent2("");
                    }

                    if (elem.getElementsByTagName("content3") != null && elem.getElementsByTagName("content3").item(0) != null) {
                        stelle.setContent3(
                                elem.getElementsByTagName("content3").item(0).getTextContent()
                        );
                    } else {
                        stelle.setContent3("");
                    }

                    if (elem.getElementsByTagName("content4") != null && elem.getElementsByTagName("content4").item(0) != null) {
                        stelle.setContent4(
                                elem.getElementsByTagName("content4").item(0).getTextContent()
                        );
                    } else {
                        stelle.setContent4("");
                    }

                    if (elem.getElementsByTagName("content5") != null && elem.getElementsByTagName("content5").item(0) != null) {
                        stelle.setContent5(
                                elem.getElementsByTagName("content5").item(0).getTextContent()
                        );
                    } else {
                        stelle.setContent5("");
                    }

                    if (elem.getElementsByTagName("offer1") != null && elem.getElementsByTagName("offer1").item(0) != null) {
                        stelle.setOffer1(
                                elem.getElementsByTagName("offer1").item(0).getTextContent()
                        );
                    } else {
                        stelle.setOffer1("");
                    }

                    if (elem.getElementsByTagName("offer2") != null && elem.getElementsByTagName("offer2").item(0) != null) {
                        stelle.setOffer2(
                                elem.getElementsByTagName("offer2").item(0).getTextContent()
                        );
                    } else {
                        stelle.setOffer2("");
                    }

                    if (elem.getElementsByTagName("offer3") != null && elem.getElementsByTagName("offer3").item(0) != null) {
                        stelle.setOffer3(
                                elem.getElementsByTagName("offer3").item(0).getTextContent()
                        );
                    } else {
                        stelle.setOffer3("");
                    }

                    if (elem.getElementsByTagName("offer4") != null && elem.getElementsByTagName("offer4").item(0) != null) {
                        stelle.setOffer4(
                                elem.getElementsByTagName("offer4").item(0).getTextContent()
                        );
                    } else {
                        stelle.setOffer4("");
                    }

                    if (elem.getElementsByTagName("offer5") != null && elem.getElementsByTagName("offer5").item(0) != null) {
                        stelle.setOffer5(
                                elem.getElementsByTagName("offer5").item(0).getTextContent()
                        );
                    } else {
                        stelle.setOffer5("");
                    }

                    if (elem.getElementsByTagName("selection1") != null && elem.getElementsByTagName("selection1").item(0) != null) {
                        stelle.setSelection1(
                                elem.getElementsByTagName("selection1").item(0).getTextContent()
                        );
                    } else {
                        stelle.setSelection1("");
                    }

                    if (elem.getElementsByTagName("selection2") != null && elem.getElementsByTagName("selection2").item(0) != null) {
                        stelle.setSelection2(
                                elem.getElementsByTagName("selection2").item(0).getTextContent()
                        );
                    } else {
                        stelle.setSelection2("");
                    }

                    if (elem.getElementsByTagName("selection3") != null && elem.getElementsByTagName("selection3").item(0) != null) {
                        stelle.setSelection3(
                                elem.getElementsByTagName("selection3").item(0).getTextContent()
                        );
                    } else {
                        stelle.setSelection3("");
                    }

                    if (elem.getElementsByTagName("selection4") != null && elem.getElementsByTagName("selection4").item(0) != null) {
                        stelle.setSelection4(
                                elem.getElementsByTagName("selection4").item(0).getTextContent()
                        );
                    } else {
                        stelle.setSelection4("");
                    }

                    if (elem.getElementsByTagName("selection5") != null && elem.getElementsByTagName("selection5").item(0) != null) {
                        stelle.setSelection5(
                                elem.getElementsByTagName("selection5").item(0).getTextContent()
                        );
                    } else {
                        stelle.setSelection5("");
                    }

                    if (elem.getElementsByTagName("verguetung") != null && elem.getElementsByTagName("verguetung").item(0) != null) {
                        stelle.setVerguetung(
                                elem.getElementsByTagName("verguetung").item(0).getTextContent()
                        );
                    } else {
                        stelle.setVerguetung("");
                    }

                    if (elem.getElementsByTagName("workTime") != null && elem.getElementsByTagName("workTime").item(0) != null) {
                        stelle.setWorkTime(
                                elem.getElementsByTagName("workTime").item(0).getTextContent()
                        );
                    } else {
                        stelle.setWorkTime("");
                    }

//                    if (elem.getElementsByTagName("selection3") != null && elem.getElementsByTagName("selection3").item(0) != null) {
//                        stelle.setSelection(
//                                elem.getElementsByTagName("selection1").item(0).getTextContent()
//                        );
//                    }

                    stellenListe.add(stelle);
                }

            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        return stellenListe;
    }

}
