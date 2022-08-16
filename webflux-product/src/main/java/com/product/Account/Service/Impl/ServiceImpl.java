package com.product.Account.Service.Impl;

import com.product.Account.Dao.IDao;
import com.product.Account.Model.Account;
import com.product.Account.Service.IService;
import com.product.Account.Exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class
ServiceImpl implements IService {

	private final IDao iDao;

	@Override
	public Mono<ResponseEntity<Void>> deleteContact(String dni) {
		log.info("delete {}", dni);
		return this.iDao.findById(dni)
				.flatMap(obj ->
						iDao.delete(obj)
								.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
				)
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@Override
	public Flux<Account> getAll() {
		return this.iDao.findAll();
	}

	@Override
	public Mono<ResponseEntity<Account>> get(String dni) {
		if (dni.length() == 8) {
            return this.iDao.findById(dni)
                    .map(account -> new ResponseEntity<>(account, HttpStatus.OK))
                    .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		}
        throw new NotFoundException("El id debe ser de 8 caracteres");
	}

	@Override
	public Mono<ResponseEntity<Account>> insert(Account account) {

		List<String> types = List.of("CC", "AH", "PF");

		if (!types.contains(account.getTypeAccount())) {
			throw new NotFoundException("Tipo de cuenta invalido");
		}
		account.setCash(0);
		account.setDateRegister(LocalDateTime.now());
		return this.iDao.insert(account)
		.map(cli -> new ResponseEntity<>(cli, HttpStatus.ACCEPTED))
		.defaultIfEmpty(new ResponseEntity<>(account, HttpStatus.NOT_ACCEPTABLE));
	}

	@Override
	public Mono<ResponseEntity<Account>> updateCashSum(float cash, String id) {
		return this.iDao.findById(id)
		.flatMap(account -> {
		  account.setCash(account.getCash() + cash);
		  return this.iDao.save(account)
			.map(accountSave -> new ResponseEntity<>(accountSave, HttpStatus.ACCEPTED));
		}).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

}