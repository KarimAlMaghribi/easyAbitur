package de.l.stadtwerke.loga3jobofferservice.repository;

import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StellenausschreibungRepository extends JpaRepository<Stellenausschreibung, String> {
    //Stellenausschreibung findByFileDBid(String fileDBid);
}
