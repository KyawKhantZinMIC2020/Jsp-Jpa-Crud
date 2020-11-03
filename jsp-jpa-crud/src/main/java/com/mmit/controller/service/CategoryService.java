package com.mmit.controller.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mmit.model.entity.Category;

public class CategoryService {
	private EntityManager em;
	
	public CategoryService(EntityManager em) {
		this.em = em;
	}

	public List<Category> findAll() {
		TypedQuery<Category> query = em.createNamedQuery("Category.getAll", Category.class);
		List<Category> list = query.getResultList();
		return list;
	}

	public void save(Category c) {
		// TODO Auto-generated method stub
		em.getTransaction().begin();
		//em.persist(c);
		if(c.getId()==0)//new category
			em.persist(c);
		else
			em.merge(c);//edit
		em.getTransaction().commit();
		
	}

	public Category findById(int id) {
		return em.find(Category.class,id);
	}

	public void delete(int id) {
		em.getTransaction().begin();
		Category category = em.find(Category.class, id);
		em.remove(category);
		em.getTransaction().commit();
	}
}

