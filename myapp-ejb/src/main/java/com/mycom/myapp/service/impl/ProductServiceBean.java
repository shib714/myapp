package com.mycom.myapp.service.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mycom.myapp.entity.Product;
import com.mycom.myapp.service.ProductService;


@Stateless
public class ProductServiceBean implements ProductService {

    @PersistenceContext(unitName = "shoppingPU")
    private EntityManager em;

    @Override
    public Product create(Product product) {
        em.persist(product);
        return product;
    }

    @Override
    public Product find(Long id) {
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        String jpql = "SELECT p FROM Product p";
        List<Product> products = em.createQuery(jpql, Product.class)
                 .getResultList();
        return products;
    }

    @Override
    public Product update(Product product) {
        return em.merge(product);
    }

    @Override
    public void delete(Long id) {
        Product p = em.find(Product.class, id);
        if (p != null) {
            em.remove(p);
        }
    }
}
