package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.DepartmentRequest;
import org.example.dto.DepartmentResponse;
import org.example.mapper.DepartmentMapper;
import org.example.model.Department;
import org.example.service.department.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    private final DepartmentMapper departmentMapper;

    @Operation(summary = "создание")
    @PostMapping
    public DepartmentResponse post(@RequestBody @Valid DepartmentRequest departmentRequest){
        Department department=departmentMapper.toDepartment(departmentRequest);
        department=departmentService.post(department);
        return departmentMapper.toDepartmentResponse(department);
    }

    @Operation(summary = "обновление")
    @PutMapping(value = "/{id}")
    public DepartmentResponse put(@Valid @RequestBody DepartmentRequest departmentRequest,@PathVariable Long id) {
        Department department=departmentMapper.toDepartment(departmentRequest);
        department.setId(id);
        department=departmentService.put(department);
        return departmentMapper.toDepartmentResponse(department);
    }

    @Operation(summary = "чтение")
    @GetMapping(value = "/{id}")
    public DepartmentResponse get(@PathVariable Long id) {
        Department department=departmentService.get(id);
        return departmentMapper.toDepartmentResponse(department);
    }

    @Operation(summary = "удаление")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }
}
