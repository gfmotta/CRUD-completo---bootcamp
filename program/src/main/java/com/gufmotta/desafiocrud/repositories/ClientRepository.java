package com.gufmotta.desafiocrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gufmotta.desafiocrud.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
}
