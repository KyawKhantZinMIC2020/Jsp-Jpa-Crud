package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.Item;

public class ItemService  {
	
	private EntityManager em;

	public ItemService(EntityManager em) {
		this.em = em;
	}

	public List<Item> findAll() {
		TypedQuery<Item> query = em.createNamedQuery("Item.findAll", Item.class);
		List<Item>list = query.getResultList();
		return list;
	}

	public void save(Item item) {
		em.getTransaction().begin();
		if(item.getId()==0)
			em.persist(item);// new Item
		else
			em.merge(item);// edit
		em.getTransaction().commit();
		
	}

	public Item findById(int id) {
		return em.find(Item.class, id);
	}

	public void delete(int id) {
		em.getTransaction().begin();
		Item item = em.find(Item.class, id);
		em.remove(item);
		em.getTransaction().commit();
		
	}
	
}
