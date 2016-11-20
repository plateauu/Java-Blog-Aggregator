package com.plateauu.jba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.plateauu.jba.repository", entityManagerFactoryRef = "emf")
@ComponentScan(basePackages = "com.plateauu.jba", excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, EnableWebMvc.class})})
public class RootContext {


    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.HSQL)
                .build();
        return db;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        hibernateJpaVendorAdapter.setDatabase(Database.HSQL);
        return hibernateJpaVendorAdapter;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean emf(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.plateauu.jba.entity");
        emf.setJpaVendorAdapter(jpaVendorAdapter);
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
