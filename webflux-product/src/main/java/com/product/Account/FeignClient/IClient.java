package com.product.Account.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@FeignClient(name = "webflux-client", url = "localhost:8082/api/v1/client")
public interface IClient {

    @GetMapping("/")
    public Flux<Client> getAllContacts();

    @GetMapping
    public Mono<ResponseEntity<Client>> getClient(@RequestParam String dni);

    @PostMapping
    public Mono<ResponseEntity<Client>> insertClient(@RequestBody Client client);

    @PutMapping
    public Mono<ResponseEntity<Client>> updateClient(@RequestParam String dni, @RequestParam String email);

    @DeleteMapping
    public Mono<ResponseEntity<Void>>  deleteContact(@RequestParam String dni);

}
