package com.digitaldatawarehouse.model;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Deal {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dealId;
	private String ordringCurrency;
	private String tocurrencyISOcode;
	private Date timestamp;
	private double dealAmount;
	
	public Long getDealId() {
		return dealId;
	}
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	public String getOrdringCurrency() {
		return ordringCurrency;
	}
	public void setOrdringCurrency(String ordringCurrency) {
		this.ordringCurrency = ordringCurrency;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public double getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(double dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getTocurrencyISOcode() {
		return tocurrencyISOcode;
	}
	public void setTocurrencyISOcode(String tocurrencyISOcode) {
		this.tocurrencyISOcode = tocurrencyISOcode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dealAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((ordringCurrency == null) ? 0 : ordringCurrency.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((tocurrencyISOcode == null) ? 0 : tocurrencyISOcode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deal other = (Deal) obj;
		if (Double.doubleToLongBits(dealAmount) != Double.doubleToLongBits(other.dealAmount))
			return false;
		if (ordringCurrency == null) {
			if (other.ordringCurrency != null)
				return false;
		} else if (!ordringCurrency.equals(other.ordringCurrency))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (tocurrencyISOcode == null) {
			if (other.tocurrencyISOcode != null)
				return false;
		} else if (!tocurrencyISOcode.equals(other.tocurrencyISOcode))
			return false;
		return true;
	}

}
