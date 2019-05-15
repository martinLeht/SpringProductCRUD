package com.mithrandir.springcrud.service;

import java.util.List;

import com.mithrandir.springcrud.entity.Product;

public interface ProductService {

	public List<Product> getProducts();
	
	public void saveProduct(Product product);
	
	public Product getProduct(int id);
	
	public void deleteProduct(int id);
	
	public List<Product> searchProducts(String productName);
	
}
