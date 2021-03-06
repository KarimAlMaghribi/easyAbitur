package de.backend.spring.easyAbitur.controller;

import de.backend.spring.easyAbitur.dto.response.ResponseFile;
import de.backend.spring.easyAbitur.dto.response.ResponseFileMessage;
import de.backend.spring.easyAbitur.model.FileDB;
import de.backend.spring.easyAbitur.model.LogoImage;
import de.backend.spring.easyAbitur.model.TitleImage;
import de.backend.spring.easyAbitur.repository.StellenausschreibungRepository;
import de.backend.spring.easyAbitur.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
  //@PostMapping("/titleImage/{id}")
  @RequestMapping(value = "/upload/mathGK", method = RequestMethod.POST)
  public void upload_MathGK_Exam_File(@RequestParam("imageFile") MultipartFile file, @RequestParam("title") String title, @RequestParam("year") int year) throws IOException {

    storageService.storeMathGK_Exam(file, title, year);

  }


  @Transactional(readOnly=true)
  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/download-pdf/{id}")
  public ResponseEntity<byte[]> downloadPDFResource(HttpServletResponse response, @PathVariable String id) {

    FileDB fileDB = storageService.getFile(id);
    return ResponseEntity.ok()
      .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
      .body(fileDB.getData());


  }

}
