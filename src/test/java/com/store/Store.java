package com.store;

public class Store {
	
	private long id;
	private long petId;
	private int quantity;
	private String shipDate;
	private String status;
	private Boolean complete;
	
	public Store (long id, long petId, int quantity, String shipDate, String status, boolean parsedComplete) {
		this.id = id;
		this.petId = petId;
		this.quantity = quantity;
		this.shipDate = shipDate;
		this.status = status;
		this.complete = parsedComplete;
	}
	
	   // Getter for id
    public long getId() {
        return id;
    }

    // Setter for id
    public void setId(long id) {
        this.id = id;
    }

    // Getter for petId
    public long getPetId() {
        return petId;
    }

    // Setter for petId
    public void setPetId(long petId) {
        this.petId = petId;
    }

    // Getter for quantity
    public int getQuantity() {
        return quantity;
    }

    // Setter for quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Getter for shipDate
    public String getShipDate() {
        return shipDate;
    }

    // Setter for shipDate
    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter for complete
    public Boolean getComplete() {
        return complete;
    }

    // Setter for complete
    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

}
