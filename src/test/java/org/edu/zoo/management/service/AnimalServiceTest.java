package org.edu.zoo.management.service;

import org.edu.zoo.management.model.entity.AnimalEntity;
import org.edu.zoo.management.model.entity.EnclosureEntity;
import org.edu.zoo.management.model.request.AnimalRequest;
import org.edu.zoo.management.repository.AnimalRepository;
import org.edu.zoo.management.repository.EnclosureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {
    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private EnclosureRepository enclosureRepository;

    private AnimalService animalService;

    @BeforeEach
    void setUp() {
        animalService = new AnimalService(animalRepository, enclosureRepository);
    }

    @Test
    void testGetAllAnimals() {
        // Arrange
        AnimalEntity animal = new AnimalEntity();
        animal.setIdAnimal(1L);
        animal.setName("Lion");

        when(animalRepository.findAll()).thenReturn(List.of(animal));

        // Act
        List<AnimalEntity> result = animalService.getAllAnimals();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Lion", result.get(0).getName());
    }

    @Test
    void testGetAnimalById() {
        // Arrange
        Long id = 1L;
        AnimalEntity animal = new AnimalEntity();
        animal.setIdAnimal(id);
        animal.setName("Lion");

        when(animalRepository.findById(id)).thenReturn(Optional.of(animal));

        // Act
        Optional<AnimalEntity> result = animalService.getAnimalById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Lion", result.get().getName());
    }

    @Test
    void testSaveAnimal() {
        // Arrange
        AnimalRequest request = new AnimalRequest("Lion", "Panthera leo", 5, 13, 11, 190);
        AnimalEntity animal = new AnimalEntity();
        animal.setName("Lion");
        animal.setSpecies("Panthera leo");

        when(animalRepository.save(any(AnimalEntity.class))).thenReturn(animal);

        // Act
        AnimalEntity result = animalService.saveAnimal(request);

        // Assert
        assertEquals("Lion", result.getName());
        assertEquals("Panthera leo", result.getSpecies());
    }

    @Test
    void testUpdateAnimal() {
        // Arrange
        Long id = 1L;
        AnimalRequest request = new AnimalRequest("Lion", "Panthera leo", 6, 11, 26, 200);
        AnimalEntity existingAnimal = new AnimalEntity();
        existingAnimal.setIdAnimal(id);
        existingAnimal.setName("Lion");
        existingAnimal.setSpecies("Panthera leo");

        when(animalRepository.findById(id)).thenReturn(Optional.of(existingAnimal));
        when(animalRepository.save(any(AnimalEntity.class))).thenReturn(existingAnimal);

        // Act
        Optional<AnimalEntity> result = animalService.updateAnimal(request, id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Lion", result.get().getName());
        assertEquals("Panthera leo", result.get().getSpecies());
    }

    @Test
    void testDeleteAnimal() {
        // Arrange
        Long id = 1L;
        doNothing().when(animalRepository).deleteById(id);

        // Act
        animalService.deleteAnimal(id);

        // Assert
        verify(animalRepository, times(1)).deleteById(id);
    }

    @Test
    void testLinkAnimalToEnclosure() {
        // Arrange
        Long animalId = 1L;
        Long enclosureId = 1L;
        AnimalEntity animal = new AnimalEntity();
        animal.setIdAnimal(animalId);
        animal.setName("Lion");

        EnclosureEntity enclosure = new EnclosureEntity();
        enclosure.setId(enclosureId);
        enclosure.setName("Lion Enclosure");

        when(animalRepository.findById(animalId)).thenReturn(Optional.of(animal));
        when(enclosureRepository.findById(enclosureId)).thenReturn(Optional.of(enclosure));
        when(animalRepository.save(any(AnimalEntity.class))).thenReturn(animal);

        // Act
        AnimalEntity result = animalService.linkAnimalToEnclosure(animalId, enclosureId);

        // Assert
        assertEquals("Lion", result.getName());
        assertEquals(enclosure, result.getEnclosure());
    }
}
