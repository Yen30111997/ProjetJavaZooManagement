package org.edu.zoo.management.model.request;

public record AnimalRequest(
         String name,
         String species,
         Integer age,
         float height,
         float length,
         float weight
) {
}
