package com.mycom.myapp.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mycom.myapp.entity.Product;

@ExtendWith(MockitoExtension.class)
class ProductServiceBeanTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Product> typedQuery;

    private ProductServiceBean productServiceBean;

    @BeforeEach
    void setUp() throws Exception {
        productServiceBean = new ProductServiceBean();
        injectEntityManager(productServiceBean, entityManager);
    }

    @Test
    void create_shouldPersistAndReturnProduct() {
        Product product = new Product();

        Product result = productServiceBean.create(product);

        verify(entityManager).persist(product);
        assertEquals(product, result);
    }

    @Test
    void find_shouldDelegateToEntityManager() {
        Product product = new Product();
        when(entityManager.find(Product.class, 1L)).thenReturn(product);

        Product result = productServiceBean.find(1L);

        verify(entityManager).find(Product.class, 1L);
        assertEquals(product, result);
    }

    @Test
    void findAll_shouldReturnListFromQuery() {
        List<Product> products = List.of(new Product(), new Product());
        when(entityManager.createQuery("SELECT p FROM Product p", Product.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(products);

        List<Product> result = productServiceBean.findAll();

        verify(entityManager).createQuery("SELECT p FROM Product p", Product.class);
        verify(typedQuery).getResultList();
        assertEquals(products, result);
    }

    @Test
    void update_shouldMergeAndReturnProduct() {
        Product product = new Product();
        when(entityManager.merge(product)).thenReturn(product);

        Product result = productServiceBean.update(product);

        verify(entityManager).merge(product);
        assertEquals(product, result);
    }

    @Test
    void delete_shouldRemoveWhenProductExists() {
        Product product = new Product();
        when(entityManager.find(Product.class, 1L)).thenReturn(product);

        assertDoesNotThrow(() -> productServiceBean.delete(1L));

        verify(entityManager).find(Product.class, 1L);
        verify(entityManager).remove(product);
    }

    @Test
    void delete_shouldDoNothingWhenProductDoesNotExist() {
        when(entityManager.find(Product.class, 99L)).thenReturn(null);

        assertDoesNotThrow(() -> productServiceBean.delete(99L));

        verify(entityManager).find(Product.class, 99L);
    }

    private void injectEntityManager(Object target, EntityManager entityManager) throws Exception {
        Field field = ProductServiceBean.class.getDeclaredField("em");
        field.setAccessible(true);
        field.set(target, entityManager);
    }
}
