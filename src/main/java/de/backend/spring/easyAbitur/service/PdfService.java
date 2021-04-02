package de.backend.spring.easyAbitur.service;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import de.backend.spring.easyAbitur.model.FileDB;
import de.backend.spring.easyAbitur.model.Mandant;
import de.backend.spring.easyAbitur.model.Stellenausschreibung;
import de.backend.spring.easyAbitur.repository.FileDBRepository;
import de.backend.spring.easyAbitur.repository.StellenausschreibungRepository;
import de.backend.spring.easyAbitur.repository.Stellenausschreibung_oldRepository;
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

    private static final String PDF_RESOURCES = "/static/";
    @Autowired
    private StellenausschreibungRepository stellenausschreibungRepository;

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private Stellenausschreibung_oldRepository stellenausschreibung_oldRepository;
    private SpringTemplateEngine templateEngine;
    @Autowired
    private XMLService xmlService;

    @Autowired
    public PdfService(StellenausschreibungRepository stellenausschreibungRepository, SpringTemplateEngine templateEngine) {
        this.stellenausschreibungRepository = stellenausschreibungRepository;
        this.templateEngine = templateEngine;
    }


   /* public Stellenausschreibung_old generatePdfandSave_old(Stellenausschreibung_old stellenausschreibung) throws IOException, DocumentException {
        boolean stelleExists = stellenausschreibung_oldRepository.existsStellenausschreibungByStellenId( stellenausschreibung.getStellenId());

        if(!stelleExists){
            Context context = getContext_old(stellenausschreibung);
            String html = loadAndFillTemplate(context);
            File file = renderPdf(html);
            MultipartFile multipartFile = new MockMultipartFile("stellenanzeige", new FileInputStream(file));
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            FileDB fileDB = new FileDB(fileName, multipartFile.getContentType(), multipartFile.getBytes());
            stellenausschreibung.setPdf(fileDB);
            stellenausschreibung_oldRepository.save(stellenausschreibung);
        }
        return stellenausschreibung;
    }
*/
    public Stellenausschreibung generatePdf(Mandant mandant, Stellenausschreibung stellenausschreibung) throws IOException, DocumentException {
        boolean stelleExists = stellenausschreibungRepository.existsStellenausschreibungByStellenId( stellenausschreibung.getStellenId());

        if(!stelleExists){
            stellenausschreibung.setLogoPicture(mandant.getLogoImage());
            Context context = getContext(stellenausschreibung);
            String html = loadAndFillTemplate(context);
            File file = renderPdf(html);
            MultipartFile multipartFile = new MockMultipartFile("stellenanzeige", new FileInputStream(file));
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            FileDB fileDB = new FileDB(fileName, multipartFile.getContentType(), multipartFile.getBytes());
            fileDBRepository.save(fileDB);
            stellenausschreibung.setPdf(fileDB);
        }
        return stellenausschreibung;
    }

    public Stellenausschreibung saveStelle(Mandant mandant, Stellenausschreibung stellenausschreibung) throws IOException, DocumentException {
        Stellenausschreibung stelle = generatePdf(mandant, stellenausschreibung);
        stellenausschreibungRepository.save(stelle);
        return stelle;
    }

    public File generatePdfdepricated(String id) throws IOException, DocumentException {
        List<Stellenausschreibung> stelle = stellenausschreibungRepository.findAll();
        Context context = getContext(stelle.get(0));
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("ausschreibung", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.getFontResolver().addFont("static/css/calibri.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(Stellenausschreibung stelle) {
        Context context = new Context();
        context.setVariable("stelle", stelle);
        return context;
    }

   /* private Context getContext_old(Stellenausschreibung_old stellenausschreibung) {
        Context context = new Context();
        context.setVariable("stelle", stellenausschreibung);
        return context;
    }
*/
    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("template2", context);
    }

}
