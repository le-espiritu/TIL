# Spring JDBC

> JDBC를 이용해서 프로그래밍을 하게 되면 반복적인 코드가 많이 발생한다.
>
> 이러한 반복적인 코드는 개발자의 생산성을 떨어트리는 주된 원인이 된다.
>
> 이러한 문제를 해결하기 위해 등장한 것이 Spring JDBC다.



## Spring JDBC 소개



### Spring JDBC

+ JDBC 프로그래밍을 보면 반복되는 개발 요소가 있다.
+ 이러한 반복적인 요소는 개발자를 지루하게 만든다.
+ 개발하기 지루한 JDBC의 모든 저수준 세부사항을 스프링 프레임워크가 처리해준다.
  + 반복적으로 나오는 코드들은 프로그래머가 만들지 않고 Spring JDBC가 대신 만들어 준다.
+ 개발자는 필요한 부분만 개발하면 된다.



### Spring JDBC - 개발자가 해야 할 일은?

![2_11_2_springJDBC](https://user-images.githubusercontent.com/88477839/160748441-565d7540-cb0b-4f4c-8b59-6982028ce5ed.png)



### **Spring JDBC 패키지**

+ **org.springframework.jdbc.core**
  - JdbcTemplate 및 관련 Helper 객체 제공

+ **org.springframework.jdbc.datasource**
  - DataSource를 쉽게 접근하기 위한 유틸 클래스, 트랜젝션매니져 및 다양한 DataSource 구현을 제공

+ **org.springframework.jdbc.object**
  - RDBMS 조회, 갱신, 저장등을 안전하고 재사용 가능한 객제 제공

+ **org.springframework.jdbc.support**
  - jdbc.core 및 jdbc.object를 사용하는 JDBC 프레임워크를 지원



### JDBCTemplate

+ Org.springframework.jdbc.core 에서 가장 중요한 클래스
+ 리소스 생성, 해지를 처리해서 연결을 닫는 것을 잊어 발생하는 문제등을 피할 수 있도록 한다.
+ 스테이트먼트(Statement)의 생성과 실행을 처리한다.
+ SQL조회, 업데이트, 저장 프로시저 호출, ResultSet 반복호출 등을 실행한다.
+ JDBC예외가 발생할 경우 org.springframework.dao패키지에 정의되어 있는 일반적인 예외로 변환시킨다.



### **JdbcTemplate외의 접근방법**

+ **NamedParameterJdbcTemplate**

  - JdbcTemplate에서 JDBC statement 인자를 ?를 사용하는 대신 파라미터명을 사용하여 작성하는 것을 지원

  - [NamedParameterJdbcTemplate 예제](https://docs.spring.io/spring/docs/current/spring-framework-reference/data-access.html#jdbc-NamedParameterJdbcTemplate)

+ **SimpleJdbcTemplate**

  - JdbcTemplate과 NamedParameterJdbcTemplate 합쳐 놓은 템플릿 클래스

  - 이제 JdbcTemplate과 NamedParameterJdbcTemplate에 모든 기능을 제공하기 때문에 삭제 예정될 예정(deprecated)

  - [SimpleJdbcTemplate 예제](https://www.concretepage.com/spring/simplejdbctemplate-spring-example)

+ **SimpleJdbcInsert**

  - 테이블에 쉽게 데이터 insert 기능을 제공

  - [SimpleJdbcInsert 예제](https://www.tutorialspoint.com/springjdbc/springjdbc_simplejdbcinsert.htm)



## SPring JDBC 실습1

### Spring JDBC를 이용한 DAO개발



### DTO란?

+ DTO란 Data Transfer Object의 약자이다.
+ 계층간 데이터 교환을 위한 자바빈즈이다.
+ 여기서의 계층이란 컨트롤러 뷰, 비즈니스 계층, 퍼시스턴스 계층을 의미한다.
+ 일반적으로 DTO는 로직을 가지고 있지 않고, 순수한 데이터 객체이다.



### DTO의 예

+ 필드와 getter, setter를 가지고 있다.

+ 추가적으로 toString(),equals(),hashCode()등의 Object 메소드를 오버라이딩 할 수 있다.

+ 데이터를 들고 다닐 때 하나씩 들고 다니면 불편하기 때문에 어떤 하나의 가방처럼 만들어서 데이터들을 한꺼번에 갖고 다니는 용도로 사용한다고 생각하면 된다.

  ~~~java
  public class ActorDTO {
      private Long id;
      private String firstName;
      private String lastName;
      public String getFirstName() {
          return this.firstName;
      }
      public String getLastName() {
          return this.lastName;
      }
      public Long getId() {
          return this.id;
      }
      // ......
  }
  ~~~

  

### DAO란?

+ DAO란 Data Access Object의 약자로 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 객체이다.
+ 보통 데이터베이스를 조작하는 기능을 전담하는 목적으로 만들어진다.
+ 객체 지향은 어떤 객체가 한 가지 일만 정확하게 제대로 하는 것을 바란다.
  + 이 DAO 객체는 딱 그 일만 하도록 만들어 놓은  객체이다.



### ConnectionPool 이란?

+ DB연결은 비용이 많이 든다.

  + 데이터베이스로부터 정보를 읽고 쓰는 프로그램을 실행해보면 프로그램이 DBMS에게 접속하는 시간이 조금 오래 걸리는 것을 볼 수 있다.
  + 이렇게 시간이 오래 걸리거나 자원을 많이 소모하는 것을 비용이 많이 발생한다고 한다.
  + 이런 문제를 해결하기 위해서 DBMS와 커넥션을 미리 많이 맺어둔 객체를 사용하는 경우가 있다. 이것을 ConnectionPool이라고 한다.

+ 커넥션 풀은 미리 커넥션을 여러 개 맺어 둔다.

+ 커넥션이 필요하면 커넥션 풀에게 빌려서 사용한 후 반납한다.

+ 커넥션을 반납하지 않으면 어떻게 될까?

  + 커넥션 풀에서 사용가능한 커넥션이 없어서 프로그램이 늦어지거나 심한 경우에는 장애를 발생시킬 수도 있다.

  ![3_8_2_ConnectionPool](https://user-images.githubusercontent.com/88477839/160751811-5289dc62-6415-49cd-a766-c8cf2c24bdd4.jpeg)

  



### DataSource란?

+ DataSource는 커넥션 풀을 관리하는 목적으로 사용되는 객체이다.
  + 커넥션 풀은 경우에 따라서 여러 개가 생성될 수 있다.
+ DataSource를 이용해 커넥션을 얻어오고 반납하는 등의 작업을 수행한다.



### Spring JDBC를 이용한 DAO작성 실습

![3_8_2_Spring_JDBC__DAO_](https://user-images.githubusercontent.com/88477839/160966336-f8634e16-3826-4d6c-84d0-f73e86bb01f9.png)



## Spring JDBC 실습2



### Spring JDBC를 사용하기 위한 준비

+ 메이븐 프로젝트 생성 (퀵스타트 1.1로)

+ 

  ~~~xml
  <build>
       <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-compiler-plugin</artifactId>
              <version>3.6.1</version>
              <configuration>
                  <source>1.8</source>
                  <target>1.8</target>
              </configuration>
          </plugin>
      </plugins>
    </build>
  ~~~

  + Pom.xml에 jdk를 사용하기 위한 플러그인 설정을 추가한다.

+ ~~~xml
  <!-- Spring -->
  	<dependency>
  		<groupId>org.springframework</groupId>
  		<artifactId>spring-context</artifactId>
  		<version>${spring.version}</version> 
  	</dependency>
  ~~~

  + Spring-context 추가

+ ~~~xml
  <dependency>
  	<groupId>org.springframework</groupId>
  	<artifactId>spring-jdbc</artifactId>
  	<version>${spring.version}</version>
  </dependency>
  
  <dependency>
  	<groupId>org.springframework</groupId>
  	<artifactId>spring-tx</artifactId>
  	<version>${spring.version}</version>
  </dependency>
  ~~~

  + spring-jdbc와 spring-tx 부분을 라이브러리로 추가

+  ~~~xml
   <spring.version>4.3.5.RELEASE</spring.version>
   ~~~

  + 프로퍼티즈에 스프링 버전 추가

+ ~~~xml
  <!-- mysql 버전 8.x.x일 경우  -->
  
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.28</version>
  </dependency>
  ~~~

  + mysql에서 제공하는 드라이버 추가

+ ~~~xml
  <!-- basic data source -->
  <dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-dbcp2</artifactId>
    <version>2.1.1</version>
  </dependency>
  ~~~

  + DataSource 설치
  + 여기서 사용하는 DataSource는 Apache에서 제공하는 commons-dbcp2이다.

+ 라이브러리들을 추가했으면 저장을 눌러주고 프로젝트 우클릭 - 메이븐 - 업데이트프로젝트를 반드시 눌러준다.



### ApplicationConfig

+ Config 설정파일만 따로 보관하기 위해 kr.or.connect.daoexam.config 패키지를 만든다.

+ ~~~java
  // ApplicationConfig.java
  
  package kr.or.connect.daoexam.config;
  
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.Import;
  
  @Configuration
  @Import({DBConfig.class})
  public class ApplicationConfig {
  
  }
  ~~~

  + ApplicationConfig 파일을 만든다.
  + Import 어노테이션 
    + 설정 파일을 여러개로 나눠서 작성할 수 있다.
    + 예를 들어 이 설정 파일 하나에다가 모든 설정을 다 넣는게 아니라 데이터베이스 연결에 관련된 설정은 따로 빼주고 싶은 것이다.
    + 하나의 클래스가 모든 정보를 갖고 있으면 나중에 유지 보수하기가 힘들다.
    + 위 코드는(@Import({DBConfig.class})) DBConfig라는 파일에다가 DB 관련된 설정을 따로 작성하겠다는 의미

+ DBconfig 작성

  ~~~java
  // DBConfig.java
  
  package kr.or.connect.daoexam.config;
  
  import javax.sql.DataSource;
  
  import org.apache.commons.dbcp2.BasicDataSource;
  import org.springframework.context.annotation.Bean;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.transaction.annotation.EnableTransactionManagement;
  
  @Configuration
  @EnableTransactionManagement // 트랜잭션때문에 필요한 어노테이션 
  public class DBConfig {
  	private String driverClassName = "com.mysql.cj.jdbc.Driver";
  	private String url = "jdbc:mysql://localhost:3306/connectdb?useUnicode=true&characterEncoding=utf8";
  
  	private String username = "connectuser";
  	private String password = "connect123!@#";
  	
  	@Bean
  	public DataSource dataSource() {
  		BasicDataSource dataSource = new BasicDataSource();
  		dataSource.setDriverClassName(driverClassName);
  		dataSource.setUrl(url);
  		dataSource.setUsername(username);
  		dataSource.setPassword(password);
  		return dataSource;
  	}
  }
  ~~~

  + DataSource 객체는 우리가 작성할 객체가 아니고 이미 작성되어 있는 객체를 사용할 것임



### DataSourceTest.java 파일

+ 실행파일만 따로 보관하기 위해 kr.or.connect.daoexam.main 패키지를 만든다.

+ ~~~java
  package kr.or.connect.daoexam.main;
  
  import java.sql.Connection;
  
  import javax.sql.DataSource;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.AnnotationConfigApplicationContext;
  
  import kr.or.connect.daoexam.config.ApplicationConfig;
  
  public class DataSourceTest {
  
  	public static void main(String[] args) {
  		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
  		DataSource ds = ac.getBean(DataSource.class);
  		Connection conn = null;
  		try {
  			conn = ds.getConnection();
  			if(conn != null)
  				System.out.println("접속 성공");
  		}catch (Exception e) {
  			e.printStackTrace();
  		}finally {
  			if(conn != null) {
  				try {
  					conn.close();
  				}catch(Exception e) {
  					e.printStackTrace();
  				}
  			}
  		}
  		
  	}
  
  }
  ~~~
  
  + 접속을 확인하는 테스트코드임.
  
    

## Spring JDBC 실습 3

### DTO 생성

+ kr.or.connect.daoexam.dto 패키지 생성

+ Role.java(dto) 생성

  ~~~java
  package kr.or.connect.daoexam.dto;
  
  public class Role {
  	private int roleId;
  	private String description;
  	
  	public int getRoleId() {
  		return roleId;
  	}
  	public void setRoleId(int roleId) {
  		this.roleId = roleId;
  	}
  	public String getDescription() {
  		return description;
  	}
  	public void setDescription(String description) {
  		this.description = description;
  	}
  	@Override
  	public String toString() {
  		return "Role [roleId=" + roleId + ", description=" + description + "]";
  	}
  
  
  }
  ~~~

  

### DAO 생성

+ kr.or.connect.daoexam.dao 패키지 생성

+ sql문을 담고 있는 RoleDaoSqls라는 클래스 생성

  ~~~java
  package kr.or.connect.daoexam.dao;
  
  public class RoleDaoSqls {
  	// 상수는 항상 모든 글자가 대문자로 쓰는 것이 관례다.
    // 모두 대문자이기 때문에 두단어 이상 필요한 경우 _(언더바)로 구분한다.
  	public static final String SELECT_ALL = "SELECT role_id, description FROM role order by role_id";
  }
  
  ~~~

+ 데이터를 액세스 할 수 있는 DAO 오브젝트 생성  

  ~~~java
  package kr.or.connect.daoexam.dao;
  
  import java.util.Collections;
  import java.util.List;
  
  import javax.sql.DataSource;
  
  import org.springframework.jdbc.core.BeanPropertyRowMapper;
  import org.springframework.jdbc.core.RowMapper;
  import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
  import org.springframework.stereotype.Repository;
  
  import kr.or.connect.daoexam.dto.Role;
  
  //static import를 사용하면 RoleDaoSqls 객체에 선언 변수를 클래스 이름 없이 그냥 바로 가져다가 사용할 수 있도록 해준다. 
  import static kr.or.connect.daoexam.dao.RoleDaoSqls.*;
  
  @Repository //저장소의 역할을 한다는 의미 
  public class RoleDao {
  	private NamedParameterJdbcTemplate jdbc;
  	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class) ;
  	
  	//생성자 
  	public RoleDao(DataSource dataSource) { // DataSource는 DBconfig에서 @Bean으로 등록
  		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  	}
  	
  	public List<Role> selecctAll(){
  		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
  	}
  }
  ~~~

  + Collections.*emptyMap*() : 비어있는 맵객체
    + sql 문에 바인딩 할 값이 있을 경우에 바인딩 할 값을 전달할 목적으로 사용하는 객체 
  + RowMapper :  select 한건, 한건의 결과를 DTO에 저장하는 목적으로 사용 
  + query()메서드는 결과가 여러 건이었을 때 내부적으로 반복하면서 DTO 생성
  + DBMS에서는 column 명이 단어와 단어를 구분할 때 '_' 를 사용했었다. (대소문자 구별이 없어서)
  + java에서는 camel 표기법을 이용해서 단어와 단어가 만날 때 대문자를 사용하게 된다.(대소문자 구별을 하기 때문에) 
  + BeanPropertyRowMapper는 DBMS와 Java의 이름 규칙을 맞춰주는 기능을 가지고 있다. 



### ApplicationConfig.java 에 추가

+ ~~~java
  @ComponentScan(basePackages = { "kr.or.connect.daoexam.dao" })
  ~~~

+ ~~~java
  package kr.or.connect.daoexam.config;
  
  import org.springframework.context.annotation.ComponentScan;
  import org.springframework.context.annotation.Configuration;
  import org.springframework.context.annotation.Import;
  
  @Configuration
  @ComponentScan(basePackages = { "kr.or.connect.daoexam.dao" })
  @Import({DBConfig.class})
  public class ApplicationConfig {
  
  }
  ~~~

  + 패키지를 지정할때 'basePackages =' 를 이용하면 패키지를 여러개 나열해서 사용할 수 있다.



### 테스트 코드 작성

+ kr.or.connect.daoexam.main 패키지에 테스트할 수 있는 클래스를 만든다.

+ ~~~java
  package kr.or.connect.daoexam.main;
  
  import java.util.List;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.AnnotationConfigApplicationContext;
  
  import kr.or.connect.daoexam.config.ApplicationConfig;
  import kr.or.connect.daoexam.dao.RoleDao;
  import kr.or.connect.daoexam.dto.Role;
  
  public class SelectAllTest {
  	public static void main(String[] args) {
  		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
  		
  		RoleDao roleDao = ac.getBean(RoleDao.class);
  		
  		List<Role> list = roleDao.selecctAll();
  		
  		for(Role role : list) {
  			System.out.println(role);
  		}
  		
  	}
  
  }
  
  ~~~
  
  ~~~
  < 실행결과>
  
  Role [roleId=100, description=Developer]
  Role [roleId=101, description=Researcher]
  Role [roleId=102, description=Project manager]
  Role [roleId=501, description=PROGRAMMER]
  
  ~~~
  
  




## Spring JDBC 실습 4



### insert문

+ insert문을 실행하기 위해서는 SimpleJdbcInsert라는 객체가 필요하다.

  ~~~java
  // RoleDao.java
  
  import java.util.Collections;
  import java.util.List;
  
  import javax.sql.DataSource;
  
  import org.springframework.jdbc.core.BeanPropertyRowMapper;
  import org.springframework.jdbc.core.RowMapper;
  import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
  import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
  import org.springframework.jdbc.core.namedparam.SqlParameterSource;
  import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
  import org.springframework.stereotype.Repository;
  
  import kr.or.connect.daoexam.dto.Role;
  
  //static import를 사용하면 RoleDaoSqls 객체에 선언 변수를 클래스 이름 없이 그냥 바로 가져다가 사용할 수 있도록 해준다. 
  import static kr.or.connect.daoexam.dao.RoleDaoSqls.*;
  
  @Repository //저장소의 역할을 한다는 의미 
  public class RoleDao {
  	private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction;
  	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class) ;
  	
  	//생성자 
  	public RoleDao(DataSource dataSource) {
  		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("role");
  	}
  	
  	public List<Role> selecctAll(){
  		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
  	}
    
    public int insert(Role role) {
  		SqlParameterSource params = new BeanPropertySqlParameterSource(role); // role 객체 있는 값을 맵으로 바꿔줌 이때 roleId가 role_id로 자동으로 변환
  		return insertAction.execute(params);
  	}
    
  }
  ~~~

  + 필드에 **private** SimpleJdbcInsert insertAction; 추가
  + 생성자에 this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("role"); 코드 추가됨
    + .withTableName("role") - 어떤 테이블에 insert할 것인지 명시하는 것.




+ 위 코드를 실행하는 JDBCTest.java 클래스 생성

  ~~~java
  package kr.or.connect.daoexam.main;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.AnnotationConfigApplicationContext;
  
  import kr.or.connect.daoexam.config.ApplicationConfig;
  import kr.or.connect.daoexam.dao.RoleDao;
  import kr.or.connect.daoexam.dto.Role;
  
  public class JDBCTest {
  
  	public static void main(String[] args) {
  		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
  		
  		RoleDao roleDao = ac.getBean(RoleDao.class);
  		
  		Role role = new Role();
  		role.setRoleId(501);
  		role.setDescription("CEO");
  		
  		int count = roleDao.insert(role);
  		System.out.println(count + " 건 입력하였습니다.");
  	}
  
  }
  ~~~

  

### Update문

+ update문은 query문이 필요하다.

  ~~~java
  package kr.or.connect.daoexam.dao;
  
  public class RoleDaoSqls {
  	// 상수는 항상 모든 글자가 대문자로 쓰는 것이 관례다.
  	public static final String SELECT_ALL = "SELECT role_id, description FROM role order by role_id";
  	public static final String UPDATE = "UPDATE role set description = :description where role_id = :roleId";
  }
  ~~~

  + :description , :roleId - 나중에 값으로 바인딩 될 부분.

+ RoleDao.java에 update 메서드 추가

  ~~~java
  public int update(Role role) {
  		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
  		return jdbc.update(UPDATE, params);
  	}
  ~~~

  ~~~java
  package kr.or.connect.daoexam.dao;
  
  import java.util.Collections;
  import java.util.List;
  
  import javax.sql.DataSource;
  
  import org.springframework.jdbc.core.BeanPropertyRowMapper;
  import org.springframework.jdbc.core.RowMapper;
  import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
  import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
  import org.springframework.jdbc.core.namedparam.SqlParameterSource;
  import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
  import org.springframework.stereotype.Repository;
  
  import kr.or.connect.daoexam.dto.Role;
  
  //static import를 사용하면 RoleDaoSqls 객체에 선언 변수를 클래스 이름 없이 그냥 바로 가져다가 사용할 수 있도록 해준다. 
  import static kr.or.connect.daoexam.dao.RoleDaoSqls.*;
  
  @Repository //저장소의 역할을 한다는 의미 
  public class RoleDao {
  	private NamedParameterJdbcTemplate jdbc;
  	private SimpleJdbcInsert insertAction;
  	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class) ;
  	
  	//생성자 
  	public RoleDao(DataSource dataSource) {
  		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("role");
  	}
  	
  	public List<Role> selecctAll(){
  		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
  	}
  	
  	public int insert(Role role) {
  		SqlParameterSource params = new BeanPropertySqlParameterSource(role); // role 객체 있는 값을 맵으로 바꿔줌 이때 roleId가 role_id로 자동으로 변환
  		return insertAction.execute(params);
  	}
  	
  	public int update(Role role) {
  		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
  		return jdbc.update(UPDATE, params);
  	}
  	
  }
  ~~~

  + return jdbc.update(UPDATE, params)
    + 두번째 파라메터는 맵객체이다.
    + sql에 값을 채워야하는 부분(:description , :roleId)의 값을 매핑 시켜줄 값을 가진 객체이다.
    + SqlParameterSource 객체가 맵으로 바꿔주는 일을 수행하게 된다.



+ 위 코드를 실행하는 코드 작성

  ~~~java
  // JDBCTest.java
  
  package kr.or.connect.daoexam.main;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.AnnotationConfigApplicationContext;
  
  import kr.or.connect.daoexam.config.ApplicationConfig;
  import kr.or.connect.daoexam.dao.RoleDao;
  import kr.or.connect.daoexam.dto.Role;
  
  public class JDBCTest {
  
  	public static void main(String[] args) {
  		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
  		
  		RoleDao roleDao = ac.getBean(RoleDao.class);
  		
  		Role role = new Role();
  		role.setRoleId(501);
  		role.setDescription("PROGRAMMER");
  		
  		// 아래 부분 또 실행하면 오류가 발생할 것이기 때문에 주석처리 
  //		int count = roleDao.insert(role);
  //		System.out.println(count + " 건 입력하였습니다.");
  		
  		int count = roleDao.update(role);
  		System.out.println(count + " 건 수정하였습니다.");
  	}
  
  }
  ~~~

  

## Spring JDBC 실습 5

### Delete 문

+ RoleDaoSqls 에 query 추가하기

  ~~~java
  package kr.or.connect.daoexam.dao;
  
  public class RoleDaoSqls {
  	// 상수는 항상 모든 글자가 대문자로 쓰는 것이 관례다.
  	public static final String SELECT_ALL = "SELECT role_id, description FROM role order by role_id";
  	public static final String UPDATE = "UPDATE role set description = :description where role_id = :roleId";
  	
  	public static final String DELETE_BY_ROLE_Id = "DELETE FROM role WHERE role_id = :roleId";
  	
  }
  ~~~

+ RoleDao.java에 실행코드 추가

  ~~~java
  public int deleteById(Integer id) {
  		Map<String, ?> params = Collections.singletonMap("roleId", id);
  		return jdbc.update(DELETE_BY_ROLE_Id, params);
  }
  ~~~

  ~~~java
  package kr.or.connect.daoexam.dao;
  
  import java.util.Collections;
  import java.util.List;
  import java.util.Map;
  
  import javax.sql.DataSource;
  
  import org.springframework.jdbc.core.BeanPropertyRowMapper;
  import org.springframework.jdbc.core.RowMapper;
  import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
  import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
  import org.springframework.jdbc.core.namedparam.SqlParameterSource;
  import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
  import org.springframework.stereotype.Repository;
  
  import kr.or.connect.daoexam.dto.Role;
  
  //static import를 사용하면 RoleDaoSqls 객체에 선언 변수를 클래스 이름 없이 그냥 바로 가져다가 사용할 수 있도록 해준다. 
  import static kr.or.connect.daoexam.dao.RoleDaoSqls.*;
  
  @Repository //저장소의 역할을 한다는 의미 
  public class RoleDao {
  	private NamedParameterJdbcTemplate jdbc;
  	private SimpleJdbcInsert insertAction;
  	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class) ;
  	
  	//생성자 
  	public RoleDao(DataSource dataSource) {
  		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("role");
  	}
  	
  	public List<Role> selecctAll(){
  		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
  	}
  	
  	public int insert(Role role) {
  		SqlParameterSource params = new BeanPropertySqlParameterSource(role); // role 객체 있는 값을 맵으로 바꿔줌 이때 roleId가 role_id로 자동으로 변환
  		return insertAction.execute(params);
  	}
  	
  	public int update(Role role) {
  		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
  		return jdbc.update(UPDATE, params);
  	}
  	
  	public int deleteById(Integer id) {
  		Map<String, ?> params = Collections.singletonMap("roleId", id);
  		return jdbc.update(DELETE_BY_ROLE_Id, params);
  	}
  	
  }
  ~~~

  + delete같은 경우에는 값이 딱 하나만("roleId") 들어온다.
    + 이 경우 굳이 객체를 만들어서(sqlParameterSource) 사용하는것이 조금 복잡하고 불편할 수도 있다.
    + Collections.singletonMap()은 값이 여러개 들어가지 않고 딱 1건만 넣어서 사용할때 사용할 수 있다.



### 1건 select 해오기

+ RoleDaoSqls 에 query 추가하기

  ~~~java
  package kr.or.connect.daoexam.dao;
  
  public class RoleDaoSqls {
  	// 상수는 항상 모든 글자가 대문자로 쓰는 것이 관례다.
  	public static final String SELECT_ALL = "SELECT role_id, description FROM role order by role_id";
  	public static final String UPDATE = "UPDATE role set description = :description where role_id = :roleId";
  	public static final String SELECT_BY_ROLE_ID = "SELECT role_id, description FROM role where role_id = :roleId";
  	
  	public static final String DELETE_BY_ROLE_Id = "DELETE FROM role WHERE role_id = :roleId";
  	
  }
  ~~~

+ RoleDao.java에 실행코드 추가

  ~~~java
  public Role selectById(Integer id) {
  		try {
  			Map<String, ?> params = Collections.singletonMap("roleId", id);
  			return jdbc.queryForObject(SELECT_BY_ROLE_ID, params, rowMapper);
  		}catch(EmptyResultDataAccessException e) {
  			return null;
  		}
  	}
  ~~~

  ~~~java
  package kr.or.connect.daoexam.dao;
  
  import java.util.Collections;
  import java.util.List;
  import java.util.Map;
  
  import javax.sql.DataSource;
  
  import org.springframework.dao.EmptyResultDataAccessException;
  import org.springframework.jdbc.core.BeanPropertyRowMapper;
  import org.springframework.jdbc.core.RowMapper;
  import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
  import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
  import org.springframework.jdbc.core.namedparam.SqlParameterSource;
  import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
  import org.springframework.stereotype.Repository;
  
  import kr.or.connect.daoexam.dto.Role;
  
  //static import를 사용하면 RoleDaoSqls 객체에 선언 변수를 클래스 이름 없이 그냥 바로 가져다가 사용할 수 있도록 해준다. 
  import static kr.or.connect.daoexam.dao.RoleDaoSqls.*;
  
  @Repository //저장소의 역할을 한다는 의미 
  public class RoleDao {
  	private NamedParameterJdbcTemplate jdbc;
  	private SimpleJdbcInsert insertAction;
  	private RowMapper<Role> rowMapper = BeanPropertyRowMapper.newInstance(Role.class) ;
  	
  	//생성자 
  	public RoleDao(DataSource dataSource) {
  		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("role");
  	}
  	
  	public List<Role> selecctAll(){
  		return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
  	}
  	
  	public int insert(Role role) {
  		SqlParameterSource params = new BeanPropertySqlParameterSource(role); // role 객체 있는 값을 맵으로 바꿔줌 이때 roleId가 role_id로 자동으로 변환
  		return insertAction.execute(params);
  	}
  	
  	public int update(Role role) {
  		SqlParameterSource params = new BeanPropertySqlParameterSource(role);
  		return jdbc.update(UPDATE, params);
  	}
  	
  	public int deleteById(Integer id) {
  		Map<String, ?> params = Collections.singletonMap("roleId", id);
  		return jdbc.update(DELETE_BY_ROLE_Id, params);
  	}
  	
  	public Role selectById(Integer id) {
  		try {
  			Map<String, ?> params = Collections.singletonMap("roleId", id);
  			return jdbc.queryForObject(SELECT_BY_ROLE_ID, params, rowMapper);
  		}catch(EmptyResultDataAccessException e) {
  			return null;
  		}
  	}
  	
  }
  ~~~

  + 1건 셀렉트 할때는 queryForObject라는 메서드를 이용한다.



### 위 코드들을 테스트 하는 테스트 코드 작성

+ JDBCTest.java에 추가

  ~~~java
  package kr.or.connect.daoexam.main;
  
  import org.springframework.context.ApplicationContext;
  import org.springframework.context.annotation.AnnotationConfigApplicationContext;
  
  import kr.or.connect.daoexam.config.ApplicationConfig;
  import kr.or.connect.daoexam.dao.RoleDao;
  import kr.or.connect.daoexam.dto.Role;
  
  public class JDBCTest {
  
  	public static void main(String[] args) {
  		ApplicationContext ac = new AnnotationConfigApplicationContext(ApplicationConfig.class);
  		
  		RoleDao roleDao = ac.getBean(RoleDao.class);
  		
  		Role role = new Role();
  		role.setRoleId(501);
  		role.setDescription("PROGRAMMER");
  		
  		// 아래 부분 또 실행하면 오류가 발생할 것이기 때문에 주석처리 
  //		int count = roleDao.insert(role);
  //		System.out.println(count + " 건 입력하였습니다.");
  		
  		int count = roleDao.update(role);
  		System.out.println(count + " 건 수정하였습니다.");
  		
  		Role resultRole = roleDao.selectById(501);
  		System.out.println(resultRole);
  		
  		int deleteCount = roleDao.deleteById(500);
  		System.out.println(deleteCount + " 건 삭제하였습니다.");
  		
  		Role resultRole2 = roleDao.selectById(500);
  		System.out.println(resultRole2);
  	}
  
  }
  ~~~

  

