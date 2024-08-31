package org.edu.zoo.management.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;


@Entity
@Table(name = "enclosure")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnclosureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enclosure_id")
    private Long id;

    private String name;
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "enclosure")
    @JsonIgnore
    private Set<AnimalEntity> animals;

}
