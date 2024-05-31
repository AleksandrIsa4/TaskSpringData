package org.example.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "first_name")
    String firstName ;

    @Column(name = "last_name")
    String lastName ;

    @Column(name = "position")
    String position ;

    @Column(name = "salary")
    String salary ;

    @JoinColumn(name = "departments_id")
    @OneToOne
    Department department;
}
