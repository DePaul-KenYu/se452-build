package edu.depaul.cdm.se452.se452demo.concepts.relational.basic;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Additional setup with mapping between code and table/columns when they do not match  class/property
 */
@Data
@Entity
@Table(name = "Students")
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @Jacksonized
public class Student {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "stu_id")
	private String studentId;

	@Column(name = "nm")
	private String name;

	private String email;

	private long age;
	private Date admittedDate;
}