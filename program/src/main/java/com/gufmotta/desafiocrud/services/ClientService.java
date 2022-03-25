package com.gufmotta.desafiocrud.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gufmotta.desafiocrud.dto.ClientDTO;
import com.gufmotta.desafiocrud.entities.Client;
import com.gufmotta.desafiocrud.repositories.ClientRepository;
import com.gufmotta.desafiocrud.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ModelMapper mapper;

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> entity = repository.findById(id);
		Client client = entity.orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));
		return mapper.map(client, ClientDTO.class);
	}

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(Pageable page) {
		Page<Client> clientPage = repository.findAll(page);
		return clientPage.map(x -> mapper.map(x, ClientDTO.class));
	}

	@Transactional
	public ClientDTO insert(ClientDTO newClient) {
		Client entity = new Client();
		mapper.map(newClient, entity);
		entity = repository.save(entity);
		return mapper.map(entity, ClientDTO.class);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO dto) {
		try {
			Client entity = repository.getOne(id);
			mapper.map(dto, entity);
			entity = repository.save(entity);
			return mapper.map(entity, ClientDTO.class);
		}
		catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Cliente não encontrado");
		}
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Cliente não encontrado");
		}
	}
}
