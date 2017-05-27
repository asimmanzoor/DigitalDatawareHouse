package com.digitaldatawarehouse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.digitaldatawarehouse.dao.DigitalDatawareHouseDaoImpl;
import com.digitaldatawarehouse.model.Deal;
import com.digitaldatawarehouse.service.DigitalDatawareHouseServiceImpl;

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
		Deal deal = new Deal();
		//deal.setDealId("1");
		deal.setDealAmount(41.2);
		deal.setOrdringCurrency("doller");
		deal.setTimestamp(new Date());
		Deal d = digitalDatawareHouseServiceImpl.addDeal(deal);
		if (d !=null && d.equals(deal)) {
			System.out.println("Data Saved!");
		} else {
			System.out.println("Data Not Saved!");
		}
	}
	//@Test
	public void saveDeal2() {
		Deal deal = new Deal();
		//deal.setDealId("1");
		deal.setDealAmount(42.4);
		deal.setOrdringCurrency("doller");
		deal.setTimestamp(new Date());
		Deal d = digitalDatawareHouseServiceImpl.addDeal(deal);
		if (d !=null && d.equals(deal)) {
			System.out.println("Data Saved!");
		} else {
			System.out.println("Data Not Saved!");
		}
	}
	
	//@Test
	public void saveDeals() {
		Deal deal = new Deal();
		//deal.setDealId("1");
		deal.setDealAmount(43.4);
		deal.setOrdringCurrency("doller");
		deal.setTimestamp(new Date());
		Deal deal2 = new Deal();
		//deal.setDealId("1");
		deal2.setDealAmount(45.4);
		deal2.setOrdringCurrency("doller");
		deal2.setTimestamp(new Date());
		Deal deal3 = new Deal();
		//deal.setDealId("1");
		deal3.setDealAmount(46.4);
		deal3.setOrdringCurrency("doller");
		deal3.setTimestamp(new Date());
		List<Deal> deals = new ArrayList<Deal>();
		deals.add(deal);
		deals.add(deal2);
		deals.add(deal3);
		
		
		
		Iterable<Deal> dItr = digitalDatawareHouseServiceImpl.addDeals(deals);
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
		 PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("/home/asim/digitaldatawarehouse/shared/digitaldatawarehouse.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        StringBuilder sb = new StringBuilder();
	        sb.append("dealuniqueid");
	        sb.append(',');
	        sb.append("fromcurrencyISOCode");
	        sb.append(',');
	        sb.append("tocurrencyISOcode");
	        sb.append(',');
	        sb.append("dealtimestamp");
	        sb.append(',');
	        sb.append(" dealamountinorderingcurrency");
	        sb.append('\n');

	        for (int i = 1; i<= 100000;i++) {
	        	sb.append(i);
	        	sb.append(',');
	        	sb.append("USD");
	        	sb.append(',');
	        	sb.append("AED");
	        	sb.append(',');
	        	sb.append(new Date());
	        	sb.append(',');
	        	sb.append(500 + i);
	        	sb.append('\n');
	        }

	        pw.write(sb.toString());
	        pw.close();
	        System.out.println("done!");
		
	}
	
	@Test
	public void readCSV() {
		long start = System.currentTimeMillis();
		//System.out.println(start);
		List<Deal> deals = processInputCSVFile("/home/asim/digitaldatawarehouse/shared/digitaldatawarehouse.csv");
		long end = System.currentTimeMillis();
		System.out.println("Total Time in ms = " + (end - start));
		digitalDatawareHouseServiceImpl.persistDeals(deals);
				
		System.out.println("Total Deal found = " + deals.size());
	}

	private List<Deal> processInputCSVFile(String inputFilePath) {
	    List<Deal> inputList = new ArrayList<Deal>();
	    try{
	      File inputF = new File(inputFilePath);
	      InputStream inputFS = new FileInputStream(inputF);
	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
	      // skip the header of the csv
	      inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
	      br.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return inputList ;
	}
	@SuppressWarnings("deprecation")
	private Function<String, Deal> mapToItem = (line) -> {
		  String[] p = line.split(",");// a CSV has comma separated lines
		  Deal deal = new Deal();
		  //deal.setDealId(Long.parseLong(p[0]));
		  deal.setOrdringCurrency(p[1]);
		  deal.setTocurrencyISOcode(p[2]);
		  deal.setTimestamp(new Date());
	     /*  SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	       DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
	      
	       Date date = new Date();
	       df.format(new Date());
	       System.out.println();
	       try {
			Date d = DateFormat.getInstance().parse(p[3]);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	       
		  try {
			deal.setTimestamp(sdf.parse(p[3]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		  deal.setDealAmount(Double.parseDouble(p[4]));
		 // deal.setDealAmount(dealAmount);
		  
		  //setItemNumber(p[0]);//<-- this is the first column in the csv file
		 /* if (p[3] != null && p[3].trim().length() > 0) {
			  deal.setSomeProeprty(p[3]);
		  }*/
		  //more initialization goes here
		  return deal;
		};

}
