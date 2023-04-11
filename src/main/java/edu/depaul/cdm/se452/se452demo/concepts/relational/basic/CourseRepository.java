package edu.depaul.cdm.se452.se452demo.concepts.relational.basic;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @see JpaRepository that extends PagingAndSortingRepository that expends CrudRepository
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
