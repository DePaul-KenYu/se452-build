package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> { 
    public Instructor findByName(String name);
}
