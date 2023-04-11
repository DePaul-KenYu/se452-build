package edu.depaul.cdm.se452.se452demo.concepts.lombok;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StudentTest {
    @Test
    public void testToString() {
        var student = new Student();
        student.setFirstName("James");
        student.setLastName("Bond");
        var expectedWithError = "";
        var expectedNoError = "Student(studentId=null, firstName=James, lastName=Bond)";
        assertEquals(expectedNoError, student.toString());
    }    
}
