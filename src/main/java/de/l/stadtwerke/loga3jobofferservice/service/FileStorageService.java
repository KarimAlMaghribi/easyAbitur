package de.l.stadtwerke.loga3jobofferservice.service;

import de.l.stadtwerke.loga3jobofferservice.model.FileDB;
import de.l.stadtwerke.loga3jobofferservice.model.LogoImage;
import de.l.stadtwerke.loga3jobofferservice.model.LogoState;
import de.l.stadtwerke.loga3jobofferservice.model.TitleImage;
import de.l.stadtwerke.loga3jobofferservice.repository.FileDBRepository;
import de.l.stadtwerke.loga3jobofferservice.repository.LogoImageRepository;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import de.l.stadtwerke.loga3jobofferservice.repository.TitleImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
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
    private LogoImageRepository logoImageRepository;

    @Autowired
    private TitleImageRepository titleImageRepository;

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

    public void storeLogoFile
            (MultipartFile file, String ident_Id) throws IOException {
        if(!logoImageRepository.existsLogoImageByIdentId(ident_Id)){
            LogoImage logoImage= new LogoImage();
            logoImage.setName(file.getName());
            logoImage.setLogoID(ident_Id);
            logoImage.setData(file.getBytes());
            logoImage.setType(file.getContentType());
            logoImageRepository.save(logoImage);
        }
    }

    public void storeTitleFile
            (MultipartFile file, String ident_Id) throws IOException {
        if(!titleImageRepository.existsTitleImageByTitleID(ident_Id)){
            TitleImage titleImage= new TitleImage();
            titleImage.setName(file.getName());
            titleImage.setTitleID(ident_Id);
            titleImage.setData(file.getBytes());
            titleImage.setType(file.getContentType());
            titleImageRepository.save(titleImage);
        }
    }

    public FileDB getFile(String id) {
        return fileDBRepository.findById(id).get();
    }

    public TitleImage getTitleImage(String id) {
        return titleImageRepository.findByTitleID(id);
    }

    public void deleteTitleImage(String id) {
        titleImageRepository.deleteByTitleID(id);
    }

    public void deleteLogoImage(String id) {
        logoImageRepository.deleteByLogoID(id);
    }

    public List<TitleImage> getAllTitleImages() {
        return titleImageRepository.findAll();
    }

    public List<LogoImage> getAllLogoImages() {
        return logoImageRepository.findAll();
    }

    public LogoImage getLogoImage(String id) {
        return logoImageRepository.findByLogoID(id);
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
