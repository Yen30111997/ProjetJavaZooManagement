package org.edu.zoo.management.service;

import org.edu.zoo.management.model.entity.EnclosureEntity;
import org.edu.zoo.management.model.request.EnclosureRequest;
import org.edu.zoo.management.repository.AnimalRepository;
import org.edu.zoo.management.repository.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnclosureService {

    private final EnclosureRepository enclosureRepository;

    public EnclosureService(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }

    public List<EnclosureEntity> getAllEnclosures() {
        return enclosureRepository.findAll();
    }

    public Optional<EnclosureEntity> getEnclosureById(Long id) {
        return enclosureRepository.findById(id);
    }

    public EnclosureEntity createEnclosure(EnclosureRequest enclosureRequest) {
        EnclosureEntity enclosure = new EnclosureEntity();
        enclosure.setName(enclosureRequest.name());
        enclosure.setLocation(enclosureRequest.location());
        return enclosureRepository.save(enclosure);
    }

    public EnclosureEntity updateEnclosure(Long id, EnclosureEntity enclosureEntityDetails) {
        EnclosureEntity enclosureEntity = enclosureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure not found"));
        enclosureEntity.setName(enclosureEntityDetails.getName());
        enclosureEntity.setLocation(enclosureEntityDetails.getLocation());
        return enclosureRepository.save(enclosureEntity);
    }

    public void deleteEnclosure(Long id) {
        EnclosureEntity enclosureEntity = enclosureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enclosure not found"));
        enclosureRepository.delete(enclosureEntity);
    }
}
