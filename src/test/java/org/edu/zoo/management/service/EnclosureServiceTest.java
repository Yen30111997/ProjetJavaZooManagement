package org.edu.zoo.management.service;

import org.edu.zoo.management.model.entity.EnclosureEntity;
import org.edu.zoo.management.model.request.EnclosureRequest;
import org.edu.zoo.management.repository.EnclosureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EnclosureServiceTest {
    @Mock
    private EnclosureRepository enclosureRepository;


    private EnclosureService enclosureService;

    @BeforeEach
    void setUp() {
        this.enclosureService = new EnclosureService(enclosureRepository);
    }
    @Test
    void testCreateEnclosure() {
        // Arrange
        EnclosureRequest request = new EnclosureRequest("Lion Enclosure", "North Zone");
        EnclosureEntity savedEnclosure = new EnclosureEntity();
        savedEnclosure.setId(1L);
        savedEnclosure.setName("Lion Enclosure");
        savedEnclosure.setLocation("North Zone");

        when(enclosureRepository.save(any(EnclosureEntity.class))).thenReturn(savedEnclosure);

        // Act
        EnclosureEntity result = enclosureService.createEnclosure(request);

        // Assert
        assertEquals(savedEnclosure.getId(), result.getId());
        assertEquals(savedEnclosure.getName(), result.getName());
        assertEquals(savedEnclosure.getLocation(), result.getLocation());
    }

    @Test
    void testUpdateEnclosure() {
        // Arrange
        Long id = 1L;
        EnclosureEntity existingEnclosure = new EnclosureEntity();
        existingEnclosure.setId(id);
        existingEnclosure.setName("TP1");
        existingEnclosure.setLocation("Paris");

        EnclosureEntity updatedEnclosureDetails = new EnclosureEntity();
        updatedEnclosureDetails.setName("TP2");
        updatedEnclosureDetails.setLocation("Lyon");

        when(enclosureRepository.findById(id)).thenReturn(Optional.of(existingEnclosure));
        when(enclosureRepository.save(any(EnclosureEntity.class))).thenReturn(updatedEnclosureDetails);

        // Act
        EnclosureEntity result = enclosureService.updateEnclosure(id, updatedEnclosureDetails);

        // Assert
        assertEquals(updatedEnclosureDetails.getName(), result.getName());
        assertEquals(updatedEnclosureDetails.getLocation(), result.getLocation());
    }

    @Test
    void testDeleteEnclosure() {
        // Arrange
        Long id = 1L;
        EnclosureEntity existingEnclosure = new EnclosureEntity();
        existingEnclosure.setId(id);

        when(enclosureRepository.findById(id)).thenReturn(Optional.of(existingEnclosure));
        doNothing().when(enclosureRepository).delete(existingEnclosure);

        // Act
        enclosureService.deleteEnclosure(id);

        // Assert
        verify(enclosureRepository, times(1)).delete(existingEnclosure);
    }

    @Test
    void testDeleteEnclosure_NotFound() {
        // Arrange
        Long id = 1L;
        when(enclosureRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> enclosureService.deleteEnclosure(id));
    }
}
