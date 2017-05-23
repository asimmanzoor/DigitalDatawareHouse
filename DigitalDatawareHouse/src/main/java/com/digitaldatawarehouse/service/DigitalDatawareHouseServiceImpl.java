package com.digitaldatawarehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaldatawarehouse.dao.DigitalDatawareHouseDao;
import com.digitaldatawarehouse.model.Deal;

@Service
public class DigitalDatawareHouseServiceImpl implements DigitalDatawareHouseService {

	@Autowired
	DigitalDatawareHouseDao digitalDatawareHouseDao;
	@Override
	public Deal addDeal(Deal deal) {
		// TODO Auto-generated method stub
		return digitalDatawareHouseDao.save(deal);
	}

	@Override
	public void updateDeal(Deal deal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDeal(Deal deal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findDeal(Deal deal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<Deal> addDeals(List<Deal> deals) {
		// TODO Auto-generated method stub
		//deals.iterator()
		return	digitalDatawareHouseDao.save(deals);
		//return digitalDatawareHouseDao.
	}
	
}
