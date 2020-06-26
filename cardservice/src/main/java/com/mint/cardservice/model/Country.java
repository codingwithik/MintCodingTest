package com.mint.cardservice.model;

import lombok.Data;

public @Data class Country {
	
	private String numeric;
	private String alpha2;
    private String name;
    private String emoji;
    private String currency;
    private Long latitude;
    private Long longitude;
}
