package kr.or.bbs.BBSSpring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement // 트랜잭션과 관련된 설정을 자동으로 해줌
public class DBConfig implements TransactionManagementConfigurer {// 사용자 간의 트랜잭션 처리를 위한
	//PlatformTransactionManager를 설정하기 위해서는 TransactionManagementConfigurer 인터페이스를 구현해야 한다. 

	private String driverClassName = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/bbsprac?useUnicode=true&characterEncoding=utf8";
	private String username = "root";
	private String password = "root";
	
	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	//TransactionManagementConfigurer의 추상메서드 (반드시 구현해야한다는 의미) 
	// 사용자 간의 트랜잭션 처리를 위한 PlatformTransactionManager 를 반환하고 있음 
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManager();
	}
	
	// 만약 자바 Bean을 사용하지 않고 위 메서드에서 바로 retrun new DataSourceTransactionManager(dataSource())
	// 를 해줬다면 싱글톤으로 관리 되지 않았을 것이다. 
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
