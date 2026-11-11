package com.mycom.services;

import java.util.List;

import javax.ejb.Local;

import com.mycom.models.Product;

@Local
public interface ProductService {

	Product create(Product product);

	Product find(Long id);

	public List<Product> findByCategory(String category);

	List<Product> findAll();

	Product update(Product product);

	void delete(Long id);
}
