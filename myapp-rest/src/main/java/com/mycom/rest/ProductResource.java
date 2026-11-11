package com.mycom.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.mycom.models.Product;
import com.mycom.services.ProductService;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

	@EJB(beanName = "ProductServiceBean")
	private ProductService productService;

	@GET
	public List<Product> getAll() {
		return productService.findAll();
	}

	@GET
	@Path("/{id}")
	public Product getById(@PathParam("id") Long id) {
		return productService.find(id);
	}
	//http://localhost:7001/myapp/rest/api/products/category/electronics
	@GET
	@Path("category/{category}")
	public List<Product> getByCategory(@PathParam("category") String category) {
		System.out.println("DEBUG: ProductResource.getByCategory() called");
		System.out.println("DEBUG VERSION 2");
		return productService.findByCategory(category);
	}

	@POST
	public Product create(Product product) {
		return productService.create(product);
	}
}
