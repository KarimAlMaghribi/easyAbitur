package de.l.stadtwerke.loga3jobofferservice.service;

import com.lowagie.text.DocumentException;
import de.l.stadtwerke.loga3jobofferservice.model.FileDB;
import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.List;
import java.util.Objects;

@Service
public class PdfService {

    private static final String PDF_RESOURCES = "/pdf-resources/";
    private StellenausschreibungRepository stellenausschreibungRepository;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(StellenausschreibungRepository stellenausschreibungRepository, SpringTemplateEngine templateEngine) {
        this.stellenausschreibungRepository = stellenausschreibungRepository;
        this.templateEngine = templateEngine;
    }

    public Stellenausschreibung generatePdfandSave(Stellenausschreibung stellenausschreibung) throws IOException, DocumentException {
        Context context = getContext(stellenausschreibung);
        String html = loadAndFillTemplate(context);
        File file = renderPdf(html);
        MultipartFile multipartFile = new MockMultipartFile("stellenanzeige", new FileInputStream(file));
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileDB fileDB = new FileDB(fileName, multipartFile.getContentType(), multipartFile.getBytes());
        stellenausschreibung.setPdf(fileDB);
        return stellenausschreibungRepository.save(stellenausschreibung);
    }

    public File generatePdf(String id) throws IOException, DocumentException {
        List<Stellenausschreibung> stelle = stellenausschreibungRepository.findAll();
        Context context = getContext(stelle.get(0));
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("ausschreibung", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(Stellenausschreibung stellenausschreibung) {
        Context context = new Context();
        context.setVariable("stelle", stellenausschreibung);
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("mockup", context);
    }


}