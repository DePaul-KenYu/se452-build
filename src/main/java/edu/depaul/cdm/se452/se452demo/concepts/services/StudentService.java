package edu.depaul.cdm.se452.se452demo.concepts.services;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.depaul.cdm.se452.se452demo.concepts.relational.basic.Student;
import edu.depaul.cdm.se452.se452demo.concepts.relational.basic.StudentRepository;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/students")
@Log4j2
public class StudentService {
    @Autowired
    private StudentRepository repo;

    @GetMapping
    public List<Student> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return retval;
    }

    @PostMapping
    public Student save(@RequestBody Student student) {
        log.traceEntry("enter save", student);
        repo.save(student);
        log.traceExit("exit save", student);
        return student;
    }

    @PostMapping("/valid")
    public ResponseEntity<Student> saveValidated(@Valid @RequestBody Student student) {
        log.traceEntry("enter save", student);
        repo.save(student);
        log.traceExit("exit save", student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id ) {
        log.traceEntry("Enter delete", id);
        repo.deleteById(id);
        log.traceExit("Exit delete");
    }           

/* 
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }    
    */

}
