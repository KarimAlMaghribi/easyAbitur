package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.MathLK_Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathLK_Exam_Repository extends JpaRepository<MathLK_Exam, String> {
}
