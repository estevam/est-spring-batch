package ca.est.config;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author estevam.meneses
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManager", 
transactionManagerRef = "transactionManager", basePackages = "ca.est.repository*")
@PropertySource("classpath:postgres_config.properties")
public class DbConfig {

	private final String mssqlEntityManager = "entityManager";
	private final String mssqlTransactionManager = "transactionManager";

	@Value("${datasource.driver}")
	private String driver;

	@Value("${datasource.url}")
	private String url;

	@Value("${datasource.username}")
	private String username;

	@Value("${datasource.password}")
	private String password;

	@Value("${entity_scan}")
	private String entityScan;

	@Value("${hikari_cp.datasource.connectionTimeout}")
	private long connectionTimeout;

	@Value("${persistence_unit}")
	private String mssqlPersistenceUnit;

	@Value("${hibernate_properties}")
	private String mssqlHibernateProperties;

	// HikariCP
	@Value("${hikari_cp.datasource.minimumIdle}")
	private int minimumIdle;

	@Value("${hikari_cp.datasource.maxPoolSize}")
	private int maxPoolSize;

	@Value("${hikari_cp.cache_prep_stmts}")
	private String cachePrepStmts;

	@Value("${hikari_cp.prep_stmt_cache_size}")
	private String prepStmtCacheSize;

	@Value("${hikari_cp.prep_stmt_cache_sql_limit}")
	private String prepStmtCacheSqlLimit;

	@Value("${hikari_cp.use_Server_prep_stmts}")
	private String useServerPrepStmts;

	@Value("${hikari_cp.connection_init_sql}")
	private String connectionInitSql;

	@Value("${hikari_cp.cache_prep_stmts_value}")
	private String cachePrepStmtsValue;

	@Value("${hikari_cp.prep_stmt_cache_size_value}")
	private String prepStmtCacheSizeValue;

	@Value("${hikari_cp.prep_stmt_cache_sql_limit_value}")
	private String prepStmtCacheSqlLimitValue;

	@Value("${hikari_cp.use_Server_prep_stmts_value}")
	private String useServerPrepStmtsValue;

	@Value("${pool_name}")
	private String poolName;

	@Primary
	@Bean
	public DataSource mssqlDataSource() {

		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName(driver);

		hikariConfig.setJdbcUrl(url);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setPoolName(poolName);
		hikariConfig.addDataSourceProperty(cachePrepStmts, cachePrepStmtsValue);
		hikariConfig.addDataSourceProperty(prepStmtCacheSize, prepStmtCacheSizeValue);
		hikariConfig.addDataSourceProperty(prepStmtCacheSqlLimit, prepStmtCacheSqlLimitValue);
		hikariConfig.addDataSourceProperty(useServerPrepStmts, useServerPrepStmtsValue);
		hikariConfig.setConnectionTimeout(connectionTimeout);
		hikariConfig.setMinimumIdle(minimumIdle);
		hikariConfig.setMaximumPoolSize(maxPoolSize);
		hikariConfig.setConnectionInitSql(connectionInitSql);
		return new HikariDataSource(hikariConfig);
	}

	@Primary
	@Bean(name = mssqlEntityManager)
	public LocalContainerEntityManagerFactoryBean entityManager(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(mssqlDataSource()).properties(hibernateProperties()).packages(entityScan)
				.persistenceUnit(mssqlPersistenceUnit).build();
	}

	@Primary
	@Bean(name = mssqlTransactionManager)
	public PlatformTransactionManager transactionManager(
			@Qualifier(mssqlEntityManager) EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	/**
	 * Load hibernate properties
	 * @return
	 */
	private Map<String, ?> hibernateProperties() {

		Resource resource = new ClassPathResource(mssqlHibernateProperties);

		try {
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			return properties.entrySet().stream()
					.collect(Collectors.toMap(e -> e.getKey().toString(), e -> e.getValue()));
		} catch (IOException e) {
			return null;
		}
	}
}