package org.edu.zoo.management.repository;
import org.edu.zoo.management.model.entity.EnclosureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnclosureRepository extends JpaRepository<EnclosureEntity, Long> {
}
