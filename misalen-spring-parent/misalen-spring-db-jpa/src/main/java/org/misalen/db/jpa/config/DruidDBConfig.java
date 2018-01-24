package org.misalen.db.jpa.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 数据库连接池
 * 
 * @author zhaoguochao
 *
 */
@Configuration
@Component
public class DruidDBConfig {
	DataSourceProperties properties;

	DruidDBConfig(DataSourceProperties properties) {
		this.properties = properties;
	}

	@Value("${spring.datasource.initialSize:5}")
	private int initialSize;

	@Value("${spring.datasource.minIdle:5}")
	private int minIdle;

	@Value("${spring.datasource.maxActive:20}")
	private int maxActive;

	@Value("${spring.datasource.maxWait:60000}")
	private int maxWait;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis:60000}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis:300000}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery:SELECT 1 FROM DUAL}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle:true}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow:false}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn:false}")
	private boolean testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements:true}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize:20}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.filters:stat,wall,log4j2}")
	private String filters;

	@Value("{spring.datasource.connectionProperties:druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000;}")
	private String connectionProperties;

	@Bean // 声明其为Bean实例
	@Primary // 在同样的DataSource中，首先使用被标注的DataSource
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(properties.getUrl());
		datasource.setUsername(properties.getUsername());
		datasource.setPassword(properties.getPassword());
		datasource.setDriverClassName(properties.getDriverClassName());
		// configuration
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {

		}
		datasource.setConnectionProperties(connectionProperties);
		// Properties properties =new Properties();
		// properties.setProperty("remarks", "true");
		//// properties.setProperty("useInformationSchema", "true");
		// datasource.setConnectProperties(properties);
		return datasource;
	}

	/**
	 * 获取dbUrl
	 * 
	 * @return dbUrl dbUrl
	 */
	public String getDbUrl() {
		return properties.getUrl();
	}

	/**
	 * 获取username
	 * 
	 * @return username username
	 */
	public String getUsername() {
		return properties.getUsername();
	}

	/**
	 * 获取password
	 * 
	 * @return password password
	 */
	public String getPassword() {
		return properties.getPassword();
	}

	/**
	 * 获取driverClassName
	 * 
	 * @return driverClassName driverClassName
	 */
	public String getDriverClassName() {
		return properties.getDriverClassName();
	}

	/**
	 * 获取initialSize
	 * 
	 * @return initialSize initialSize
	 */
	public int getInitialSize() {
		return initialSize;
	}

	/**
	 * 设置initialSize
	 * 
	 * @param initialSize
	 *            initialSize
	 */
	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	/**
	 * 获取minIdle
	 * 
	 * @return minIdle minIdle
	 */
	public int getMinIdle() {
		return minIdle;
	}

	/**
	 * 设置minIdle
	 * 
	 * @param minIdle
	 *            minIdle
	 */
	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	/**
	 * 获取maxActive
	 * 
	 * @return maxActive maxActive
	 */
	public int getMaxActive() {
		return maxActive;
	}

	/**
	 * 设置maxActive
	 * 
	 * @param maxActive
	 *            maxActive
	 */
	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	/**
	 * 获取maxWait
	 * 
	 * @return maxWait maxWait
	 */
	public int getMaxWait() {
		return maxWait;
	}

	/**
	 * 设置maxWait
	 * 
	 * @param maxWait
	 *            maxWait
	 */
	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	/**
	 * 获取timeBetweenEvictionRunsMillis
	 * 
	 * @return timeBetweenEvictionRunsMillis timeBetweenEvictionRunsMillis
	 */
	public int getTimeBetweenEvictionRunsMillis() {
		return timeBetweenEvictionRunsMillis;
	}

	/**
	 * 设置timeBetweenEvictionRunsMillis
	 * 
	 * @param timeBetweenEvictionRunsMillis
	 *            timeBetweenEvictionRunsMillis
	 */
	public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
		this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
	}

	/**
	 * 获取minEvictableIdleTimeMillis
	 * 
	 * @return minEvictableIdleTimeMillis minEvictableIdleTimeMillis
	 */
	public int getMinEvictableIdleTimeMillis() {
		return minEvictableIdleTimeMillis;
	}

	/**
	 * 设置minEvictableIdleTimeMillis
	 * 
	 * @param minEvictableIdleTimeMillis
	 *            minEvictableIdleTimeMillis
	 */
	public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
		this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
	}

	/**
	 * 获取validationQuery
	 * 
	 * @return validationQuery validationQuery
	 */
	public String getValidationQuery() {
		return validationQuery;
	}

	/**
	 * 设置validationQuery
	 * 
	 * @param validationQuery
	 *            validationQuery
	 */
	public void setValidationQuery(String validationQuery) {
		this.validationQuery = validationQuery;
	}

	/**
	 * 获取testWhileIdle
	 * 
	 * @return testWhileIdle testWhileIdle
	 */
	public boolean isTestWhileIdle() {
		return testWhileIdle;
	}

	/**
	 * 设置testWhileIdle
	 * 
	 * @param testWhileIdle
	 *            testWhileIdle
	 */
	public void setTestWhileIdle(boolean testWhileIdle) {
		this.testWhileIdle = testWhileIdle;
	}

	/**
	 * 获取testOnBorrow
	 * 
	 * @return testOnBorrow testOnBorrow
	 */
	public boolean isTestOnBorrow() {
		return testOnBorrow;
	}

	/**
	 * 设置testOnBorrow
	 * 
	 * @param testOnBorrow
	 *            testOnBorrow
	 */
	public void setTestOnBorrow(boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	/**
	 * 获取testOnReturn
	 * 
	 * @return testOnReturn testOnReturn
	 */
	public boolean isTestOnReturn() {
		return testOnReturn;
	}

	/**
	 * 设置testOnReturn
	 * 
	 * @param testOnReturn
	 *            testOnReturn
	 */
	public void setTestOnReturn(boolean testOnReturn) {
		this.testOnReturn = testOnReturn;
	}

	/**
	 * 获取poolPreparedStatements
	 * 
	 * @return poolPreparedStatements poolPreparedStatements
	 */
	public boolean isPoolPreparedStatements() {
		return poolPreparedStatements;
	}

	/**
	 * 设置poolPreparedStatements
	 * 
	 * @param poolPreparedStatements
	 *            poolPreparedStatements
	 */
	public void setPoolPreparedStatements(boolean poolPreparedStatements) {
		this.poolPreparedStatements = poolPreparedStatements;
	}

	/**
	 * 获取maxPoolPreparedStatementPerConnectionSize
	 * 
	 * @return maxPoolPreparedStatementPerConnectionSize
	 *         maxPoolPreparedStatementPerConnectionSize
	 */
	public int getMaxPoolPreparedStatementPerConnectionSize() {
		return maxPoolPreparedStatementPerConnectionSize;
	}

	/**
	 * 设置maxPoolPreparedStatementPerConnectionSize
	 * 
	 * @param maxPoolPreparedStatementPerConnectionSize
	 *            maxPoolPreparedStatementPerConnectionSize
	 */
	public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
		this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
	}

	/**
	 * 获取filters
	 * 
	 * @return filters filters
	 */
	public String getFilters() {
		return filters;
	}

	/**
	 * 设置filters
	 * 
	 * @param filters
	 *            filters
	 */
	public void setFilters(String filters) {
		this.filters = filters;
	}

	/**
	 * 获取connectionProperties
	 * 
	 * @return connectionProperties connectionProperties
	 */
	public String getConnectionProperties() {
		return connectionProperties;
	}

	/**
	 * 设置connectionProperties
	 * 
	 * @param connectionProperties
	 *            connectionProperties
	 */
	public void setConnectionProperties(String connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

}
