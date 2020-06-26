package com.mint.cardservice.controllertest;


import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import com.mint.cardservice.controllers.CardController;
import com.mint.cardservice.entities.Card;
import com.mint.cardservice.repositories.CardRepository;
import com.mint.cardservice.services.CardService;
import com.mint.cardservice.services.HitCountService;

@RunWith(SpringRunner.class)
@WebMvcTest(CardController.class)
public class CardControllerTest {
	
   @Autowired
   private MockMvc mvc;
   
   @MockBean
   KafkaTemplate<String, String> KafkaJsontemplate;
   
   @MockBean
   private CardService cardController;
   
   @MockBean
   private HitCountService hitController;
   
   @MockBean
   private CardRepository cardRepository;
   
   
   @Before
   public void setUp() {
       Card card = new Card();
       card.setAccountNumber("45717360");
       card.setBank("ACCESS");
       card.setScheme("Mastercard");
       card.setType("debit");
       Mockito.when(cardRepository.findByAccountNumber(card.getAccountNumber()))
         .thenReturn(card);
   }
   
	@Test
	public void getCardDetails() throws Exception {
		
       mvc.perform(get("/card-scheme/verify/45717360")
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(1)));
	}
	
//	@Test
//	public void getHitCounts() throws Exception {
//		
//	}

}
