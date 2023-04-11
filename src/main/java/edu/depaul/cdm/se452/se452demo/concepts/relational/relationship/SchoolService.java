package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.val;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/school")
@Tag(name = "School", description = "Everything about your School")
@Log4j2
public class SchoolService {
    @Autowired
    private SchoolRepository repo;

    @GetMapping
    public List<School> list() {
        log.traceEntry("Enter list");
        var retval = repo.findAll();
        log.traceExit("Exit list", retval);        
        return repo.findAll();
    }

    @Autowired
    private AddressRepository addrRepository;

    @PostMapping
    public @ResponseBody School save(@RequestBody School request) {
        log.traceEntry("enter save(school={})", request);

        if (request.getId() > 0) {
            val updatedAddressId = request.getAddress().getId();
            request = repo.findById(request.getId()).orElse(new School());
            log.trace("requested object populated {}", request);

            // if address is updated, then need to lookup the address
            if (updatedAddressId > 0) {
                val newAddress = addrRepository.findById(updatedAddressId).orElse(new Address());
                request.setAddress(newAddress);
            }
        }

        repo.save(request);
        log.traceExit("exit save(school={})", request);        
        return request;
    }

}
