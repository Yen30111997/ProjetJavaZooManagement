package org.edu.zoo.management.controller;

import org.edu.zoo.management.model.entity.EnclosureEntity;
import org.edu.zoo.management.model.request.EnclosureRequest;
import org.edu.zoo.management.service.EnclosureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enclosures")
public class EnclosureController {

    @Autowired
    private EnclosureService enclosureService;

    @GetMapping
    public List<EnclosureEntity> getAllEnclosures() {
        return enclosureService.getAllEnclosures();
    }

    @GetMapping("/{id}")
    public Optional<EnclosureEntity> getEnclosureById(@PathVariable Long id) {
        return enclosureService.getEnclosureById(id);
    }

    @PostMapping
    public EnclosureEntity createEnclosure(@RequestBody EnclosureRequest enclosure) {
        return enclosureService.createEnclosure(enclosure);
    }

    @PutMapping("/{id}")
    public EnclosureEntity updateEnclosure(@PathVariable Long id, @RequestBody EnclosureEntity enclosureEntityDetails) {
        return enclosureService.updateEnclosure(id, enclosureEntityDetails);
    }

    @DeleteMapping("/{id}")
    public void deleteEnclosure(@PathVariable Long id) {
        enclosureService.deleteEnclosure(id);
    }
}
