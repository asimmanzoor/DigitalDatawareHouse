package com.digitaldatawarehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digitaldatawarehouse.dao.DigitalDatawareHouseDao;
import com.digitaldatawarehouse.dao.DigitalDatawareHouseDaoImpl;
import com.digitaldatawarehouse.model.DealDetails;
import com.digitaldatawarehouse.model.DealDetailsValidRows;

@Service
public class DigitalDatawareHouseServiceImpl implements DigitalDatawareHouseService {

	@Autowired
	DigitalDatawareHouseDaoImpl digitalDatawareHouseDaoImpl;
	@Autowired
	DigitalDatawareHouseDao digitalDatawareHouseDao;

	@Override
	public DealDetailsValidRows addDeal(DealDetailsValidRows deal) {
		System.out.println("Inside DAO!!!!");
		return digitalDatawareHouseDao.save(deal);
	}

	@Override
	public void updateDeal(DealDetailsValidRows deal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDeal(DealDetailsValidRows deal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void findDeal(DealDetailsValidRows deal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<DealDetailsValidRows> addDeals(List<DealDetailsValidRows> deals) {
		// TODO Auto-generated method stub
		//deals.iterator()
		return	digitalDatawareHouseDao.save(deals);
		//return digitalDatawareHouseDao.
	}
	
	@Override
	public void persistDeals(List<DealDetails> deals) {
		// TODO Auto-generated method stub
		//deals.iterator()
		digitalDatawareHouseDaoImpl.persistBulkData(deals);
		//return digitalDatawareHouseDao.
	}
	
}
