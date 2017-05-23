package com.digitaldatawarehouse;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitaldatawarehouse.model.Deal;
import com.digitaldatawarehouse.service.DigitalDatawareHouseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DigitalDatawareHouseApplicationTests {

	@Autowired
	DigitalDatawareHouseServiceImpl digitalDatawareHouseServiceImpl;
	@Test
	public void contextLoads() {
	}
	@Test
	public void saveDeal() {
		Deal deal = new Deal();
		deal.setDealId("1");
		deal.setDealAmount(44.2);
		deal.setOrdringCurrency("doller");
		deal.setTimestamp(new Date());
		Deal d = digitalDatawareHouseServiceImpl.addDeal(deal);
		if (d !=null && d.equals(deal)) {
			System.out.println("Data Saved!");
		} else {
			System.out.println("Data Not Saved!");
		}
	}

}
