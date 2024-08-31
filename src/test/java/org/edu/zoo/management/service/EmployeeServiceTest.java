package org.edu.zoo.management.service;

import org.edu.zoo.management.model.entity.EmployeeEntity;
import org.edu.zoo.management.model.entity.EnclosureEntity;
import org.edu.zoo.management.model.request.EmployeeRequest;
import org.edu.zoo.management.model.request.EnclosureRequest;
import org.edu.zoo.management.model.response.EnclosureDto;
import org.edu.zoo.management.repository.EmployeeRepository;
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
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EnclosureRepository enclosureRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(employeeRepository, enclosureRepository);
    }

    @Test
    void testGetAllEmployees() {
        // Arrange
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setRole("Zookeeper");

        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        // Act
        List<EmployeeEntity> result = employeeService.getAllEmployees();

        // Assert
        assertEquals(1, result.size());
        assertEquals("John Doe", result.get(0).getName());
    }

    @Test
    void testGetEmployeeById() {
        // Arrange
        Long id = 1L;
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id);
        employee.setName("John Doe");
        employee.setRole("Zookeeper");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        // Act
        Optional<EmployeeEntity> result = employeeService.getEmployeeById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());
    }

    @Test
    void testCreateEmployee() {
        // Arrange
        EmployeeRequest request = new EmployeeRequest("John Doe", "Zookeeper");
        EmployeeEntity employee = new EmployeeEntity();
        employee.setName("John Doe");
        employee.setRole("Zookeeper");

        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(employee);

        // Act
        EmployeeEntity result = employeeService.createEmployee(request);

        // Assert
        assertEquals("John Doe", result.getName());
        assertEquals("Zookeeper", result.getRole());
    }

    @Test
    void testUpdateEmployee() {
        // Arrange
        Long id = 1L;
        EmployeeRequest request = new EmployeeRequest("John Doe", "Senior Zookeeper");
        EmployeeEntity existingEmployee = new EmployeeEntity();
        existingEmployee.setId(id);
        existingEmployee.setName("John Doe");
        existingEmployee.setRole("Zookeeper");

        when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(any(EmployeeEntity.class))).thenReturn(existingEmployee);

        // Act
        EmployeeEntity result = employeeService.updateEmployee(id, request);

        // Assert
        assertEquals("John Doe", result.getName());
        assertEquals("Senior Zookeeper", result.getRole());
    }

    @Test
    void testDeleteEmployee() {
        // Arrange
        Long id = 1L;
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(id);

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).delete(employee);

        // Act
        employeeService.deleteEmployee(id);

        // Assert
        verify(employeeRepository, times(1)).delete(employee);
    }

    @Test
    void testAddEnclosureToEmployee() {
        // Arrange
        Long employeeId = 1L;
        EnclosureRequest request = new EnclosureRequest("Lion Enclosure", "North Zone");
        EmployeeEntity employee = new EmployeeEntity();
        employee.setId(employeeId);
        employee.setName("John Doe");
        employee.setRole("Zookeeper");

        EnclosureEntity enclosure = new EnclosureEntity();
        enclosure.setId(1L);
        enclosure.setName("Lion Enclosure");
        enclosure.setLocation("North Zone");
        enclosure.setEmployee(employee);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(enclosureRepository.save(any(EnclosureEntity.class))).thenReturn(enclosure);

        // Act
        EnclosureDto result = employeeService.addEnclosureToEmployee(employeeId, request);

        // Assert
        assertEquals("Lion Enclosure", result.name());
        assertEquals("North Zone", result.location());
        assertEquals(employeeId, result.employee().id());
    }
}
