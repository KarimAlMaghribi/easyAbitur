package de.l.stadtwerke.loga3jobofferservice.controller;

import com.lowagie.text.DocumentException;
import de.l.stadtwerke.loga3jobofferservice.model.FileDB;
import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import de.l.stadtwerke.loga3jobofferservice.service.DocxGeneratorService;
import de.l.stadtwerke.loga3jobofferservice.service.FileStorageService;
import de.l.stadtwerke.loga3jobofferservice.service.PdfService;
import de.l.stadtwerke.loga3jobofferservice.service.XMLService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loga3-joboffer-service")
public class StellenausschreibungController {

    @Autowired
    StellenausschreibungRepository stellenausschreibungRepository;

    @Autowired
    PdfService pdfService;

    @Autowired
    DocxGeneratorService docxGeneratorService;

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private XMLService xmlService;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/stellen")
    public List<Stellenausschreibung> getAllStellenausschreibungen() throws IOException, DocumentException {
        return stellenausschreibungRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/stellen/xml")
    public List<Stellenausschreibung> getAllXMLStellenausschreibungen() throws IOException, DocumentException {
        List<Stellenausschreibung> stellenListe =  xmlService.parseJobs();
        for ( Stellenausschreibung stelle: stellenListe){
            this.pdfService.generatePdfandSave(stelle);
        }
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/stellen")
    public Stellenausschreibung setStellenausschreibungen( @RequestBody(required=false)  Stellenausschreibung stelle) throws Exception {
        return pdfService.generatePdfandSave(stelle);
    }

    @Transactional(readOnly=true)
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/download-pdf/{id}")
    public ResponseEntity<byte[]> downloadPDFResource(HttpServletResponse response, @PathVariable String id) {

        FileDB fileDB = storageService.getFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());

    }
}
