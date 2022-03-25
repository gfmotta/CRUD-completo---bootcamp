package com.gufmotta.desafiocrud.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gufmotta.desafiocrud.dto.ClientDTO;
import com.gufmotta.desafiocrud.entities.Client;

@Configuration
public class AppConfig {

	@Bean
	public ModelMapper mapper() {
		var mapper = new ModelMapper();
		
		mapper.typeMap(ClientDTO.class, Client.class).addMappings(x -> x.skip(ClientDTO::getId, Client::setId));
		
		return mapper;
	}
}
