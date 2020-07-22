package com.pojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserDetails implements Comparable<UserDetails>{

	private String bankName;
	private String cardNumber;
	private String expiryDate;
	private Date expdate;

	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getExpdate() {
		return expdate;
	}
	public void setExpdate(Date expdate) {
		this.expdate = expdate;
	}
	public void setExpdate(String input) throws ParseException {
		this.expdate = new SimpleDateFormat("MMM-yyyy").parse(input);
	}
	
	@Override
	public int compareTo(UserDetails k) {
		return k.getExpdate().compareTo(getExpdate());
	}
	public UserDetails() {
		super();
	}
	public UserDetails(String bankName, String cardNumber, String expiryDate) throws Exception {
		super();
		this.bankName = bankName;
		this.cardNumber = validateAndObfuscateCardNumber(cardNumber);
		if(this.cardNumber.isEmpty()) {
			throw new Exception("Not a valid Card number");
		}
		this.expiryDate = expiryDate;
		setExpdate(expiryDate);
	}
	private String validateAndObfuscateCardNumber(String cardNumber) {
		try {
			if(cardNumber.length()==19 && 
					"-".equals(String.valueOf(cardNumber.charAt(4))) &&
					"-".equals(String.valueOf(cardNumber.charAt(9))) &&
					"-".equals(String.valueOf(cardNumber.charAt(14))) &&
					cardNumber.replace("-", "").matches("\\d+")) {
				cardNumber = "xxxx-xxxx-xxxx-"+cardNumber.substring(cardNumber.length()-4,cardNumber.length());
			}else {
				cardNumber="";
			}
		} catch (Exception e) {
			e.printStackTrace();
			cardNumber = "";
		}
		return cardNumber;
	}
}
