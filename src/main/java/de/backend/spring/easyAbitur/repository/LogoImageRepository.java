package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.LogoImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface LogoImageRepository extends JpaRepository<LogoImage, String> {
    boolean existsLogoImageByIdentId(String identId);
    LogoImage findByLogoID(String identId);
    @Transactional
    void deleteByLogoID(String identId);
}
