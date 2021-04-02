package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.PhysikGK_Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysikGK_Exam_Repository extends JpaRepository<PhysikGK_Exam, String> {
}
