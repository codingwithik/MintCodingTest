package com.mint.cardservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mint.cardservice.entities.Card;

public interface CardRepository extends CrudRepository<Card, Long>{
	
	Card findByAccountNumber(String acc);
}
