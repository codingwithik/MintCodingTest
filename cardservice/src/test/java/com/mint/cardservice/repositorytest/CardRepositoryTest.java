package com.mint.cardservice.repositorytest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.mint.cardservice.entities.Card;
import com.mint.cardservice.repositories.CardRepository;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class CardRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private CardRepository cardService;
	
	@Test
	public void whenFindAllCards() {
		
	   //given
       Card card = new Card();
       card.setAccountNumber("45717360");
       card.setBank("GTB");
       card.setScheme("Visa");
       card.setType("debit");
       
       entityManager.persist(card);
       entityManager.flush();
       
       Card card2 = new Card();
       card.setAccountNumber("45717361");
       card.setBank("ACCESS");
       card.setScheme("Mastercard");
       card.setType("debit");
       
       entityManager.persist(card2);
       entityManager.flush();
       
       //when
       List<Card> cards = (List<Card>)cardService.findAll();
       
       //then
       assertThat(cards.size()).isEqualTo(2);
       assertThat(cards.get(0)).isEqualTo(card);
       assertThat(cards.get(1)).isEqualTo(card2);
	}
	
	
}
