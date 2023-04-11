package edu.depaul.cdm.se452.se452demo.concepts.relational.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// Need to get Autowired to work
@SpringBootTest
public class CourseTest {
    // @Autowired is to leverage Spring to instatiate the CourseRepository
    @Autowired
    private CourseRepository repo;

    /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud() {
        Course orgCourse = new Course();
        orgCourse.setDept("SE");
        orgCourse.setNum("452");
        long b4Count = repo.count();
        long b4Id = orgCourse.getId();
        repo.save(orgCourse);
        long afterCount = repo.count();
        long afterId = orgCourse.getId();

        // there should be 1 more in the database after the save
        assertEquals(b4Count + 1, afterCount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(b4Id, afterId);

        // Scenario of updating cross listing
        // Be sure to find the reference from the database before the update
        Course updated = repo.findById(afterId).orElse(new Course());
        String crossListed = "352-452";
        updated.setNum(crossListed);
        repo.save(updated);

        Course updatedCheck = repo.findById(afterId).orElse(new Course());
        assertNotEquals(updatedCheck, orgCourse);
        assertEquals(crossListed, updatedCheck.getNum());

        b4Count = repo.count();
        repo.delete(updatedCheck);
        afterCount = repo.count();
        assertEquals(b4Count - 1, afterCount);

    }

}
