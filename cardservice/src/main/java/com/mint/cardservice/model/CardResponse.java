package com.mint.cardservice.model;

import lombok.Data;

@Data
public class CardResponse {
	
	private Boolean success;
	private CardPayload payload;
}
