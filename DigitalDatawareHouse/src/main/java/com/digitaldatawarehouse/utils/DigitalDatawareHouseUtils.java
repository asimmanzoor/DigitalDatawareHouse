package com.digitaldatawarehouse.utils;

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

import com.digitaldatawarehouse.model.DealDetails;
import com.digitaldatawarehouse.model.DealDetailsInValidRows;
import com.digitaldatawarehouse.model.DealDetailsValidRows;

public class DigitalDatawareHouseUtils {
	
	static String fileName;
	public static void writeCsvFile() {
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
	
	public static List<DealDetails> processInputCSVFile(String inputFilePath) {
	    List<DealDetails> inputList = new ArrayList<DealDetails>();
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
	
	public static List<DealDetails> processInputCSVFile(InputStream inputFS, String fileNameLocal) {
	    List<DealDetails> inputList = new ArrayList<DealDetails>();
	    try{
	    	 /* byte[] bytes = file.getBytes();
	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            Files.write(path, bytes);	
	      File inputF = new File(inputFilePath);
	      InputStream inputFS = new FileInputStream(inputF);*/
	     fileName = fileNameLocal;
	      BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
	      // skip the header of the csv
	      inputList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());
	      br.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return inputList ;
	}
	
	public static Function<String, DealDetails> mapToItem = (line) -> {
		  String[] p = line.split(",");// a CSV has comma separated lines
		  DealDetails deal = null;
		  //deal.setDealId(Long.parseLong(p[0]));
		  if (Long.parseLong(p[0])% 4 ==0) {
			  deal = new DealDetailsInValidRows();
			  ((DealDetailsInValidRows) deal).setOrderingCurrency(p[1]);
			  ((DealDetailsInValidRows) deal).setTocurrencyISOcode(p[2]);
			  ((DealDetailsInValidRows) deal).setTimestamp(new Date());
			  ((DealDetailsInValidRows) deal).setDealAmount(Double.parseDouble(p[4]));
			  ((DealDetailsInValidRows) deal).setFileName(fileName);
		  } else {
			  deal = new DealDetailsValidRows();
			  ((DealDetailsValidRows) deal).setOrderingCurrency(p[1]);
			  ((DealDetailsValidRows) deal).setTocurrencyISOcode(p[2]);
			  ((DealDetailsValidRows) deal).setTimestamp(new Date());
			  ((DealDetailsValidRows) deal).setDealAmount(Double.parseDouble(p[4]));
			  ((DealDetailsValidRows) deal).setFileName(fileName);
		  }
		  
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
		  //deal.setDealAmount(Double.parseDouble(p[4]));
		 // deal.setDealAmount(dealAmount);
		  
		  //setItemNumber(p[0]);//<-- this is the first column in the csv file
		 /* if (p[3] != null && p[3].trim().length() > 0) {
			  deal.setSomeProeprty(p[3]);
		  }*/
		  //more initialization goes here
		  return deal;
		};


}
