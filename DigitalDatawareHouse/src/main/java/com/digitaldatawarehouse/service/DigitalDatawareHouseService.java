package com.digitaldatawarehouse.service;

import java.util.List;

import com.digitaldatawarehouse.model.DealDetails;
import com.digitaldatawarehouse.model.DealDetailsValidRows;

public interface DigitalDatawareHouseService {
	public DealDetailsValidRows addDeal(DealDetailsValidRows deal);
	public Iterable<DealDetailsValidRows> addDeals(List<DealDetailsValidRows> deal);
	public void updateDeal(DealDetailsValidRows deal);
	public void deleteDeal(DealDetailsValidRows deal);
	public void findDeal(DealDetailsValidRows deal);
	public void persistDeals(List<DealDetails> deals);
	//void persistDeals(List<DealDetails> deals);

}
