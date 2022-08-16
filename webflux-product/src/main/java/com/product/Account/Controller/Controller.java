package com.product.Account.Controller;

import com.product.Account.Model.Account;
import com.product.Account.Service.IService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
public class Controller {

    private final IService iService;

    @GetMapping("/")
    public Flux<Account> getAllContacts() {
      return this.iService.getAll();
    }

    @GetMapping
    public Mono<ResponseEntity<Account>> getClient(@RequestParam String id) {
        return this.iService.get(id);
    }
    
    @PostMapping
    public Mono<ResponseEntity<Account>> insertClient(@RequestBody Account account){
        return this.iService.insert(account);
    }
    
    @PutMapping
    public Mono<ResponseEntity<Account>> updateClient(@RequestParam String id, @RequestParam float cash) {
        return this.iService.updateCashSum(cash, id);
    }

    @DeleteMapping
	public Mono<ResponseEntity<Void>> deleteContact(@RequestParam String dni) {
        return this.iService.deleteContact(dni);
    }

}
