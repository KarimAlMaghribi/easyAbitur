package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.PhysikLK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysikLK_Repository extends JpaRepository<PhysikLK, String> {
  //PhysikLK findByPhysikLk_id(String id);
}
