package com.client.Dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.client.Model.Client;

public interface IDao extends ReactiveMongoRepository<Client, String> {
}
