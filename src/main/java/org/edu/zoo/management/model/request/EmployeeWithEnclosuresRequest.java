package org.edu.zoo.management.model.request;

import java.util.List;

public record EmployeeWithEnclosuresRequest(
         String name,
         String role,
         List<EnclosureRequest> enclosures
) {
}
