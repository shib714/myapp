package com.mycom.myapp.service.impl;

import java.util.List;

import com.mycom.myapp.entity.Product;
import com.mycom.myapp.service.ProductService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
        return em.createQuery("SELECT p FROM Product p", Product.class)
                 .getResultList();
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
