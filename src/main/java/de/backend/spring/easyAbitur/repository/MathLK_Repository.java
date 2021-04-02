package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.MathLK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathLK_Repository extends JpaRepository<MathLK, String> {
  //MathLK findByMathLK_Id(String id);
}
