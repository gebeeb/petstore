package com.enums;

public enum PetStatus {
	AVAILABLE("available"),
	PENDING("pending"),
	SOLD("sold");
	
	private String description;
	
	PetStatus(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

}
