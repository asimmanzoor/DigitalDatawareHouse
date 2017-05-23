package com.digitaldatawarehouse.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Deal {
	@Id
	private String dealId;
	private String ordringCurrency;
	private Date timestamp;
	private double dealAmount;
	
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(dealAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((dealId == null) ? 0 : dealId.hashCode());
		result = prime * result + ((ordringCurrency == null) ? 0 : ordringCurrency.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
		if (dealId == null) {
			if (other.dealId != null)
				return false;
		} else if (!dealId.equals(other.dealId))
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
		return true;
	}
	
	
	
	
	

}
