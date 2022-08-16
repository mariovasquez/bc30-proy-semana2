package com.client.Service;

import org.springframework.http.ResponseEntity;

import com.client.Model.Client;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {

	Flux<Client> getAllContacts();

	Mono<Client> getClient(String dni);

	Mono<Client> insertClient(Client client);

	Mono<Client> updateClient(String email, String dni);

	Mono<Void> deleteContact(String dni);

}