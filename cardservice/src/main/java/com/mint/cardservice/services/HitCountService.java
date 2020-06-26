package com.mint.cardservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mint.cardservice.entities.HitCount;
import com.mint.cardservice.repositories.HitCountRepository;

@Service
public class HitCountService {
	
	private HitCountRepository hitRepository;

	public HitCountService(HitCountRepository hitRepository) {
		this.hitRepository = hitRepository;
	}

	public Optional<HitCount> findById(Long id) {
		return hitRepository.findById(id);
	}
	
	public Optional<HitCount> findByAccountNumber(String acct) {
		return hitRepository.findByAccountNumber(acct);
	}
	
	public Page<HitCount> findAll(Pageable pageable) {
		return (Page<HitCount>) hitRepository.findAll(pageable);
	}

	
	public void deleteById(Long id) {
		hitRepository.deleteById(id);
	}

	public void delete(HitCount hit) {
		hitRepository.delete(hit);
	}

	public HitCount save(HitCount hit) {
		return hitRepository.save(hit);
	}

	public void saveAll(List<HitCount> hits) {
		hitRepository.saveAll(hits);
	}

	public boolean existsById(Long id) {
		return hitRepository.existsById(id);
	}

	public long count() {
		return hitRepository.count();
	}

	public void deleteAll(List<HitCount> hits) {
		hitRepository.deleteAll(hits);
	}

	public void deleteAll() {
		hitRepository.deleteAll();
	}
}
