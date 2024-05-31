package org.example.mapper;

import org.example.dto.EmployeeRequest;
import org.example.dto.EmployeeResponse;
import org.example.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    Employee toEmployee(EmployeeRequest employeeRequest);

    EmployeeResponse toEmployeeResponse(Employee employee);
}
