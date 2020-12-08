package de.l.stadtwerke.loga3jobofferservice.repository;

import de.l.stadtwerke.loga3jobofferservice.model.Stellenausschreibung_old;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Stellenausschreibung_oldRepository extends JpaRepository<Stellenausschreibung_old, String> {
        //Stellenausschreibung findByFileDBid(String fileDBid);
    boolean existsStellenausschreibungByStellenId(String stellenId);
}
