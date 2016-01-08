package com.chapman.webapp.action;

public class Stats {

	public Stats() {
	}
	
	public Stats(String category, Double amount){
		this.category=category;
		this.amount=amount;
	}
	
	private String category;
	private Double amount;
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
