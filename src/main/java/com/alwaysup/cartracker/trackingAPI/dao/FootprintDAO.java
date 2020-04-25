package com.alwaysup.cartracker.trackingAPI.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.alwaysup.cartracker.trackingAPI.model.Footprint;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;

@Repository
public class FootprintDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Footprint getFootprintById(int id) {
		return null;
	}

	public void addFootprint(Footprint footprint) {
		
	}	

	public List getAll() {
		Criteria criteria = sessionFactory.openSession().createCriteria(Footprint.class);
		return criteria.list();
	}
}