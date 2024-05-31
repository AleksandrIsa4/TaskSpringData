package org.example.service.department;

import lombok.RequiredArgsConstructor;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Department;
import org.example.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public Department post(Department department) {
        return departmentRepository.save(department);
    }

    public Department put(Department department) {
        get(department.getId());
        return departmentRepository.save(department);
    }

    public Department get(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Нет отдела с данным id=" + id));
    }

    public void delete(Long id) {
        get(id);
        departmentRepository.deleteById(id);
    }
}
