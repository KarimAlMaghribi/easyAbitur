package de.backend.spring.easyAbitur.service;

import de.backend.spring.easyAbitur.dto.model.MathGK_Exam_DTO;
import de.backend.spring.easyAbitur.dto.model.MathLK_Exam_DTO;
import de.backend.spring.easyAbitur.dto.model.PhysikGK_Exam_DTO;
import de.backend.spring.easyAbitur.dto.model.PhysikLK_Exam_DTO;
import de.backend.spring.easyAbitur.model.*;
import de.backend.spring.easyAbitur.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolSubjectService {

  @Autowired
  MathLK_Exam_Repository mathLK_exam_repository;

  @Autowired
  MathGK_Exam_Repository mathGK_exam_repository;

  @Autowired
  PhysikGK_Exam_Repository physikGK_exam_repository;

  @Autowired
  PhysikLK_Exam_Repository physikLK_exam_repository;

  private List<MathLK_Exam> getAllMathLK_Exams() {
    return mathLK_exam_repository.findAll();
  }

  private List<MathGK_Exam> getAllMathGK_Exams() {
    return mathGK_exam_repository.findAll();
  }

  private List<PhysikGK_Exam> getAllPhysikGK_Exams() {
    return physikGK_exam_repository.findAll();
  }

  private List<PhysikLK_Exam> getAllPhysikLK_Exams() {
    return physikLK_exam_repository.findAll();
  }

  public List<MathGK_Exam_DTO> getAllMathGK_Exam_DTOs() {
    return this.getAllMathGK_Exams().stream().map(mathGK_exam -> {
      MathGK_Exam_DTO mathGK_exam_dto = new MathGK_Exam_DTO();

      mathGK_exam_dto.setFileId(mathGK_exam.getFiles_id());
      mathGK_exam_dto.setYear(mathGK_exam.getYear());

      return mathGK_exam_dto;
    }).collect(Collectors.toList());
  }

  public List<MathLK_Exam_DTO> getAllMathLK_Exam_DTOs() {
    return this.getAllMathLK_Exams().stream().map(mathLK_exam -> {
      MathLK_Exam_DTO mathLK_exam_dto = new MathLK_Exam_DTO();

      mathLK_exam_dto.setFileId(mathLK_exam.getFiles_id());
      mathLK_exam_dto.setYear(mathLK_exam.getYear());

      return mathLK_exam_dto;
    }).collect(Collectors.toList());
  }

  public List<PhysikGK_Exam_DTO> getAllPhysikGK_Exam_DTOs() {
    return this.getAllPhysikGK_Exams().stream().map(physikGK_exam -> {
      PhysikGK_Exam_DTO physikGK_exam_dto = new PhysikGK_Exam_DTO();

      physikGK_exam_dto.setYear(physikGK_exam.getYear());
      physikGK_exam_dto.setFileId(physikGK_exam.getFiles_id());

      return physikGK_exam_dto;
    }).collect(Collectors.toList());
  }

  public List<PhysikLK_Exam_DTO> getAllPhysikLK_Exam_DTOs() {
    return this.getAllPhysikLK_Exams().stream().map(physikLK_exam -> {
      PhysikLK_Exam_DTO physikLK_exam_dto = new PhysikLK_Exam_DTO();

      physikLK_exam_dto.setYear(physikLK_exam.getYear());
      physikLK_exam_dto.setFileId(physikLK_exam.getFiles_id());

      return physikLK_exam_dto;
    }).collect(Collectors.toList());
  }
}



