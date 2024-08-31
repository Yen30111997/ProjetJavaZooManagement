package org.edu.zoo.management.service;

import org.edu.zoo.management.model.entity.EmployeeEntity;
import org.edu.zoo.management.model.entity.EnclosureEntity;
import org.edu.zoo.management.model.request.EmployeeRequest;
import org.edu.zoo.management.model.request.EnclosureRequest;
import org.edu.zoo.management.model.response.EnclosureDto;
import org.edu.zoo.management.repository.EmployeeRepository;
import org.edu.zoo.management.repository.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EnclosureRepository enclosureRepository;

    public EmployeeService(EmployeeRepository employeeRepository, EnclosureRepository enclosureRepository) {
        this.employeeRepository = employeeRepository;
        this.enclosureRepository = enclosureRepository;
    }

    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public EmployeeEntity createEmployee(EmployeeRequest employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employee.name());
        employeeEntity.setRole(employee.role());
        return employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeRequest employeeDetails) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeEntity.setName(employeeDetails.name());
        employeeEntity.setRole(employeeDetails.role());
        return employeeRepository.save(employeeEntity);
    }

    public void deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employeeRepository.delete(employeeEntity);
    }
    public EnclosureDto addEnclosureToEmployee(Long employeeId, EnclosureRequest enclosureRequest) {
        Optional<EmployeeEntity> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            EmployeeEntity employee = employeeOptional.get();
            EnclosureEntity enclosure = new EnclosureEntity();
            enclosure.setName(enclosureRequest.name());
            enclosure.setLocation(enclosureRequest.location());
            enclosure.setEmployee(employee);
            var enclosureEntity = enclosureRepository.save(enclosure);
            return EnclosureDto.enclosureDto(enclosureEntity);

        } else {
            throw new RuntimeException("Employee not found");
        }
    }

}
