package edu.depaul.cdm.se452.se452demo.concepts.relational.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("test")
@Sql({"/data-h2-test.sql"})
public class StudentTest {
    @Autowired
    private StudentRepository repo;

    @Test
    public void testFindByAgeLessThanEqual() {
        long count = repo.count();
        List<Student> lessThanDrinking = repo.findByAgeLessThanEqual(21);

        assertEquals(4, count);
        assertEquals(3, lessThanDrinking.size());

    }

    @Test
    public void testFindByName() {
        List<Student> findJames = repo.findByName("James");
        List<Student> findNoOne = repo.findByName("NoOne");

        assertEquals(1, findJames.size());
        assertEquals(0, findNoOne.size());

    }

    @Test
    public void testFindByLike() {
        List<Student> findJa = repo.findByNameLike("Ja%");

        assertEquals(2, findJa.size());
    }


}
