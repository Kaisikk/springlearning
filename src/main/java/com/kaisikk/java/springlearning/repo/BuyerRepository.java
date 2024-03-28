package com.kaisikk.java.springlearning.repo;

import com.kaisikk.java.springlearning.model.Buyer;

public interface BuyerRepository {

    Iterable<Buyer> findAll();

    Buyer findById(String id);

    Buyer save(Buyer buyer);

}
