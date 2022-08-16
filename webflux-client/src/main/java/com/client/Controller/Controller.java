package com.client.Controller;

import com.client.Model.Client;
import com.client.Service.IService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/client")
@AllArgsConstructor
public class Controller {

    private final IService iService;

    @GetMapping("/")
    public Flux<Client> getAllContacts() {
      return this.iService.getAllContacts();
    }

    @GetMapping
    public Mono<ResponseEntity<Client>> getClient(@RequestParam String dni) {
        return this.iService.getClient(dni);
    }
    
    @PostMapping
    public Mono<ResponseEntity<Client>> insertClient(@RequestBody Client client){
        return this.iService.insertClient(client);
    }
    
    @PutMapping
    public Mono<ResponseEntity<Client>> updateClient(@RequestParam String dni, @RequestParam String email) {
        return this.iService.updateClient(email, dni);
    }

    @DeleteMapping
	public Mono<ResponseEntity<Void>>  deleteContact(@RequestParam String dni) {
        return this.iService.deleteContact(dni);
    }

}
