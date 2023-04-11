package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/university")
@Log4j2
@Transactional
public class UniversityService {
    @Autowired
    private UniversityRepository repo;

    @GetMapping
    public List<University> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return retval;
    }

    @GetMapping("/{name}")
    public University findByName(@PathVariable String name) {
        log.traceEntry("findByName {}", name);
        var retval = repo.findByName(name);

        log.traceExit("findByName {}", name);
        return retval;
    }

    @GetMapping("/{university_id}/instructors")
    public List<Instructor> findUniversityInstructors(@PathVariable Long id) {
        log.traceEntry("findUniversityInstructors {}", id);
        var university = repo.findById(id).orElse(new University());
        var retval = university.getInstructors();

        log.traceExit("findUniversityInstructors {}", id);
        return retval;
    }

    @PostMapping
    public University save(@RequestBody University request) {
        log.traceEntry("enter save {}", request);
        repo.save(request);
        log.traceExit("exit save {}", request);
        return request;
    }    
}
