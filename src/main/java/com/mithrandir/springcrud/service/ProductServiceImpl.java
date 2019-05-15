package com.mithrandir.springcrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mithrandir.springcrud.dao.ProductDAO;
import com.mithrandir.springcrud.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	@Override
	@Transactional
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	@Transactional
	public void saveProduct(Product product) {
		productDAO.saveProduct(product);
	}

	@Override
	@Transactional
	public Product getProduct(int id) {
		return productDAO.getProduct(id);
	}

	@Override
	@Transactional
	public void deleteProduct(int id) {
		productDAO.deleteProduct(id);
		
	}

	@Override
	@Transactional
	public List<Product> searchProducts(String productName) {
		return productDAO.searchProducts(productName);
	}

}
