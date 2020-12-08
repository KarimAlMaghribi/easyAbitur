package de.l.stadtwerke.loga3jobofferservice.repository;

import de.l.stadtwerke.loga3jobofferservice.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    boolean existsFileDBByIdentId(String identId);
    FileDB findByIdentId(String identId);
}
