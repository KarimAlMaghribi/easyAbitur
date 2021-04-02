package de.backend.spring.easyAbitur.service;

import de.backend.spring.easyAbitur.model.EMandant;
import de.backend.spring.easyAbitur.model.LogoImage;
import de.backend.spring.easyAbitur.model.Mandant;
import de.backend.spring.easyAbitur.model.Stellenausschreibung;
import de.backend.spring.easyAbitur.repository.MandantRepository;
import de.backend.spring.easyAbitur.repository.StellenausschreibungRepository;
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
import java.util.Base64;
import java.util.List;

@Service
public class XMLService {

    @Autowired
    private MandantRepository mandantRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private StellenausschreibungRepository stellenausschreibungRepository;

    @Autowired PdfService pdfService;

    private static EMandant getMandant(String text) {
        for (EMandant b : EMandant.values()) {
            if (b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    private static String getURL(String id) {
        String url = new String();
        if(id == "Netz") {
            url = "https://mitarbeiterportal.l.lecos.de/bewerber-web/?companyEid=NETZE&tenant=&lang=D#positions?company=NETZE-FIRMA-ID&tenant=&lang=D#position,id=b2c305d8-aca1-4cec-b04c-d783cffd8388,jobportalid=f162fa4f-1eeb-47f2-96a4-d78fe47323ca";
        } else if (id == "LSW"){
            url = "https://mitarbeiterportal.l.lecos.de/bewerber-web/public/JobPortalData?companyEid=LSW&jobPortal=HOMEPAGE/bewerber-web/?companyEid=LSW&lang=D?company=LSW-FIRMA-ID&tenant=&lang=D#position,id=7b5f181c-4877-406c-b8c8-cd529486fb66,jobportalid=f162fa4f-1eeb-47f2-96a4-d78fe47323ca";
        }
        return url;
    }

    private static File getXMLFile(String id) {
        String xml_Path = new String();
        if(id.equals("Netz")) {
            xml_Path = "src/main/resources/logaTest_Netze.xml";
        } else if (id.equals("LSW")){
            xml_Path = "src/main/resources/logaTest_LSW.xml";
        }
        return new File(""+xml_Path);
    }

    public Mandant parseJobsForMandant(String id) {

        Mandant mandant = new Mandant();
        LogoImage logoImage = new LogoImage();
        List<Stellenausschreibung> stellenListe = new ArrayList();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            File fXmlFile = getXMLFile(id);

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

                    if (elem.getElementsByTagName("selection1") != null && elem.getElementsByTagName("selection1").item(0) != null) {
                        stelle.setTitlePicture(
                                Base64.getEncoder().encodeToString(fileStorageService.getTitleImage(elem.getElementsByTagName("selection1").item(0).getTextContent()).getData())
                        );
                    } else {
                        stelle.setTitlePicture(null);
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
                        stelle.setUrl(elem.getElementsByTagName("url").item(0).getTextContent());
                    } else {
                        stelle.setUrl("");
                    }

                    if (elem.getElementsByTagName("mandant") != null && elem.getElementsByTagName("mandant").item(0) != null) {
                        String mandantName =  elem.getElementsByTagName("mandant").item(0).getTextContent();
                        stelle.setMandantName( mandantName );

                        logoImage = fileStorageService.getLogoImage(mandantName);
                        mandant.setLogoImage(Base64.getEncoder().encodeToString(fileStorageService.getLogoImage(mandantName).getData()));
                        mandant.setName(getMandant(mandantName));

                    } else {
                        stelle.setMandantName("");
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

                    if (elem.getElementsByTagName("text1") != null && elem.getElementsByTagName("text1").item(0) != null) {
                        stelle.setAnsprechpartner(
                                elem.getElementsByTagName("text1").item(0).getTextContent()
                        );
                    } else {
                        stelle.setAnsprechpartner("");
                    }

                    if (elem.getElementsByTagName("longtext1") != null && elem.getElementsByTagName("longtext1").item(0) != null) {
                        String rawText = elem.getElementsByTagName("longtext1").item(0).getTextContent();
                        String parsedText = parseText(rawText);
                        stelle.setGutZuWissen(parsedText);
                    } else {
                        stelle.setGutZuWissen("");
                    }

                    if (elem.getElementsByTagName("longtext2") != null && elem.getElementsByTagName("longtext2").item(0) != null) {
                        String rawText = elem.getElementsByTagName("longtext2").item(0).getTextContent();
                        String parsedText = parseText(rawText);
                        stelle.setIhreAufgaben(parsedText);
                    } else {
                        stelle.setIhreAufgaben("");
                    }

                    if (elem.getElementsByTagName("longtext3") != null && elem.getElementsByTagName("longtext3").item(0) != null) {
                        String rawText = elem.getElementsByTagName("longtext3").item(0).getTextContent();
                        String parsedText = parseText(rawText);
                        stelle.setUnserAngebot(parsedText);
                    } else {
                        stelle.setUnserAngebot("");
                    }

                    if (elem.getElementsByTagName("longtext4") != null && elem.getElementsByTagName("longtext4").item(0) != null) {
                        String rawText = elem.getElementsByTagName("longtext4").item(0).getTextContent();
                        String parsedText = parseText(rawText);
                        stelle.setIhrProfil(parsedText);
                    } else {
                        stelle.setIhrProfil("");
                    }
                    stelle.setAboutUsHTML(
                            "<p>Leipzigs Energiezukunft liegt uns am Herzen. Deshalb gehen wir bewusst neue Wege, um die Energiewende aktiv mitzugestalten. Mit neuen Ideen, Expertenwissen und viel Engagement sorgen unsere rund 1.200 Mitarbeiterinnen und Mitarbeiter dafür, dass Leipzig die Energie nicht ausgeht – heute nicht und auch nicht in Zukunft. Geben auch Sie Leipzig Energie.</p>"
                    );
                    boolean stelleExists = stellenausschreibungRepository.existsStellenausschreibungByStellenId( stelle.getStellenId());

                    if(!stelleExists) {
                        Stellenausschreibung stellenausschreibung = pdfService.generatePdf(mandant, stelle);
                        stellenListe.add(stellenausschreibung);

                    }
                }
                mandant.setStellenausschreibungList(stellenListe);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }

        mandantRepository.save(mandant);
        return mandant;
    }

    private String parseText(String rawText) {
        String[] bulletPoints = rawText.split("#");
        List<String> htmlBulletPoints = new ArrayList();
        for (String bulletPoint : bulletPoints) {
            if(!bulletPoint.equals("")){
                bulletPoint = "<li>" + bulletPoint.trim() + "</li>";
                htmlBulletPoints.add(bulletPoint);
            }
        }
        String parsedText = "<ul>" + String.join("", htmlBulletPoints) + "</ul>";
        return parsedText;
    }

}
