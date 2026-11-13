package com.mycom.myapp.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.mycom.myapp.rest.resources.ProductResource;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
	// No need to override getClasses() or getSingletons()
	// JAX-RS will auto-discover resources in the WAR module.

	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<>(Arrays.asList(ProductResource.class));
	}

}