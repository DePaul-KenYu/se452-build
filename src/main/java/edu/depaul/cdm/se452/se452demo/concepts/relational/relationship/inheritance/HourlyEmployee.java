package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship.inheritance;

import jakarta.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class HourlyEmployee extends Employee {
    private double rate;
}
