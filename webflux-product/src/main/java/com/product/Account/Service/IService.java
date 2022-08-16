package com.product.Account.Service;

import com.product.Account.Model.Account;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {

	Flux<Account> getAll();

	Mono<ResponseEntity<Account>> get(String dni);

	Mono<ResponseEntity<Account>> insert(Account account);

	Mono<ResponseEntity<Account>> updateCashSum(float cash, String id);

	Mono<ResponseEntity<Void>> deleteContact(String dni);

}