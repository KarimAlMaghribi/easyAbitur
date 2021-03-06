package de.backend.spring.easyAbitur.controller;

import com.lowagie.text.DocumentException;
import de.backend.spring.easyAbitur.model.FileDB;
import de.backend.spring.easyAbitur.model.Mandant;
import de.backend.spring.easyAbitur.model.Stellenausschreibung;
import de.backend.spring.easyAbitur.model.Stellenausschreibung_old;
import de.backend.spring.easyAbitur.service.*;
import de.backend.spring.easyAbitur.repository.MandantRepository;
import de.backend.spring.easyAbitur.repository.StellenausschreibungRepository;
import de.backend.spring.easyAbitur.repository.Stellenausschreibung_oldRepository;
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
    Stellenausschreibung_oldRepository stellenausschreibung_oldRepository;

    @Autowired
    MandantRepository mandantRepository;

    @Autowired
    PdfService pdfService;

    @Autowired
    MandantService mandantService;

    @Autowired
    DocxGeneratorService docxGeneratorService;

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private XMLService xmlService;

    @Autowired
    private XMLService_old xmlService_old;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/stellen")
    public List<Stellenausschreibung> getAllStellenausschreibungen() throws IOException, DocumentException {
        return stellenausschreibungRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/stellenOld")
    public List<Stellenausschreibung_old> getAllOldStellenausschreibungen() throws IOException, DocumentException {
        return stellenausschreibung_oldRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/stellen/xml/{id}")
    public List<Stellenausschreibung> getAllXMLStellenausschreibungen(@PathVariable String id) throws IOException, DocumentException {

        xmlService.parseJobsForMandant(id);

        Mandant mandant = mandantService.getMandant(id);

        return mandant.getStellenausschreibungList();
    }

  /*  @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/stellenOld/xml")
    public List<Stellenausschreibung_old> getAllOLDXMLStellenausschreibungen() throws IOException, DocumentException {
        List<Stellenausschreibung_old> stellenListe =  xmlService_old.parseJobs();
        for ( Stellenausschreibung_old stelle: stellenListe){
            this.pdfService.generatePdfandSave_old(stelle);
        }
        return stellenausschreibung_oldRepository.findAll();
    }
*/
    @GetMapping("/stellen/{id}")
    public Stellenausschreibung getStellenausschreibungByID(@PathVariable String id) throws NotFoundException {
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
                    return "Das L??schen war erfolgreich!";
                }).orElseThrow(() -> new NotFoundException(("Die Stellenausschreibung wurde nicht gefunden")));
    }

   /* @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/stellenOld")
    public Stellenausschreibung_old setOldStellenausschreibungen( @RequestBody(required=false)  Stellenausschreibung_old stelle) throws Exception {
        return pdfService.generatePdfandSave_old(stelle);
    }
*/
//    @CrossOrigin(origins = "http://localhost:4200")
//    @PostMapping("/stellen")
//    public Stellenausschreibung setStellenausschreibungen( @RequestBody(required=false)  Stellenausschreibung stelle) throws Exception {
//        return pdfService.saveStelle(stelle);
//    }

}
