package com.mithrandir.springcrud.configuration;


import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mithrandir.springcrud.entity.Authorities;
import com.mithrandir.springcrud.entity.Product;
import com.mithrandir.springcrud.entity.ProductDetail;
import com.mithrandir.springcrud.entity.User;


@Configuration
@EnableTransactionManagement
@PropertySource("classpath:config/persistence-mysql.properties")
public class HibernateConfig {
	
	@Autowired
	private Environment env;
	

	private Logger logger = Logger.getLogger(getClass().getName());
 
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        //factoryBean.setConfigLocation(context.getResource("classpath:hibernate.cfg.xml"));
        factoryBean.setDataSource(comboPooledDataSource());
        //factoryBean.setPackagesToScan(new String[] { "com.mithrandir.springcrud.entity" });
        
        Properties props = new Properties();
        
        props.put("SHOW_SQL", env.getProperty("hibernate.show_sql"));
        
        factoryBean.setHibernateProperties(props);
        factoryBean.setAnnotatedClasses(Product.class, ProductDetail.class, User.class, Authorities.class);
        return factoryBean;
    }
    
    
    @Bean
    public DataSource comboPooledDataSource() {
    	ComboPooledDataSource ds = new ComboPooledDataSource();
	    
    	// set jdbc driver class
    	try {
    		ds.setDriverClass(env.getProperty("jdbc.driver"));
    	} catch (PropertyVetoException e) {
    		throw new RuntimeException(e);
    	}
    	
    	logger.info(">>>> jdbc.url = " + env.getProperty("jdbc.url"));
		logger.info(">>>> jdbc.user = " + env.getProperty("jdbc.user"));
 
		// set database connection props
    	ds.setJdbcUrl(env.getProperty("jdbc.url"));
    	ds.setUser(env.getProperty("jdbc.user"));
    	ds.setPassword(env.getProperty("jdbc.password"));
 
    	logger.info(">>>> connection.pool.initialPoolSize = " + env.getProperty("connection.pool.initialPoolSize"));
		logger.info(">>>> connection.pool.minPoolSize = " + env.getProperty("connection.pool.minPoolSize"));
		logger.info(">>>> connection.pool.maxPoolSize = " + env.getProperty("connection.pool.maxPoolSize"));
		logger.info(">>>> connection.pool.maxIdleTime = " + env.getProperty("connection.pool.maxIdleTime"));
	     
		ds.setInitialPoolSize(Integer.parseInt(env.getProperty("connection.pool.initialPoolSize")));
		ds.setMinPoolSize(Integer.parseInt(env.getProperty("connection.pool.minPoolSize")));
		ds.setMaxPoolSize(Integer.parseInt(env.getProperty("connection.pool.maxPoolSize")));
		ds.setMaxIdleTime(Integer.parseInt(env.getProperty("connection.pool.maxIdleTime")));
 
		return ds;
    }
    
 
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
   
}


