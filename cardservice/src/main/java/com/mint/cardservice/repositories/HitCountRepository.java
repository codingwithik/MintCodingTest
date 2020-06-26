package com.mint.cardservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.mint.cardservice.entities.HitCount;

public interface HitCountRepository extends PagingAndSortingRepository<HitCount, Long>{
	
	Optional<HitCount> findByAccountNumber(String acc);
}
