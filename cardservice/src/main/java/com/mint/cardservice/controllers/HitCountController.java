package com.mint.cardservice.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mint.cardservice.entities.HitCount;
import com.mint.cardservice.model.HitResponse;
import com.mint.cardservice.services.HitCountService;

@RestController
@RequestMapping("card-scheme")
public class HitCountController {
	
	@Autowired
	private HitCountService hitService;
	
	@GetMapping(value = "/stats")
    public HitResponse getCount(int start, int limit){
    	
    	Page<HitCount> hit = hitService.findAll(PageRequest.of(start, limit));
    	Map<String, Integer> map = new HashMap<>();
    	
    	HitResponse response = new HitResponse();
    	
    	response.setSuccess(true);
    	response.setLimit(limit);
    	response.setSize(hit.getTotalPages());
    	response.setStart(start);
    	
    	for(HitCount content :hit.getContent()) 
    		map.put(content.getAccountNumber(), content.getCount());
    		
    	response.setPayload(map);
    	
        return response;
    }
	
}
