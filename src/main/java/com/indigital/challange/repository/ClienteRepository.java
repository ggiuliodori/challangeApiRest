package com.indigital.challange.repository;

import com.indigital.challange.repository.models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    Iterable<Cliente> findAll();
}
