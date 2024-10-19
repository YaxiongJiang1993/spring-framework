package com.davih.mybatis;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * @author Yaxio
 */
@Configuration
@ComponentScan("com.davih.mybatis")
//@MapperScan("com.com.davih.cycle")
@TestMapperScan("com.davih.mybatis.mapper")
public class AppConfiguration {

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setJdbcTypeForNull(JdbcType.NULL);
		// Add other configuration settings here

		// Environment
		Environment environment = new Environment("development", new JdbcTransactionFactory(), dataSource());
		configuration.setEnvironment(environment);

		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		return builder.build(configuration);
		/*SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		return sqlSessionFactoryBean.getObject();*/
	}

	@Bean
	public DataSource dataSource() {
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/nacos";
		String dbUsername = "root";
		String dbPassword = "123456";
		String dbDriverClassName = "com.mysql.cj.jdbc.Driver";

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(dbDriverClassName);
		dataSource.setUrl(dbUrl);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;

	}
}
