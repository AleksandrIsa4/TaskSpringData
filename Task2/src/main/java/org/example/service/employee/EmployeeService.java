package org.example.service.employee;

import org.example.dto.EmployeeProjection;
import org.example.model.Employee;

public interface EmployeeService {

    Employee post(Employee employee);

    Employee put(Employee employee);

    Employee get(Long id);

    EmployeeProjection getInfo(Long id);

    void delete(Long id);
}
