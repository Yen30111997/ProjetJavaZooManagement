package org.edu.zoo.management.controller;

import org.edu.zoo.management.model.entity.EmployeeEntity;
import org.edu.zoo.management.model.entity.EnclosureEntity;
import org.edu.zoo.management.model.request.EmployeeRequest;
import org.edu.zoo.management.model.request.EnclosureRequest;
import org.edu.zoo.management.model.response.EnclosureDto;
import org.edu.zoo.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Optional<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public EmployeeEntity createEmployee(@RequestBody EmployeeRequest employee) {
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest employeeDetails) {
        return employeeService.updateEmployee(id, employeeDetails);
    }

    @PostMapping("/{id}/enclosures")
    public EnclosureDto addEnclosureToEmployee(@PathVariable Long id, @RequestBody EnclosureRequest enclosureRequest) {
        return employeeService.addEnclosureToEmployee(id, enclosureRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
