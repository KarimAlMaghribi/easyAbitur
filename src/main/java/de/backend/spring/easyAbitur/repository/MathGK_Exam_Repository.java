package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.MathGK;
import de.backend.spring.easyAbitur.model.MathGK_Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathGK_Exam_Repository extends JpaRepository<MathGK_Exam, String> {
}
