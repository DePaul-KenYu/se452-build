package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> { 
    public University findByName(String name);
}