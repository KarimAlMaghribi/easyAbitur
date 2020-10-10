package de.l.stadtwerke.loga3jobofferservice.controller;

import com.lowagie.text.DocumentException;
import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import de.l.stadtwerke.loga3jobofferservice.service.PdfService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loga3-joboffer-service")
public class StellenausschreibungController {

    @Autowired
    StellenausschreibungRepository stellenausschreibungRepository;

    @Autowired
    PdfService pdfService;

    @CrossOrigin(origins = "http://localhost:4200")
    @Transactional(readOnly=true)
    @GetMapping("/stellen")
    public List<Stellenausschreibung> getAllStellenausschreibungen(){
        return stellenausschreibungRepository.findAll();
    }

    @GetMapping("/stellen/{id}")
    public Stellenausschreibung getStellenausschreibungByID(@PathVariable Long id) throws NotFoundException {
        Optional<Stellenausschreibung> optionalStellenausschreibung = stellenausschreibungRepository.findById(""+id);
        if(optionalStellenausschreibung.isPresent()) {
            return optionalStellenausschreibung.get();
        }else {
            throw new NotFoundException("Stellenausschreibung mit der ID "+id+ " nicht gefunden.");
        }
    }

    @DeleteMapping("/stellen/{id}")
    public String deleteStellenausschreibung(@PathVariable Long id) throws NotFoundException {
       return stellenausschreibungRepository.findById(""+id)
                .map(stelle -> {
                    stellenausschreibungRepository.delete(stelle);
                    return "Das Löschen war erfolgreich!";
                }).orElseThrow(() -> new NotFoundException(("Die Stellenausschreibung wurde nicht gefunden")));
    }

    @PostMapping("/stellen")
    public Stellenausschreibung setStellenausschreibungen( @RequestBody(required=false)  Stellenausschreibung stelle) throws IOException, DocumentException {
        return pdfService.generatePdfandSave(stelle);
    }

    @Transactional(readOnly=true)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/download-pdf")
    public void downloadPDFResource(HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf().getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
