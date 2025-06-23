package com.example.demo.config;

import com.example.demo.pgsql.PgsqlClients;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

@Configuration(proxyBeanMethods = false)
@EnableJpaRepositories(basePackageClasses = PgsqlClients.class, entityManagerFactoryRef = "pgsqlEntityManagerFactory")
public class PgsqlDbConfig {

    @Qualifier("pgsql")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("pgsql.datasource")
    public DataSourceProperties pgsqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Qualifier("pgsql")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("pgsql.datasource.configuration")
    public HikariDataSource pgsqlDataSource(@Qualifier("pgsql") DataSourceProperties pgsqlDataSourceProperties) {
        return pgsqlDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Qualifier("pgsql")
    @Bean(defaultCandidate = false)
    @ConfigurationProperties("pgsql.jpa")
    public JpaProperties pgsqlJpaProperties() {
        return new JpaProperties();
    }

    @Qualifier("pgsql")
    @Bean(defaultCandidate = false)
    public LocalContainerEntityManagerFactoryBean pgsqlEntityManagerFactory(@Qualifier("pgsql") DataSource dataSource,
                                                                             @Qualifier("pgsql") JpaProperties jpaProperties) {
        EntityManagerFactoryBuilder builder = createEntityManagerFactoryBuilder(jpaProperties);
        return builder.dataSource(dataSource).packages(PgsqlClients.class).persistenceUnit("pgsql").build();
    }

    private EntityManagerFactoryBuilder createEntityManagerFactoryBuilder(JpaProperties jpaProperties) {
        JpaVendorAdapter jpaVendorAdapter = createJpaVendorAdapter(jpaProperties);
        Function<DataSource, Map<String, ?>> jpaPropertiesFactory = (dataSource) -> createJpaProperties(dataSource,
                jpaProperties.getProperties());
        return new EntityManagerFactoryBuilder(jpaVendorAdapter, jpaPropertiesFactory, null);
    }

    private JpaVendorAdapter createJpaVendorAdapter(JpaProperties jpaProperties) {
        // ... map JPA properties as needed
        return new HibernateJpaVendorAdapter();
    }

    private Map<String, ?> createJpaProperties(DataSource dataSource, Map<String, ?> existingProperties) {
        Map<String, ?> jpaProperties = new LinkedHashMap<>(existingProperties);
        // ... map JPA properties that require the DataSource (e.g. DDL flags)
        return jpaProperties;
    }
}
