package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> { 
    public School findByName(String name);
}