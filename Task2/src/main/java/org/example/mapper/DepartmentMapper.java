package org.example.mapper;

import org.example.dto.DepartmentRequest;
import org.example.dto.DepartmentResponse;
import org.example.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    Department toDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse toDepartmentResponse(Department department);
}
