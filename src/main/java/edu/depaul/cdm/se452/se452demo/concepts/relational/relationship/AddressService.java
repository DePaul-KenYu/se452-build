package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/address")
@Tag(name = "Addr", description = "Everything about address")
@Log4j2
public class AddressService {
    @Autowired
    private AddressRepository repo;

    @GetMapping
    public List<Address> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);
        return retval;
    }

    @PostMapping
    public Address save(@RequestBody Address request) {
        log.traceEntry("enter save {}", request);
        repo.save(request);
        log.traceExit("exit save {}", request);
        return request;
    }    
    
}
