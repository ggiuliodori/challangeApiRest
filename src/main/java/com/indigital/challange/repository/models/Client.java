package com.indigital.challange.repository.models;

import lombok.Data;
import lombok.NonNull;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private LocalDate dateBirth;

    public Client() {
    }

    public Client(Long id, @NonNull String name, @NonNull String lastName, @NonNull LocalDate dateBirth) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
    }
}
