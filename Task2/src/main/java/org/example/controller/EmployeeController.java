package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.*;
import org.example.mapper.EmployeeMapper;
import org.example.model.Department;
import org.example.model.Employee;
import org.example.service.department.DepartmentService;
import org.example.service.employee.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final DepartmentService departmentService;

    private final EmployeeMapper employeeMapper;

    @Operation(summary = "создание")
    @PostMapping
    public EmployeeResponse post(@RequestBody @Valid EmployeeRequest employeeRequest){
        Department department=departmentService.get(employeeRequest.getDepartmentId());
        Employee employee=employeeMapper.toEmployee(employeeRequest);
        employee.setDepartment(department);
        employee=employeeService.post(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }

    @Operation(summary = "обновление")
    @PutMapping(value = "/{id}")
    public EmployeeResponse put(@Valid @RequestBody EmployeeRequest employeeRequest,@PathVariable Long id) {
        Employee employeet=employeeMapper.toEmployee(employeeRequest);
        employeet.setId(id);
        employeet=employeeService.put(employeet);
        return employeeMapper.toEmployeeResponse(employeet);
    }

    @Operation(summary = "чтение")
    @GetMapping(value = "/{id}")
    public EmployeeResponse get(@PathVariable Long id) {
        Employee employeet=employeeService.get(id);
        return employeeMapper.toEmployeeResponse(employeet);
    }

    @Operation(summary = "удаление")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @Operation(summary = "информация")
    @GetMapping(value = "/info/{id}")
    public EmployeeProjection getInfo(@PathVariable Long id) {
        return employeeService.getInfo(id);
    }
}
