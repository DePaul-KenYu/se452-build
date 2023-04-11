package edu.depaul.cdm.se452.se452demo.concepts.relational.relationship;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByLocation(String location);
}