package com.mycom.myapp.service;

import java.util.List;

import javax.ejb.Local;

import com.mycom.myapp.entity.Product;

@Local
public interface ProductService {

	Product create(Product product);

	Product find(Long id);

	List<Product> findAll();

	Product update(Product product);

	void delete(Long id);
}
