package de.l.stadtwerke.loga3jobofferservice.controller;

import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loga3-joboffer-service/")
public class StellenausschreibungController {
    @Autowired
    StellenausschreibungRepository stellenausschreibungRepository;

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
}
