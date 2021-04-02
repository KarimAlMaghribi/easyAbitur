package de.backend.spring.easyAbitur.repository;

import de.backend.spring.easyAbitur.model.PhysikGK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysikGK_Repository extends JpaRepository<PhysikGK, String> {
  //PhysikGK findbyPhysikGK_Id(String id);
}
