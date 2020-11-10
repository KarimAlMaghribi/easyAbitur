package de.l.stadtwerke.loga3jobofferservice.service;

import de.l.stadtwerke.loga3jobofferservice.model.FileDB;
import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import fr.opensagres.xdocreport.converter.*;
import fr.opensagres.xdocreport.core.document.DocumentKind;
import org.docx4j.Docx4J;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DocxGeneratorService {
    private static final String TEMPLATE_NAME = "mockup.docx";
    @Autowired
    private StellenausschreibungRepository stellenausschreibungRepository;

    public Stellenausschreibung generatePDFFileFromTemplate(Stellenausschreibung stelle) throws Exception {

        File initialFile = new File("src\\main\\resources\\mockup.docx");
        //boolean idontknow = initialFile.exists();
        InputStream targetStream = new FileInputStream(initialFile);

        //InputStream templateInputStream = this.getClass().getResourceAsStream(TEMPLATE_NAME);
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(targetStream);

        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        VariablePrepare.prepare(wordMLPackage);

        HashMap<String, String> variables = new HashMap<>();

        documentPart.variableReplace(variables);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //wordMLPackage.save(outputStream);
        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        ByteArrayInputStream inStream = new ByteArrayInputStream( outputStream.toByteArray() );

        Options options = Options.getFrom( DocumentKind.DOCX ).to( ConverterTypeTo.PDF ).via( ConverterTypeVia.DOCX4J );
        ConverterRegistry c = ConverterRegistry.getRegistry();
        IConverter converter  = ConverterRegistry.getRegistry().getConverter(options);
        Docx4J.toPDF(wordMLPackage, pdfStream);
        /*try {
            converter .convert(inStream, pdfStream, options);
        } catch (XDocConverterException e) {
            throw new Exception("Error", e);
        }
*/
        MultipartFile multipartFile = new MockMultipartFile("stellenanzeige", pdfStream.toByteArray());
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileDB fileDB = new FileDB(fileName, multipartFile.getContentType(), multipartFile.getBytes());
        stelle.setPdf(fileDB);
        return stellenausschreibungRepository.save(stelle);
    }

}
