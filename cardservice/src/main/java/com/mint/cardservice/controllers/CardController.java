package com.mint.cardservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mint.cardservice.entities.Card;
import com.mint.cardservice.entities.HitCount;
import com.mint.cardservice.model.BinList;
import com.mint.cardservice.model.CardPayload;
import com.mint.cardservice.model.CardResponse;
import com.mint.cardservice.services.CardService;
import com.mint.cardservice.services.HitCountService;

@RestController
@RequestMapping("card-scheme")
public class CardController {
	
	@Autowired
    KafkaTemplate<String, String> KafkaJsontemplate;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private HitCountService hitService;
	
//	@Value(value = "${kafka.topic}")
    private String TOPIC_NAME="com.mintfintech.card_verified";
	
    @GetMapping(value = "/verify/{cardnumber}")
    public CardResponse sendMessage(@PathVariable String cardnumber){
    	
    	Card details = cardService.getDetails(cardnumber);
    	
    	String card = saveCount(cardnumber);
    	
    	if(card.equals("Request Successful")) {
    		
    		CardResponse response = new CardResponse();
    		CardPayload payload = new CardPayload();
    		payload.setBank(details.getBank());
    		payload.setScheme(details.getScheme());
    		payload.setType(details.getType());
    		
        	response.setSuccess(true);
            response.setPayload(payload);
            
            return response;
    	}
    	
    	CardResponse response = new CardResponse();
		
    	response.setSuccess(false);
        response.setPayload(null);
        
        return response;
    }
    
    
    public String saveCount(String acct) {
    	
    	//check if account already exists
    	HitCount hit = hitService.findByAccountNumber(acct).orElse(null);
    	BinList response = cardService.getCardDetails(acct);
    	
    	if(response == null)
    		return "Request Unsuccessful";
    	
    	if(response.getBank() == null || response.getType() == null)
    		return "Request Unsuccessful";
    	
    	if(hit == null) {
    		
    		hit = new HitCount();
    		
    		hit.setAccountNumber(acct);
    		hit.setCount(1);
    		
    	}
    	
    	hit.setCount(hit.getCount() + 1);
    	hitService.save(hit);
    	
    	//send payload
    	CardPayload card = new CardPayload();
    	card.setBank(response.getBank().getName());
    	card.setScheme(response.getScheme());
    	card.setType(response.getType());
    	
    	try {
			KafkaJsontemplate.send(TOPIC_NAME, objectMapper.writeValueAsString(card));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "Request Successful";
    	
    }
    

}
