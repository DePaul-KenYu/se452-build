package edu.depaul.cdm.se452.se452demo.concepts.relational.basic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Example of adding additional finders
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    public List<Student> findByAgeLessThanEqual(long age);
}
