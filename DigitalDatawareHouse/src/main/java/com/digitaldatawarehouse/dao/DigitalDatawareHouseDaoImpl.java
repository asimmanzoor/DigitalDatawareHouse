package com.digitaldatawarehouse.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

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
		List<List<DealDetailsValidRows>> dealList = new ArrayList<List<DealDetailsValidRows>>();
		ExecutorService executorService = Executors.newWorkStealingPool();
		if (deals.size() > 40) {
			
		}
		int start = 0;
		int end = 2500;
		// creating sub list of data items
		try {
			for (int i = 1; i <= 30; i++) {
				dealList.add(new ArrayList<DealDetailsValidRows>(deals.subList(start, end)));
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
		List<List<DealDetailsInValidRows>> dealList = new ArrayList<List<DealDetailsInValidRows>>();
		ExecutorService executorService = Executors.newWorkStealingPool();
		int start = 0;
		int end = 2500;
		// creating sub list of data items
		try {
			for (int i = 1; i <= 10; i++) {
				dealList.add(new ArrayList<DealDetailsInValidRows>(deals.subList(start, end)));
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
