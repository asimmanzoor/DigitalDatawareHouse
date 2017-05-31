package com.digitaldatawarehouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitaldatawarehouse.dao.DigitalDatawareHouseDaoImpl;
import com.digitaldatawarehouse.model.DealDetails;
import com.digitaldatawarehouse.model.DealDetailsValidRows;
import com.digitaldatawarehouse.service.DigitalDatawareHouseServiceImpl;
import com.digitaldatawarehouse.utils.DigitalDatawareHouseUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Scope(scopeName="prototype")
public  class DigitalDatawareHouseApplicationTests {

	@Autowired
	DigitalDatawareHouseServiceImpl digitalDatawareHouseServiceImpl;
	@Autowired
	DigitalDatawareHouseDaoImpl digitalDatawareHouseDaoImpl;
	/*@Autowired
	DigitalDatawareHouseDaoImpl digitalDatawareHouseDaoImpl;*/
	
	@Test
	public void contextLoads() {
	}
	//@Test
	public void saveDeal() {
		DealDetailsValidRows deal = new DealDetailsValidRows();
		//deal.setDealId("1");
		deal.setDealAmount(41.2);
		deal.setOrderingCurrency("doller");
		deal.setTimestamp(new Date());
		DealDetailsValidRows d = digitalDatawareHouseServiceImpl.addDeal(deal);
		if (d !=null && d.equals(deal)) {
			System.out.println("Data Saved!");
		} else {
			System.out.println("Data Not Saved!");
		}
	}
	//@Test
	public void saveDeal2() {
		DealDetailsValidRows deal = new DealDetailsValidRows();
		//deal.setDealId("1");
		deal.setDealAmount(42.4);
		deal.setOrderingCurrency("doller");
		deal.setTimestamp(new Date());
		DealDetailsValidRows d = digitalDatawareHouseServiceImpl.addDeal(deal);
		if (d !=null && d.equals(deal)) {
			System.out.println("Data Saved!");
		} else {
			System.out.println("Data Not Saved!");
		}
	}
	
	//@Test
	public void saveDeals() {
		DealDetailsValidRows deal = new DealDetailsValidRows();
		//deal.setDealId("1");
		deal.setDealAmount(43.4);
		deal.setOrderingCurrency("doller");
		deal.setTimestamp(new Date());
		DealDetailsValidRows deal2 = new DealDetailsValidRows();
		//deal.setDealId("1");
		deal2.setDealAmount(45.4);
		deal2.setOrderingCurrency("doller");
		deal2.setTimestamp(new Date());
		DealDetailsValidRows deal3 = new DealDetailsValidRows();
		//deal.setDealId("1");
		deal3.setDealAmount(46.4);
		deal3.setOrderingCurrency("doller");
		deal3.setTimestamp(new Date());
		List<DealDetailsValidRows> deals = new ArrayList<DealDetailsValidRows>();
		deals.add(deal);
		deals.add(deal2);
		deals.add(deal3);
		
		
		
		Iterable<DealDetailsValidRows> dItr = digitalDatawareHouseServiceImpl.addDeals(deals);
		System.out.println("List Saved!!");
		//dItr.forEach(action);
		
		/*if (dItr !=null && d.equals(deal)) {
			System.out.println("Data Saved!");
		} else {
			System.out.println("Data Not Saved!");
		}*/
	}
	
	//@Test
	public void writeCSV() {
		DigitalDatawareHouseUtils.writeCsvFile();
			
	}
	
	//@Test
	public void readCSV() {
		long start = System.currentTimeMillis();
		//System.out.println(start);
		List<DealDetails> deals = DigitalDatawareHouseUtils.processInputCSVFile("/home/asim/digitaldatawarehouse/shared/digitaldatawarehouse.csv");
		long end = System.currentTimeMillis();
		System.out.println("Total Time in ms = " + (end - start));
		digitalDatawareHouseServiceImpl.persistDeals(deals);
				
		System.out.println("Total Deal found = " + deals.size());
	}

	
}
