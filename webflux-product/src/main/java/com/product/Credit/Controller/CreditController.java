package com.product.Credit.Controller;

import com.product.Credit.Model.Credit;
import com.product.Credit.Service.ICreditService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/credit")
@AllArgsConstructor
public class CreditController {

    private final ICreditService iCreditService;

    @GetMapping("/")
    public Flux<Credit> getAll() {
      return this.iCreditService.getAll();
    }

    @GetMapping
    public Mono<ResponseEntity<Credit>> get(@RequestParam String id) {
        return this.iCreditService.get(id);
    }
    
    @PostMapping
    public Mono<ResponseEntity<Credit>> insert(@RequestBody Credit credit){
        return this.iCreditService.insert(credit);
    }
    
    @PutMapping
    public Mono<ResponseEntity<Credit>> update(@RequestParam String id, @RequestParam float cash) {
        return this.iCreditService.updateCashSum(cash, id);
    }

    @DeleteMapping
	public Mono<ResponseEntity<Void>> deleteContact(@RequestParam String dni) {
        return this.iCreditService.deleteContact(dni);
    }

}
