package edu.depaul.cdm.se452.se452demo.concepts.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.se452demo.concepts.lombok.Student;

@RestController
@RequestMapping("/api/nondb/students")
public class StandaloneStudentService {
    private static Map<Integer, Student> STUDENTS = new HashMap<Integer, Student>();

    static {
        Student stu1 = new Student();
        stu1.setStudentId(getNextKeyValue());
        stu1.setFirstName("James");
        stu1.setLastName("Bond");
        STUDENTS.put(stu1.getStudentId(), stu1);

        Student stu2 = new Student();
        stu2.setStudentId(getNextKeyValue());
        stu2.setFirstName("Daniel");
        stu2.setLastName("Craig");
        STUDENTS.put(stu2.getStudentId(), stu2);
    }

    @GetMapping
    public List<Student> list() {
        var retval = STUDENTS.values().stream().collect(Collectors.toList());
        return retval;
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable("id") int studentId) {
        Student student = STUDENTS.get(studentId);
        return student;
    }

    /*
     * Parameter passed as part of request body
     */
    @PostMapping
    public long add(@RequestBody Student student) {
        student.setStudentId(getNextKeyValue());
        STUDENTS.put(student.getStudentId(), student);
        return student.getStudentId();
    }

    /*
     * Parameter passed as part of request uri
     */
    @PutMapping
    public void update(Student student) {
        STUDENTS.put(student.getStudentId(), student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        STUDENTS.remove(id);
    }


    private static int getNextKeyValue() {
        return ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE - 1);
    }

}
