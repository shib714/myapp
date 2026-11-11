package com.mycom.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.mycom.models.Product;
import com.mycom.services.ProductService;

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
    public List<Product> findByCategory(String category) {
        System.out.println("DEBUG INSIDE EJB METHOD: ProductServiceBean.findByCategory() called");
        return em.createQuery("SELECT p FROM Product p WHERE p.category = :category", Product.class)
                .setParameter("category", category)
                .getResultList();
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
