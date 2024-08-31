package org.edu.zoo.management.controller;

import org.edu.zoo.management.model.entity.AnimalEntity;
import org.edu.zoo.management.model.request.AnimalRequest;
import org.edu.zoo.management.service.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class AnimalControllerTest {
    @Mock
    private AnimalService animalService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        AnimalController animalController = new AnimalController(animalService);
        mockMvc = MockMvcBuilders.standaloneSetup(animalController).build();
    }

    @Test
    void testGetAllAnimals() throws Exception {
        // Arrange
        AnimalEntity animal = new AnimalEntity();
        animal.setIdAnimal(1L);
        animal.setName("Lion");

        when(animalService.getAllAnimals()).thenReturn(List.of(animal));

        // Act & Assert
        mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Lion"));
    }

    @Test
    void testGetAnimalById() throws Exception {
        // Arrange
        Long id = 1L;
        AnimalEntity animal = new AnimalEntity();
        animal.setIdAnimal(id);
        animal.setName("Lion");

        when(animalService.getAnimalById(id)).thenReturn(Optional.of(animal));

        // Act & Assert
        mockMvc.perform(get("/animals/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lion"));
    }

    @Test
    void testCreateAnimal() throws Exception {
        // Arrange
        AnimalRequest request = new AnimalRequest("Lion", "Panthera leo", 5, 12, 25, 190);
        AnimalEntity animal = new AnimalEntity();
        animal.setName("Lion");
        animal.setSpecies("Panthera leo");

        when(animalService.saveAnimal(any(AnimalRequest.class))).thenReturn(animal);

        // Act & Assert
        mockMvc.perform(post("/animals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Lion\",\"species\":\"Panthera leo\",\"age\":5,\"height\":1.2,\"length\":2.5,\"weight\":190.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lion"));
    }

    @Test
    void testUpdateAnimal() throws Exception {
        // Arrange
        Long id = 1L;
        AnimalRequest request = new AnimalRequest("Lion", "Panthera leo", 6, 13, 26, 200);
        AnimalEntity animal = new AnimalEntity();
        animal.setIdAnimal(id);
        animal.setName("Lion");
        animal.setSpecies("Panthera leo");

        when(animalService.updateAnimal(any(AnimalRequest.class), any(Long.class))).thenReturn(Optional.of(animal));

        // Act & Assert
        mockMvc.perform(put("/animals/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Lion\",\"species\":\"Panthera leo\",\"age\":6,\"height\":1.3,\"length\":2.6,\"weight\":200.0}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lion"));
    }

    @Test
    void testDeleteAnimal() throws Exception {
        // Arrange
        Long id = 1L;
        when(animalService.getAnimalById(id)).thenReturn(Optional.of(new AnimalEntity()));

        // Act & Assert
        mockMvc.perform(delete("/animals/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testLinkAnimalToEnclosure() throws Exception {
        // Arrange
        Long animalId = 1L;
        Long enclosureId = 1L;
        AnimalEntity animal = new AnimalEntity();
        animal.setIdAnimal(animalId);
        animal.setName("Lion");

        when(animalService.linkAnimalToEnclosure(animalId, enclosureId)).thenReturn(animal);

        // Act & Assert
        mockMvc.perform(put("/animals/{animalId}/enclosures/{enclosureId}", animalId, enclosureId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lion"));
    }
}
