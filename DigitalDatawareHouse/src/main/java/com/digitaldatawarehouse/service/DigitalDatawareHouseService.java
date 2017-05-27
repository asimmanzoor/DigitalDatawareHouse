package com.digitaldatawarehouse.service;

import java.util.List;

import com.digitaldatawarehouse.model.Deal;

public interface DigitalDatawareHouseService {
	public Deal addDeal(Deal deal);
	public Iterable<Deal> addDeals(List<Deal> deal);
	public void updateDeal(Deal deal);
	public void deleteDeal(Deal deal);
	public void findDeal(Deal deal);
	public void persistDeals(List<Deal> deals);

}
