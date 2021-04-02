package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, String> {
  boolean existsExamByIdentId(String identId);
}
