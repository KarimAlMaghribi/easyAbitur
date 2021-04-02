package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.FileDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
    boolean existsFileDBByIdentId(String identId);
    FileDB findByIdentId(String identId);
}
