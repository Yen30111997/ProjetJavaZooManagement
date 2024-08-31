package org.edu.zoo.management.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "animal")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "animal_id")
    private Long idAnimal;

    private String name;
    private String species;
    private Integer age;
    private float height;
    private float length;
    private float weight;

    @ManyToOne
    @JoinColumn(name = "enclosure_id")
    private EnclosureEntity enclosure;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalEntity that = (AnimalEntity) o;
        return Float.compare(height, that.height) == 0 && Float.compare(length, that.length) == 0 && Float.compare(weight, that.weight) == 0 && Objects.equals(idAnimal, that.idAnimal) && Objects.equals(name, that.name) && Objects.equals(species, that.species) && Objects.equals(age, that.age) && Objects.equals(enclosure, that.enclosure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, name, species, age, height, length, weight, enclosure);
    }

    @Override
    public String toString() {
        return "AnimalEntity{" +
                "idAnimal=" + idAnimal +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", length=" + length +
                ", weight=" + weight +
                ", enclosure=" + enclosure +
                '}';
    }
}
