package edu.depaul.cdm.se452.se452demo.concepts.services.validation;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();
}
