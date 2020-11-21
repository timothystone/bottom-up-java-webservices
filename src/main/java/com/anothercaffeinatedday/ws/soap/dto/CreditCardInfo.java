package com.anothercaffeinatedday.ws.soap.dto;

import java.util.Date;

public class CreditCardInfo {

	String cardNumber;
	private Date expirationDate;
	String firstName;
	String lastName;
	String CVV2;
	String Address;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCVV2() {
		return CVV2;
	}

	public void setCVV2(String CVV2) {
		this.CVV2 = CVV2;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}
