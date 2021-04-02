package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.MathGK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MathGK_Repository  extends JpaRepository<MathGK, String> {
  //MathGK findByMathGK_Id(String id);
  //boolean existsMathGKByIdentId(String identId);
}
