package org.example.service.department;

import org.example.model.Department;

public interface DepartmentService {

    Department post(Department department);

    Department put(Department department);

    Department get(Long id);

    void delete(Long id);
}
