package de.backend.spring.easyAbitur.service;

import de.backend.spring.easyAbitur.model.*;
import de.backend.spring.easyAbitur.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private ExamRepository examRepository;

    @Autowired
    private MathGK_Exam_Repository mathGK_exam_repository;

    @Autowired
    private MathLK_Exam_Repository mathLK_exam_repository;

    @Autowired
    private PhysikGK_Exam_Repository physikGK_exam_repository;

    @Autowired
    private PhysikLK_Exam_Repository physikLK_exam_repository;

    @Autowired
    private MathLK_Repository mathLK_repository;

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

  public void storeMathLK_Exam
    (MultipartFile file, String ident_Id, int year) throws IOException {
    if(!examRepository.existsExamByIdentId(ident_Id)){
      MathLK_Exam mathLK_Exam = new MathLK_Exam();

      mathLK_Exam.setName(file.getName());
      mathLK_Exam.setId(ident_Id);
      mathLK_Exam.setData(file.getBytes());
      mathLK_Exam.setType(file.getContentType());
      mathLK_Exam.setYear(year);

     mathLK_exam_repository.save(mathLK_Exam);
    }
  }

  public void storeMathGK_Exam
    (MultipartFile file, String ident_Id, int year) throws IOException {
    if(!examRepository.existsExamByIdentId(ident_Id)){
      MathGK_Exam mathGK_Exam = new MathGK_Exam();

      mathGK_Exam.setName(file.getName());
      mathGK_Exam.setId(ident_Id);
      mathGK_Exam.setData(file.getBytes());
      mathGK_Exam.setType(file.getContentType());
      mathGK_Exam.setYear(year);

      mathGK_exam_repository.save(mathGK_Exam);
    }
  }

  public void storePhysikGK_Exam
    (MultipartFile file, String ident_Id, int year) throws IOException {
    if(!examRepository.existsExamByIdentId(ident_Id)){
      PhysikGK_Exam physikGK_Exam = new PhysikGK_Exam();
      physikGK_Exam.setName(file.getName());
      physikGK_Exam.setId(ident_Id);
      physikGK_Exam.setData(file.getBytes());
      physikGK_Exam.setType(file.getContentType());

      physikGK_exam_repository.save(physikGK_Exam);
    }
  }

  public void storePhysikLK_Exam
    (MultipartFile file, String ident_Id, int year) throws IOException {
    if(!examRepository.existsExamByIdentId(ident_Id)){
      PhysikLK_Exam physikLK_Exam = new PhysikLK_Exam();
      physikLK_Exam.setName(file.getName());
      physikLK_Exam.setId(ident_Id);
      physikLK_Exam.setData(file.getBytes());
      physikLK_Exam.setType(file.getContentType());
      physikLK_Exam.setYear(year);
      physikLK_exam_repository.save(physikLK_Exam);
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
