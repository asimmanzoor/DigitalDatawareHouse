package com.digitaldatawarehouse.service;

import com.digitaldatawarehouse.model.Deal;

public interface DigitalDatawareHouseService {
	public Deal addDeal(Deal deal);
	public void updateDeal(Deal deal);
	public void deleteDeal(Deal deal);
	public void findDeal(Deal deal);

}
