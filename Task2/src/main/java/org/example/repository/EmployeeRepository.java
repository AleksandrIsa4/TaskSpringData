package org.example.repository;

import org.example.dto.EmployeeProjection;
import org.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Optional<EmployeeProjection> findViewEmployeeById(Long id);
}
