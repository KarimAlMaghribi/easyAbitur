package de.l.stadtwerke.loga3jobofferservice.controller;

import de.l.stadtwerke.loga3jobofferservice.dto.response.ResponseFile;
import de.l.stadtwerke.loga3jobofferservice.dto.response.ResponseFileMessage;
import de.l.stadtwerke.loga3jobofferservice.model.FileDB;
import de.l.stadtwerke.loga3jobofferservice.model.LogoImage;
import de.l.stadtwerke.loga3jobofferservice.model.TitleImage;
import de.l.stadtwerke.loga3jobofferservice.repository.StellenausschreibungRepository;
import de.l.stadtwerke.loga3jobofferservice.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/loga3-joboffer-service")
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private StellenausschreibungRepository stellenausschreibungRepository;


/*
    @PostMapping("/upload")
    public ResponseEntity<ResponseFileMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseFileMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseFileMessage(message));
        }
    }
*/
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getFiles_id())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/titleImage/objall")
    public ResponseEntity<List<TitleImage>> getAllTitleObjImages() {

        List<TitleImage> fileDB = storageService.getAllTitleImages();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(fileDB);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/logoImage/objall")
    public ResponseEntity<List<LogoImage>> getAllLogoImages() {

        List<LogoImage> fileDB = storageService.getAllLogoImages();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(fileDB);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/titleImage/all")
    public ResponseEntity<ArrayList<byte[]>> getAllTitleImages() {

        List<TitleImage> fileDB = storageService.getAllTitleImages();

        ArrayList<byte[]> pictureData = new ArrayList<>();

        for(TitleImage image: fileDB) {
            pictureData.add(image.getData());
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(pictureData);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/logoImage/{id}")
    public ResponseEntity<ResponseFileMessage> uploadLogoFile(@RequestParam("imageFile") MultipartFile file, @PathVariable String id) {
        String message = "";
        try {
            storageService.storeLogoFile(file, id);
            message = "Uploaded the image successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseFileMessage(message));
        } catch (Exception e) {
            message = "Could not upload the image: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseFileMessage(message));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/titleImage/{id}")
    public ResponseEntity<ResponseFileMessage> uploadTitleFile(@RequestParam("imageFile") MultipartFile file, @PathVariable String id) {
        String message = "";
        try {
            storageService.storeTitleFile(file, id);
            message = "Uploaded the image successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseFileMessage(message));
        } catch (Exception e) {
            message = "Could not upload the image: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseFileMessage(message));
        }
    }

   @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/titleImage/{id}")
    public ResponseEntity<byte[]> getTitleImage(@PathVariable String id) {

        TitleImage fileDB = storageService.getTitleImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/titleImage/{id}")
    public void deleteTitleImage(@PathVariable String id) {

        storageService.deleteTitleImage(id);

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/logoImage/{id}")
    public void deleteLogoImage(@PathVariable String id) {
        storageService.deleteLogoImage(id);
    }
    /*
       @CrossOrigin(origins = "http://localhost:4200")
       @GetMapping("/titleImage/{id}")
       public ResponseEntity<ArrayList<TitleImage>> getTitleImage(@PathVariable String id) {

           ArrayList<TitleImage> fileDB = new ArrayList<>();
           fileDB.add(storageService.getTitleImage(id));

           return ResponseEntity.ok()
                   .header(HttpHeaders.CONTENT_DISPOSITION)
                   .body(fileDB);
       }
    */
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/logoImage/{id}")
    public ResponseEntity<byte[]> getLogoImage(@PathVariable String id) {

        LogoImage fileDB = storageService.getLogoImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
