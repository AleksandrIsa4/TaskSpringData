package org.example.service.employee;

import lombok.RequiredArgsConstructor;
import org.example.dto.EmployeeProjection;
import org.example.exceptions.DataNotFoundException;
import org.example.model.Employee;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee post(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee put(Employee employee) {
        get(employee.getId());
        return employeeRepository.save(employee);
    }

    public Employee get(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Нет отдела с данным id=" + id));
    }

    public void delete(Long id) {
        get(id);
        employeeRepository.deleteById(id);
    }

    public EmployeeProjection getInfo(Long id) {
        return employeeRepository.findViewEmployeeById(id).orElseThrow(() -> new DataNotFoundException("Нет отдела с данным id=" + id));
    }
}
