package edu.depaul.cdm.se452.se452demo.concepts.relational.basic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentTest {
    @Autowired
    private StudentRepository repo;

    @BeforeEach
    public void addStudents() {
        var student1 = new Student();
        student1.setName("James");
        student1.setAge(18);
        repo.save(student1);

        var student2 = new Student();
        student2.setName("Daniel");
        student2.setAge(22);
        repo.save(student2);


        var student3 = new Student();
        student3.setName("Mac");
        student3.setAge(10);
        repo.save(student3);

    }


    @Test
    public void addStudentsAndFindById() {
        long count = repo.count();
        List<Student> lessThanDrinking = repo.findByAgeLessThanEqual(21);

        assertNotEquals(0, count);
        assertEquals(2, lessThanDrinking.size());

    }
}
