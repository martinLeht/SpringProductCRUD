package com.mithrandir.springcrud.dao;

import java.util.List;

import com.mithrandir.springcrud.entity.Product;
import com.mithrandir.springcrud.entity.ProductDetail;

public interface ProductDAO {
	
	public List<Product> getProducts();
	
	public void saveProduct(Product product);
	
	public Product getProduct(int id);
	
	public void deleteProduct(int id);
	
	public List<Product> searchProducts(String productName);
	
}
