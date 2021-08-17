package com.indigital.challange.repository.models;

import lombok.Data;
import lombok.NonNull;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private LocalDate dateBirth;

    public Cliente() {
    }

    public Cliente(Long id, @NonNull String name, @NonNull String lastName, @NonNull LocalDate dateBirth) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
    }
}
