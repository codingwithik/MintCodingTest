package com.mint.cardservice.model;

import lombok.Data;

@Data
public class ConsumerPayload {
	
	private String scheme;
	private String type;
	private String bank;
}
