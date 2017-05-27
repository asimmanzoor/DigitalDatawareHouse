package com.digitaldatawarehouse.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.digitaldatawarehouse.model.Deal;

@Repository
public interface DigitalDatawareHouseDao extends CrudRepository<Deal, String> {
	//public abstract <T> void persistBulkData(Collection<T> data);
}
