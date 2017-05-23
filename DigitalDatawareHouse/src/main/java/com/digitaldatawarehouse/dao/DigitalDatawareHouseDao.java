package com.digitaldatawarehouse.dao;

import org.springframework.data.repository.CrudRepository;

import com.digitaldatawarehouse.model.Deal;

public interface DigitalDatawareHouseDao extends CrudRepository<Deal, String> {

}
