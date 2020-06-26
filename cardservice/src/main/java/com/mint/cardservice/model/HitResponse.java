package com.mint.cardservice.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

@Data
public class HitResponse {
	
	private boolean success;
	private int start;
	private int limit;
	private int size;
	private Map<String,Integer> payload = new HashMap<>();
}
