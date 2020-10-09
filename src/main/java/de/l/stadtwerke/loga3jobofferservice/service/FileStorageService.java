package de.l.stadtwerke.loga3jobofferservice.service;

import de.l.stadtwerke.loga3jobofferservice.model.FileDB;
import de.l.stadtwerke.loga3jobofferservice.repository.FileDBRepository;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

/*
* #########################################################################################################
* The File Storage Service will use FileDBRepository to provide following methods:
*        store(file): receives MultipartFile object, transform to FileDB object and save it to Database
*        getFile(id): returns a FileDB object by provided Id
*        getAllFiles(): returns all stored files as list of code>FileDB objects
* #########################################################################################################
*
* */

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    @Autowired
    private StellenausschreibungRepository stellenausschreibungRepository;

    public FileDB store(MultipartFile file, Long stellenangebotID) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileDB FileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        stellenausschreibungRepository.findById(""+stellenangebotID)
                .map(stelle -> {
                    stelle.setPdf(FileDB);
                    return stellenausschreibungRepository.save(stelle);
                });
        return fileDBRepository.save(FileDB);
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
