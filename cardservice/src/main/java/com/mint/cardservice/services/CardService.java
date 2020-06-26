package com.mint.cardservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mint.cardservice.entities.Card;
import com.mint.cardservice.model.BinList;
import com.mint.cardservice.repositories.CardRepository;

@Service
public class CardService {
	
	private CardRepository cardRepository;
	
	@Autowired
    private RestTemplate restTemplate;

	public CardService(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	public Optional<Card> findById(Long id) {
		return cardRepository.findById(id);
	}
	
	public Card findByAccountNumber(String acct) {
		return cardRepository.findByAccountNumber(acct);
	}
	
	public List<Card> findAll() {
		return (List<Card>) cardRepository.findAll();
	}

	
	public void deleteById(Long id) {
		cardRepository.deleteById(id);
	}

	public void delete(Card card) {
		cardRepository.delete(card);
	}

	public Card save(Card card) {
		return cardRepository.save(card);
	}

	public void saveAll(List<Card> cards) {
		cardRepository.saveAll(cards);
	}

	public boolean existsById(Long id) {
		return cardRepository.existsById(id);
	}

	public long count() {
		return cardRepository.count();
	}

	public void deleteAll(List<Card> cards) {
		cardRepository.deleteAll(cards);
	}

	public void deleteAll() {
		cardRepository.deleteAll();
	}
	
	public BinList getCardDetails(String accountNumber) {
    	BinList response = restTemplate.getForObject("https://lookup.binlist.net/"+accountNumber, BinList.class);
        return response;
    }
	
	public Card getDetails(String acct) {
		
		Card card = findByAccountNumber(acct);
		
		BinList resp = getCardDetails(acct);
		
		if(card == null) {
			
			card = new Card();
			
			card.setAccountNumber(acct);
			card.setBank(resp.getBank().getName());
			card.setScheme(resp.getScheme());
			card.setType(resp.getType());
			
			return save(card);
			
		}
		
		return card;
	}

}
