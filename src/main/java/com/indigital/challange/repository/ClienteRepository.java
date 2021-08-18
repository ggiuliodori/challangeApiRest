package com.indigital.challange.repository;

import com.indigital.challange.repository.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Client, Long> {
    Iterable<Client> findAll();
}
