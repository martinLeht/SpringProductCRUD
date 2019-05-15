package com.mithrandir.springcrud.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mithrandir.springcrud.entity.Product;
import com.mithrandir.springcrud.entity.ProductDetail;

@Repository
public class ProductDAOimpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Product> getProducts() {
		
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// create query and sort by product name
		Query<Product> query = session.createQuery("FROM Product order by productName", Product.class);
		
		// execute query and get result list
		List<Product> productsResultList = query.getResultList();
		
		// return results
		return productsResultList;
	}

	@Override
	public void saveProduct(Product product) {
			
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// save or update given product
		session.saveOrUpdate(product);
	}
	

	@Override
	public Product getProduct(int id) {
		
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
			
		Query<Product> query = 
				session.createQuery("SELECT p FROM Product p "
						+ "JOIN FETCH p.productDetail "
						+ "WHERE p.id=:productId",
						Product.class);
		
		// set parameters in query
		query.setParameter("productId", id);
		
		// execute and get product
		Product product = query.getSingleResult();
		
		return product;
	}

	@Override
	public void deleteProduct(int id) {
		
		// get current hibernate session
		Session session = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Product product = session.get(Product.class, id);
		
		if (product != null) {
			session.delete(product);
		}
	}

	@Override
	public List<Product> searchProducts(String productName) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Query<Product> query = null;
		
		
		// only search by name if the productName is not empty
		if (productName != null && productName.trim().length() > 0) {
			
			// search for prductName
			query = session.createQuery("FROM Product WHERE lower(productName) LIKE :name", Product.class);
			query.setParameter("name", "%" + productName.toLowerCase() + "%");
		} else {
			
			// productName is empty... fetch all products
			query = session.createQuery("FROM Product ORDER BY productName", Product.class);
		}
		
		// execute and get result
		List<Product> productSearchResult = query.getResultList();
		
		// return result
		return productSearchResult;
	}

}
