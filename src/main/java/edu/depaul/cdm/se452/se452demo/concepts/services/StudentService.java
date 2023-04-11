package edu.depaul.cdm.se452.se452demo.concepts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.se452demo.concepts.relational.basic.Student;
import edu.depaul.cdm.se452.se452demo.concepts.relational.basic.StudentRepository;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/db/students")
@Log4j2
public class StudentService {
    @Autowired
    private StudentRepository repo;

    @GetMapping
    public List<Student> list() {
        log.traceEntry("list");
        var retval = repo.findAll();
        log.traceExit("list");
        return retval;
    }

    @GetMapping("/{id}")
    public Student get(@PathVariable("id") Long studentId) {
        log.traceEntry("get", studentId);
        Student student = repo.findById(studentId).orElse(new Student());
        log.traceExit("get", studentId);
        return student;
    }

    @PostMapping
    public long add(Student student) {
        log.traceEntry("add", student);        
        repo.save(student);
        log.traceExit("add", student);
        return student.getId();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.traceEntry("delete", id);        
        repo.deleteById(id);
        log.traceExit("delete", id);        
    }

    @PutMapping
    public void update(Student student) {
        log.traceEntry("update", student);        

        // find the student from database
        var repoStudent = get(student.getId());

        // update the values for student in database to value that was passed
        repoStudent.setAge(student.getAge());
        repoStudent.setEmail(student.getEmail());
        repoStudent.setAdmittedDate(student.getAdmittedDate());
        repoStudent.setName(student.getName());
        repoStudent.setStudentId(student.getStudentId());

        // save the updated value
        repo.save(repoStudent);      
        log.traceExit("update", student);        
    }
}
