package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.Stellenausschreibung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StellenausschreibungRepository extends JpaRepository<Stellenausschreibung, String> {
    //Stellenausschreibung findByFileDBid(String fileDBid);
    boolean existsStellenausschreibungByStellenId(String stellenId);
}

