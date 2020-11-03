package com.mmit.model.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQuery(name = "Item.findAll" ,query = "SELECT i FROM Item i")
public class Item implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private int id;
	private String name;
	@Column(name = "category")
	private int price;
	@ManyToOne
	@JoinColumn(name = "category_id", referencedColumnName = "cat_id")
	private Category category;
	
	private static final long serialVersionUID = 1L;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Item() {
		super();
	}
   
}
