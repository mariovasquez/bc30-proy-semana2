package com.product.Credit.Service;

import com.product.Credit.Model.Credit;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditService {

	Flux<Credit> getAll();

	Mono<ResponseEntity<Credit>> get(String dni);

	Mono<ResponseEntity<Credit>> insert(Credit credit);

	Mono<ResponseEntity<Credit>> updateCashSum(float cash, String id);

	Mono<ResponseEntity<Void>>  deleteContact(String dni);

}