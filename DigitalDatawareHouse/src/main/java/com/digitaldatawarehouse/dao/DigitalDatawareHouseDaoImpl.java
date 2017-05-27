package com.digitaldatawarehouse.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Repository;

import com.digitaldatawarehouse.model.Deal;

@Repository
public class DigitalDatawareHouseDaoImpl {
	@Autowired
	DataPersistJPAManager persist;

	public void persistBulkData(List<Deal> deals) {
		ExecutorService executorService = Executors.newWorkStealingPool();
		List<List<Deal>> dealList = new ArrayList<List<Deal>>();
		int start = 0;
		int end = 2500;
		// creating sub list of data items
		try {
			for (int i = 1; i <= 40; i++) {
				dealList.add(new ArrayList<Deal>(deals.subList(start, end)));
				start = end;
				end = (2500 * (i + 1));
			}
			System.out.println("Size = " + dealList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// creating a list of callable
		List<Callable<Integer>> callables = new ArrayList<Callable<Integer>>(dealList.size());
		for (int i = 0; i < dealList.size(); i++) {
			callables.add(callable(dealList, i));
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
		System.out.println("Total Time in to write data in db ms = " + (end2 - start2));
	}
	// a callable to persist data in db
	Callable<Integer> callable(List<List<Deal>> dealList, int index) {
		return () -> {
			persist.bulkSave(dealList.get(index));
			return dealList.get(index).size();
		};
	}
	
	
	
}
