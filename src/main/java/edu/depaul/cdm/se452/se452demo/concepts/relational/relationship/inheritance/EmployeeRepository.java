package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship.inheritance;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository<T extends Employee> extends JpaRepository<T, Long> {
    
}
