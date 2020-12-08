package de.l.stadtwerke.loga3jobofferservice.repository;

import de.l.stadtwerke.loga3jobofferservice.model.LogoImage;
import de.l.stadtwerke.loga3jobofferservice.model.LogoState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogoImageRepository extends JpaRepository<LogoImage, String> {
    boolean existsLogoImageByIdentId(String identId);
    LogoImage findByLogoID(String identId);
}
