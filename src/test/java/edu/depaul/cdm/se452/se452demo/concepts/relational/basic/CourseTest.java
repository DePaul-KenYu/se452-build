package edu.depaul.cdm.se452.se452demo.concepts.relational.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.val;

@DataJpaTest
@ActiveProfiles("test")
public class CourseTest {
    @Autowired
    private CourseRepository repo;

    /**
     * Test create, read, update, and delete
     */
    @Test
    public void testCrud() {
        var orgCourse = new Course();
        orgCourse.setDept("SE");
        orgCourse.setNum("452");
        long b4Count = repo.count();
        var b4Id = orgCourse.getId();
        repo.save(orgCourse);
        var afterCount = repo.count();
        var afterId = orgCourse.getId();

        // there should be 1 more in the database after the save
        assertEquals(b4Count + 1, afterCount);

        // original id was 0 but afterwards the id was generated and so should not be equal
        assertNotEquals(b4Id, afterId);

        // Scenario of updating cross listing
        // Be sure to find the reference from the database before the update
        var updated = repo.findById(afterId).orElse(new Course());
        val crossListed = "352-452";
        updated.setNum(crossListed);
        repo.save(updated);

        var updatedCheck = repo.findById(afterId).orElse(new Course());
        assertEquals(crossListed, updatedCheck.getNum());

        b4Count = repo.count();
        repo.delete(updatedCheck);
        afterCount = repo.count();
        assertEquals(b4Count - 1, afterCount);

    }

}
