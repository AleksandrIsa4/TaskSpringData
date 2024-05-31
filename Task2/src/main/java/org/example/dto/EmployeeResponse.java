package org.example.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.model.Department;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {

    Long id;

    String firstName ;

    String lastName ;

    String position ;

    String salary ;

    Department department;
}
