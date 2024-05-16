package com.springmvc.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Shipping implements Serializable{

	private static final long serialVersionUID = 8121814661110003493L;
	
	private String name;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date date;
	private Address address;
	
	public Shipping() {
		this.address = new Address();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
