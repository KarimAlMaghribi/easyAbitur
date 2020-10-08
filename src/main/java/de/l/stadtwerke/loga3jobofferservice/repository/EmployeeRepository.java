package de.l.stadtwerke.loga3jobofferservice.repository;

import de.l.stadtwerke.loga3jobofferservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}