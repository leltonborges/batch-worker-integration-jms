package org.demo.batch.worker.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
@EnableTransactionManagement
//@EnableJpaRepositories(basePackages = "org.demo.batch.worker.repository",
//                       transactionManagerRef = "transactionDemo")
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.demo")
    public DataSourceProperties dataSourceDemo(){
        return new DataSourceProperties();
    }

    @Bean("dbDemo")
    public DataSource dataSource() {
        return dataSourceDemo().initializeDataSourceBuilder().build();
    }

    @Bean("transactionDemo")
    public PlatformTransactionManager transactionManager(@Qualifier("dbDemo") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
//
//    @Bean("entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerDemo(@Qualifier("dbDemo") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean entity = new LocalContainerEntityManagerFactoryBean();
//        entity.setDataSource(dataSource);
//        entity.setPackagesToScan("org.demo.batch.worker.model");
//        entity.setPersistenceUnitName("entityManagerDemo");
//        entity.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        return entity;
//    }
}
