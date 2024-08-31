package org.edu.zoo.management.model.response;

import org.edu.zoo.management.model.entity.EnclosureEntity;

public record EnclosureDto(
         Long id,
         String name,
         String location,
         Employee employee) {
    public static EnclosureDto enclosureDto(EnclosureEntity enclosure) {
        return new EnclosureDto(
                enclosure.getId(),
                enclosure.getName(),
                enclosure.getLocation(),
                new Employee(
                        enclosure.getEmployee().getId(),
                        enclosure.getEmployee().getName(),
                        enclosure.getEmployee().getRole()
                )
        );
    }
}
