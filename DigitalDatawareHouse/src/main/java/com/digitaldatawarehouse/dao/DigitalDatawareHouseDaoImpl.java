package com.digitaldatawarehouse.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.digitaldatawarehouse.model.DealDetails;
import com.digitaldatawarehouse.model.DealDetailsInValidRows;
import com.digitaldatawarehouse.model.DealDetailsValidRows;

@Repository
public class DigitalDatawareHouseDaoImpl {
	@Autowired
	DataPersistJPAManager persist;

	public void persistBulkData(List<DealDetails> deals) {
		List<DealDetailsValidRows> dealDetailsValidRowsList = new ArrayList<DealDetailsValidRows>();
		List<DealDetailsInValidRows> dealDetailsInValidRowsList = new ArrayList<DealDetailsInValidRows>();
		deals.stream().forEach(d -> {
			if (d.checkValidRow()) {
				dealDetailsValidRowsList.add((DealDetailsValidRows) d);
			} else {
				dealDetailsInValidRowsList.add((DealDetailsInValidRows) d);
			}

		});
		long start2 = System.currentTimeMillis();
		Stream.<Runnable>of(
		         () -> persistValidDealDetails(dealDetailsValidRowsList),
		         () -> persistInValidDealDetails(dealDetailsInValidRowsList))
		         .parallel()
		         .forEach(Runnable::run);

		long end2 = System.currentTimeMillis();
		System.out.println("Total Final Time in to write data in db ms = " + (end2 - start2));
		
	}

	private void persistValidDealDetails(List<DealDetailsValidRows> deals) {
		if (deals.size() == 0 ) {
			return;
		}
		List<List<DealDetailsValidRows>> dealList = new ArrayList<List<DealDetailsValidRows>>();
		ExecutorService executorService = Executors.newWorkStealingPool();
		// need to check for size more than 0
		if (deals.size() > 2500) {
			dealList.addAll(ListUtils.partition(deals, 2500));
		} else {
			dealList.addAll(ListUtils.partition(deals, deals.size()));
		}
		
		// creating a list of callable
		List<Callable<Integer>> callables = new ArrayList<Callable<Integer>>(dealList.size());
		for (int i = 0; i < dealList.size(); i++) {
			callables.add(callableValidDealDetails(dealList, i));
		}

		long start2 = System.currentTimeMillis();
		try {
			executorService.invokeAll(callables).parallelStream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}).forEach(System.out::println);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
		long end2 = System.currentTimeMillis();
		//System.out.println("Total Time in to write data in db ms = " + (end2 - start2));
	}

	// a callable to persist data in db
	Callable<Integer> callableValidDealDetails(List<List<DealDetailsValidRows>> dealList, int index) {
		return () -> {
			persist.bulkSave(dealList.get(index));
			return dealList.get(index).size();
		};
	}

	private void persistInValidDealDetails(List<DealDetailsInValidRows> deals) {
		if (deals.size() == 0 ) {
			return;
		}
		List<List<DealDetailsInValidRows>> dealList = new ArrayList<List<DealDetailsInValidRows>>();
		ExecutorService executorService = Executors.newWorkStealingPool();
		// creating sub list of data items
		// make this as property in .yml file so that user can configure 
		// as per system to get better performance
		if (deals.size() > 2500) {
			dealList.addAll(ListUtils.partition(deals, 2500));
		} else {
			dealList.addAll(ListUtils.partition(deals, deals.size()));
		}
		// creating a list of callable
		List<Callable<Integer>> callables = new ArrayList<Callable<Integer>>(dealList.size());
		for (int i = 0; i < dealList.size(); i++) {
			callables.add(callableInValidDealDetails(dealList, i));
		}

		long start2 = System.currentTimeMillis();
		try {
			executorService.invokeAll(callables).parallelStream().map(future -> {
				try {
					return future.get();
				} catch (Exception e) {
					throw new IllegalStateException(e);
				}
			}).forEach(System.out::println);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			executorService.shutdown();
		}
		long end2 = System.currentTimeMillis();
		//System.out.println("Total Time in to write data in db ms = " + (end2 - start2));
	}

	// a callable to persist data in db
	Callable<Integer> callableInValidDealDetails(List<List<DealDetailsInValidRows>> dealList, int index) {
		return () -> {
			persist.bulkSave(dealList.get(index));
			return dealList.get(index).size();
		};
	}

}
