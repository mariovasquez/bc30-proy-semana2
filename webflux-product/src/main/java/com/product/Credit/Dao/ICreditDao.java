package com.product.Credit.Dao;

import com.product.Credit.Model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ICreditDao extends ReactiveMongoRepository<Credit, String> {
}
