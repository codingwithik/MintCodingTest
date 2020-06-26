package com.mint.cardservice.model;

import lombok.Data;

public @Data class CardPayload {
	
	private String scheme;
	private String type;
	private String bank;
}
