package com.client.Service.Impl;

import com.client.Dao.IDao;
import com.client.Exception.NotFoundException;
import com.client.Model.Client;
import com.client.Service.IService;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ServiceImpl implements IService {

	private final IDao iDao;

	@Override
	public Mono<Void>  deleteContact(String dni) {
		this.iDao.findById(dni);
		return null;
	}

	@Override
	public Flux<Client> getAllContacts() {
		return this.iDao.findAll();
	}

	@Override
	public Mono<Client> getClient(String dni) {
		if (dni.length() == 8) {
            return this.iDao.findById(dni);
		}
        throw new NotFoundException("El id debe ser de 8 caracteres");
	}

	@Override
	public Mono<Client> insertClient(Client client) {
		List<String> types = List.of("Personal", "Empresarial");
		if (!types.contains(client.getTypeAccount())) {
			throw new NotFoundException("Tipo de cuenta invalido");
		}
		client.setDateRegister(LocalDateTime.now());
		return this.iDao.insert(client);
	}

	@Override
	public Mono<Client> updateClient(String email, String dni) {
		return null;
	}

}