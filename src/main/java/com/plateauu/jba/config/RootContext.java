package com.plateauu.jba.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Configuration
@Profile("prod")
@EnableTransactionManagement
@EnableScheduling
@EnableJpaRepositories(basePackages = "com.plateauu.jba.repository", entityManagerFactoryRef = "emf")
@ComponentScan(basePackages = "com.plateauu.jba", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, EnableWebMvc.class})})
public class RootContext {

    private static final Logger log = Logger.getLogger("Logger");


    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        log.info("Creating postgreSQL database");

        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }


    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setDatabase(Database.POSTGRESQL);
        return hibernateJpaVendorAdapter;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.plateauu.jba.entity");
//        emf.setJpaVendorAdapter(jpaVendorAdapter);

        Map<String, String> jpaProperties = new HashMap<>();
        jpaProperties.put("hibernate.hdm2ddl", "update");
        jpaProperties.put("hibernate.show_sql", "true");
        jpaProperties.put("hibernate.dialect", "PostgresSQL");
        emf.setJpaPropertyMap(jpaProperties);

        emf.setPersistenceProvider(new HibernatePersistenceProvider());

        return emf;

    }

    @Bean
    public JpaTransactionManager transactionManager(DataSource dataSource, EntityManagerFactory emf) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource);
        jpaTransactionManager.setEntityManagerFactory(emf);
        return jpaTransactionManager;

    }

}
