package com.product.Credit.Service.Impl;

import com.product.Account.Exception.NotFoundException;
import com.product.Account.FeignClient.Client;
import com.product.Account.FeignClient.IClient;
import com.product.Credit.Dao.ICreditDao;
import com.product.Credit.Model.Credit;
import com.product.Credit.Service.ICreditService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
@AllArgsConstructor
public class CreditServiceImpl implements ICreditService {

	private final ICreditDao iCreditDao;
	private final IClient iClient;
	@Autowired
	private WebClient.Builder webClientBuilder;

	@Override
	public Mono<ResponseEntity<Void>>  deleteContact(String dni) {
		log.info("delete {}", dni);
		return this.iCreditDao.findById(dni)
				.flatMap(obj ->
						iCreditDao.delete(obj)
								.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
				)
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Override
	public Flux<Credit> getAll() {
		return this.iCreditDao.findAll();
	}

	@Override
	public Mono<ResponseEntity<Credit>> get(String dni) {
		if (dni.length() == 8) {
            return this.iCreditDao.findById(dni)
                    .map(credit -> new ResponseEntity<>(credit, HttpStatus.OK))
                    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
        throw new NotFoundException("El id debe ser de 8 caracteres");
	}

	@Override
	public Mono<ResponseEntity<Credit>> insert(Credit credit) {
		Flux<Client> client = webClientBuilder.build().get().uri("http://webflux-client/api/v1/client", credit.getDniCreador()).retrieve().bodyToFlux(Client.class);
		System.out.print("\nclient: " + client
				+ " - dni : " + client.map(c -> c.getDni())
				//+ " - feignclient dni: " + iClient.getClient(credit.getDniCreador()).map(cli -> cli.getBody().getTypeAccount()).defaultIfEmpty(null)
				//+ " otro feif : " + this.iClient.getClient(credit.getDniCreador()).map(c -> { return c; })
				+ "\n");


		List<String> types = List.of("CC", "AH", "PF");

		if (!types.contains(credit.getTypeAccount())) {
			throw new NotFoundException("Tipo de cuenta invalido");
		}
		credit.setCash(0);
		credit.setDateRegister(LocalDateTime.now());
		return this.iCreditDao.insert(credit)
		.map(cli -> new ResponseEntity<>(cli, HttpStatus.ACCEPTED))
		.defaultIfEmpty(new ResponseEntity<>(credit, HttpStatus.NOT_ACCEPTABLE));
	}

	@Override
	public Mono<ResponseEntity<Credit>> updateCashSum(float cash, String id) {
		return this.iCreditDao.findById(id)
		.flatMap(credit -> {
		  credit.setCash(credit.getCash() + cash);
		  return this.iCreditDao.save(credit)
			.map(creditSave -> new ResponseEntity<>(creditSave, HttpStatus.ACCEPTED));
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}