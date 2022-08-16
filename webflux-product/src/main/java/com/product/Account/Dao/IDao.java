package com.product.Account.Dao;

import com.product.Account.Model.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IDao extends ReactiveMongoRepository<Account, String> {
}
