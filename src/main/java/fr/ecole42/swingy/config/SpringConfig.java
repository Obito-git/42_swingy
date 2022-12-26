package fr.ecole42.swingy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("fr.ecole42.swingy")
@EnableTransactionManagement
public class SpringConfig {
	@Bean (name = "mode")
	public int lolMode() {
		return 42;
	}

	@Bean
	public DataSource dataSource() {
		org.hibernate.cfg.Configuration conf = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		dataSource.setDriverClassName(conf.getProperty("hibernate.driver_class"));
		dataSource.setUrl(conf.getProperty("hibernate.connection.url"));
		dataSource.setPassword(conf.getProperty("hibernate.connection.password"));
		dataSource.setUsername(conf.getProperty("hibernate.connection.username"));
		return dataSource;
	}

	private Properties hibernateProperties() {
		org.hibernate.cfg.Configuration conf = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml");
		Properties properties = new Properties();
		properties.put("hibernate.dialect", conf.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", conf.getProperty("hibernate.show_sql"));
		properties.put("hibernate.enable_lazy_load_no_trans", conf.getProperty("hibernate.enable_lazy_load_no_trans"));
		return properties;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("fr.ecole42.swingy");
		sessionFactory.setHibernateProperties(hibernateProperties());

		return sessionFactory;
	}

	@Bean
	public PlatformTransactionManager hibernateTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}
