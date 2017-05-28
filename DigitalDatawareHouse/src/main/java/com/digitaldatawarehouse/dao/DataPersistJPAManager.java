package com.digitaldatawarehouse.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.digitaldatawarehouse.model.DealDetails;
@Repository
public class DataPersistJPAManager {
	
	@PersistenceContext
	private EntityManager entityManager;
	 
	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
	private int batchSize;
	@Transactional 
	public <T extends DealDetails> Collection<T> bulkSave(Collection<T> entities) {
	  final List<T> savedEntities = new ArrayList<T>(entities.size());
	  int i = 0;
	  for (T t : entities) {
	    savedEntities.add(persistOrMerge(t));
	    i++;
	    if (i % batchSize == 0) {
	      // Flush a batch of inserts and release memory.
	      entityManager.flush();
	      entityManager.clear();
	    }
	  }
	  return savedEntities;
	}
	@Transactional  
	private <T extends DealDetails> T persistOrMerge(T t) {
		try {
			
			if (t.getDealId() == null) {
				entityManager.persist(t);
				return t;
			} else {
				return entityManager.merge(t);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
