package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship.inheritance;

import jakarta.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class SalaryEmployee extends Employee{
    private long salary;    
}
