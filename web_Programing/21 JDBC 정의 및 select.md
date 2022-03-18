# JDBC

> JDBC는 JAVA언어를 이용해서 DBMS로 부터 정보를 조회하는 방법이다.



### JDBC(Java Database Connectivity)의 정의

+ 자바를 이용한 데이터베이스 접속과 SQL 문장의 실행, 그리고 실행 결과로 얻어진 데이터의 핸들링을 제공하는 방법과 절차에 관한 규악
+ 자바 프로그램내에서 SQL문을 실행하기 위한 자바 API
+ SQL과 프로그래밍 언어의 통합 접근 중 한 형태
+ JAVA는 표준 인터페이스인 JDBC API를 제공
+ 데이터베이스 벤더, 또는 기타 써드파티에서는 JDBC 인터페이스를 구현한 드라이버(driver)를 제공한다.



### 환경 구성

+ JDK 설치

+ JDBC 드라이버 설치

  + Maven에 다음과 같은 의존성을 추가한다. MySQL사이트에서 다운로드 한다.

  ~~~xml
  <dependency>   
    <groupId>mysql</groupId>   
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.45</version>
  </dependency>
  ~~~
  
  ~~~xml
  <!-- mysql 버전 8.x.x일 경우  -->
  
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.28</version>
  </dependency>
  ~~~
  
  

### 참고

+ Java API Reference
  + Https://docs.oracle.com/javase/8/docs/api/
+ JDBC Tutorial
  + Https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html



### JDBC 사용 - 단계별 정리

+ JDBC를 이용한 프로그래밍 방법
  + 1단계 : import java.sql.*;
  + 2단계 : 드라이버를 로드 한다.
  + 3단계 : Connection 객체를 생성한다. 
    + DB에 접속하는 단계
    + Connection 객체는 DB가 접속됐을 때 얻어내줄 수 있는 객체
  + 4단계 : Statement 객체를 생성 및 질의 수행
    + 쿼리문을 생성함
    + 쿼리문을 실행함
  + 5단계 : SQL문에 결과물이 있다면 ResultSet 객체를 생성한다.
    + select는 조회하는 쿼리문이기 때문에 결과물이 있다.
    + 나머지 insert, update, delete는 결과가 '잘 수행되었다.'이런식이기 때문에 쿼리문 종류에 따라 5단계 부분이 좀 다르게 실행됨
  + 6단계 : 모든 객체를 닫는다.(열었던 반대 순서로)
    + 접속하는 클라이언트가 한정되어있는데, 만약 연결을 끊어주지 않으면 연결할수 있는 최대치의 클라이언트수와 연결이 계속 지속됨. 그렇기 때문에 다 사용하고 난 후에는  연결을 닫아줘야 함.



### JDBC 클래스의 생성관계

![2_11_1_JDBC_](https://user-images.githubusercontent.com/88477839/158720916-4aa6a2ec-22bb-445f-a18e-7a60507f20b1.png)

### JDBC 사용 - 단계별 설명

+ **Import**

  ~~~java
  import java.sql.*;
  ~~~

  + 자바 코드에서 나랑 다른 패키지에 있는 클래스를 사용하기 위해서는 반드시 import 해야한다.

    

+ **드라이버 로드 (각각 DB벤더에서 제공하는 객체)**

  ~~~java
  Class.forName("com.mysql.jdbc.Driver");
  ~~~

  ~~~java
  Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 버전 8.x.x일 경우 
  ~~~
  
  + Class라는 클래스가 갖고 있는 forName이라는 메서드를 이용하면 com.mysql.jdbc.Driver <- 이 객체가 메모리에 올라간다.
  + Com.mysql.jdbc(패키지명).Driver(클래스명) 
    + 각각 DB벤더에서 제공하는 객체
    + 여기서는 MySQL이라는 DB 벤더가 제공하는 객체



+ **Connection 얻기**

  ~~~java
  String dburl = "jdbc:mysql://localhost/dbName";
  Connection con = DriverManager.getConnection(dburl,ID,PWD);
  ~~~

  + Connection 객체를 얻어낼 때 사용하는 객체가 DriverManager 객체다.
  + dburl - db가 있는 ip주소(데이터베이스가 있는 URL)
  + Id - user명
  + Pwd - 패스워드



+ 소스코드 예제 - 오라클 방식 (오라클이라는 DB 벤더)

  ~~~java
  public static Connection getConnection()throws Exception{
    String url = "jdbc:oracle:thin:@117.16.46.111:1521:xe";
    String user = "smu";
    String password = "smu";
    Connection conn = null;
    Class.forName("oracle.jdbc.driver.OracleDriver");
    conn = DriverManager.getConnection(url,user,password);
    return conn;
  }
  ~~~

  

+ **Statement 생성** 

  ~~~java
  Statement stmt = con.creaateStatement();
  
  // con은 Connection con = DriverManager.getConnection(dburl,ID,PWD); 의 con임
  ~~~

  + select문을 사용한다던가 Insert문을 사용한다던가 쿼리를 사용하기 위해서는 반드시  Statement라는 객체를 얻어내야 한다.



+ **질의 수행**

  ~~~java
  ResultSet rs = stmt.executeQuery("select no from user");
  ~~~

  + Statement 객체를 생성했으면 이 Statement 객체에 쿼리문을 작성한다.

  + executeQuery() - 쿼리를 실행해주세요 라는 의미의 메서드임
    + 이 메서드는 쿼리의 종류에 따라 조금씩 달라짐
    + execute("query"); - any SQL
    + executeQuery("query"); - select
    + executeUpdate("query"); - Insert,update,delete



+ **ResultSet으로 결과 받기**

  ~~~java
  ResultSet rs = stmt.executeQuery("select no form user");
  while(rs.next())
    	System.out.println(rs.getInt("no")); //컬럼명이 no인 애 값을 꺼내와라
  ~~~

  + 현재 ResultSet은 데티어베이스 쪽에 있다.

    + rs는 데이터베이스 ResultSet의 레퍼런스를 얻어온 것이다.
    + 결과 셋은 데이터베이스가 갖고 있고 이 결과 셋을 가리킬 수 있는 레퍼런스 변수를 가져온것이다. (우리 프로그램이 클라이언트)
    + 예를 들어 select 쿼리를 수행했는데 실행 결과가 만건이다. 이 만건의 데이터를 우리 서버에 갖고 들어오기란 매우 힘듦
    + 그래서 이렇게 결과를 얻어왔으면 rs(ResultSet)으로 부터 하나씩 꺼내옴

  + rs.next() - ResultSet으로부터 하나씩 꺼내옴

    + 결과 셋이 여러개의 레코드가 존재했다면 next() 메서드를 통해 한줄 한줄 레코드를 확인한다. (결과 값이 있다면 )
    + 그래서 결과 값이 있다면 rs.next()는 true를 반환하고 while문이 계속 실행된다.
    + 만약 레코드 마지막에 와서 더 이상 레코드가 없다면 rs.next()는 false를 반환해서 while문을 종료할 것이다.

    

+ **Close**

  ~~~java
  rs.close();
  stmt.close();
  con.close();
  ~~~



+ 소스코드 예제

  ~~~java
  public List<GuestBookVO> getGuestBookList(){
  		List<GuestBookVO> list = new ArrayList<>();
  		GuestBookVO vo = null;
  		Connection conn = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		try{
  			conn = DBUtil.getConnection();
  			String sql = "select * from guestbook";
  			ps = conn.prepareStatement(sql);
  			rs = ps.executeQuery();
  			while(rs.next()){
  				vo = new GuestBookVO();
  				vo.setNo(rs.getInt(1));
  				vo.setId(rs.getString(2));
  				vo.setTitle(rs.getString(3));
  				vo.setConetnt(rs.getString(4));
  				vo.setRegDate(rs.getString(5));
  				list.add(vo);
  			}
  		}catch(Exception e){
  			e.printStackTrace();
  		}finally {
  			DBUtil.close(conn, ps, rs);
  		}		
  		return list;		
  	}
  
  ~~~

  ~~~
  public int addGuestBook(GuestBookVO vo){
  		int result = 0;
  		Connection conn = null;
  		PreparedStatement ps = null;
  		try{
  			conn = DBUtil.getConnection();
  			String sql = "insert into guestbook values("
  					+ "guestbook_seq.nextval,?,?,?,sysdate)";
  			ps = conn.prepareStatement(sql);
  			ps.setString(1, vo.getId());
  			ps.setString(2, vo.getTitle());
  			ps.setString(3, vo.getConetnt());
  			result = ps.executeUpdate();
  		}catch(Exception e){
  			e.printStackTrace();
  		}finally {
  			DBUtil.close(conn, ps);
  		}
  		
  		return result;
  	}
  ~~~

  ~~~java
  public static void close(Connection conn, PreparedStatement ps){
  		if (ps != null) {
  			try {
  				ps.close();
  			} catch (SQLException e) {e.printStackTrace(); }
  		}
  		if (conn != null) {
  			try {
  				conn.close();
  			} catch (SQLException e) {e.printStackTrace();}
  		}
  	}
  ~~~

  

## JDBC 실습



### 메이븐 프로젝트 생성

<img width="616" alt="스크린샷 2022-03-17 13 41 38" src="https://user-images.githubusercontent.com/88477839/158738737-7c34176b-7248-43fe-8079-6ccfbc9a5016.png">

+ QuickStart 로 만든다.

<img width="909" alt="스크린샷 2022-03-17 13 43 10" src="https://user-images.githubusercontent.com/88477839/158738817-9e363878-6c50-4960-8ff4-c17b55e298b0.png">

+ Artifact Id는 jdbcexam으로 한다.

<img width="974" alt="스크린샷 2022-03-17 13 48 54" src="https://user-images.githubusercontent.com/88477839/158739330-25cbc351-78c2-44d1-861c-aaadc39b0867.png">

+ Pom.xml에 jdk 1.8버전 사용하기 위해 plugins를 추가한다.

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

<img width="1047" alt="스크린샷 2022-03-17 13 54 12" src="https://user-images.githubusercontent.com/88477839/158739833-c874aaf1-6065-440c-8ebc-02aec7e50f39.png">

+ pom.xml dependencies에 jdbc dependency를 추가한다.

  ~~~xml
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.28</version>
  </dependency>
  ~~~

  + mySQL 버전 8.x.x 일 경우,

    + 아래 링크에서 현재 쓰는 mysql 버전을 클릭하여 dependency를 복사하여 붙여넣기

      https://mvnrepository.com/artifact/mysql/mysql-connector-java

+ 저장을 누른다.



<img width="954" alt="스크린샷 2022-03-17 14 41 37" src="https://user-images.githubusercontent.com/88477839/158744712-8bf899c2-871d-41ee-95a6-bf9e25d2c7f4.png">



+ 프로젝트 우클릭 -> Maven -> Update Project를 반드시 누른다.



### 데이터 정보를 담을 수 있는 객체 생성 

<img width="690" alt="스크린샷 2022-03-17 14 50 33" src="https://user-images.githubusercontent.com/88477839/158745677-d7b201c7-97b0-42c4-9799-eddd3747ebf0.png">



<img width="598" alt="스크린샷 2022-03-17 14 51 43" src="https://user-images.githubusercontent.com/88477839/158745755-ec0fb6b4-c5ed-4f83-a76b-145145b7f532.png">

+ 패키지명에 .dto를 추가해준다.



~~~java
// Role.java


package kr.or.connect.jdbcexam.dto;

public class Role {
	// 자바에서 필드명은 항상 소문자로 시작하고 단어가 두 개 이상 연걸될 때는 두번째 단어의 첫 글자를 대문자로 쓴다.(카멜 표기법)
	private Integer roleId; // role 테이블에서 rolId가 int type이었기 때문에 변수를 Integer로 선언
	private String description; // role 테이블에서 description이 varchar type 이었기 때문에 변수를 String으로 선언 
	
	public Role() {
		
	}
	
	public Role(Integer roleId, String description) {
		super();
		this.roleId = roleId;
		this.description = description;
	}
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
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

+ 필드와 생성자, 게터, 세터, toString() 생성



### DAO 생성 - 입력, 조회, 수정, 삭제하는 일들을 묶어 놓은 클래스

<img width="690" alt="스크린샷 2022-03-17 14 50 33" src="https://user-images.githubusercontent.com/88477839/158745677-d7b201c7-97b0-42c4-9799-eddd3747ebf0.png">



<img width="595" alt="스크린샷 2022-03-17 15 07 43" src="https://user-images.githubusercontent.com/88477839/158747448-b5e3dbd1-9987-40f0-9a6e-8507e026b68e.png">

+ 패키지명에 .dao 추가해준다.

~~~java
// RoleDao.java


package kr.or.connect.jdbcexam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.connect.jdbcexam.dto.Role;

public class RoleDao {
	
	//db에 접속하는 url, user, password 을 getRole메서드에서만 사용할게 아니라 
	//계속 중복돼서 사용할것이기때문에 상수처럼 바깥에다가 선언한다.
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
	
	public Role getRole(Integer roleId) { //이 메서드의 리턴타입은 Role 임
		Role role = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try { // 연결하는 과정에서 생길 에러나 예외 처리를 예방하기 위해 try catch로 감
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 버전 8.x.x일 경우 "com.mysql.cj.jdbc.Driver" 임.
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
      
			// where 조건 부분의 인자 값은 매번 바뀔것이다.
			// 이런 부분을 사용할때 물음표를 대신해서 사용해주는 것이 preparedStatement 라는 쿼리의 특징이다.
			// 이렇게 사용하게 되면 쿼리 자체는 계속 변경되지 않고 물음표가 바인딩 되는 부분만 바뀐다.
			String sql = "SELECT description,role_id FROM role WHERE role_id = ?";
			ps = conn.prepareStatement(sql);
      
			//v role_id컬럼이 Integer기 때문에 setInt이다.
			ps.setInt(1, roleId); //parameterIndex는 물음표의 순서이다. 첫번째 물음표에 roleId 값(getRole()의 인자)을 넣으라는 의미 
			rs = ps.executeQuery(); // 실행해주세요 라는 의미 
			
			if(rs.next()) { //next()의 리턴값이 boolean타입임.
				// 아래 값을 꺼내는 순서는 실제 테이블의 컬럼 순서가 아니라 위에서 작성한 쿼리문의 컬럼 순서로 적어줘야 한다.
				String description = rs.getString(1); // 첫번째 컬럼의 값 
				int id = rs.getInt("role_id");// 2를 써도 되고, 여기서는 컬럼의 이름을 적었다.
				role = new Role(id,description);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally { // 닫는 코드 실행시 발생할 예외 처리를 해줌 
			if(rs != null) {//만약 ps에서 예외가 발생한다면 rs는 null일것이다. 이런 상태에서  rs.close()를 실행시키면 Nullpoint 예외가 발생한다. 이를 방지해주기 위해 이런 코드를 작
				try {
					rs.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(ps != null) {
				try {
					ps.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn != null) {
				try {
					conn.close(); 
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return role;
	}
}

~~~



+ 실행하기

  ~~~java
  // JDBCExam1.java
  
  package kr.or.connect.jdbcexam;
  
  import kr.or.connect.jdbcexam.dao.RoleDao;
  import kr.or.connect.jdbcexam.dto.Role;
  
  public class JDBCExam1 {
  	public static void main(String[] args) {
  		RoleDao dao = new RoleDao();
  		Role role = dao.getRole(100);
  		
  		System.out.println(role);
  		
  	}
  }
  ~~~

  ~~~
  실행결과 (콘솔)
  
  Role [roleId=100, description=Developer]
  ~~~

  

### SELECT - 모두 조회하기

+ RoleDao클래스에 **public** List\<Role> getRoles() 메소드 추가

  ~~~java
  package kr.or.connect.jdbcexam.dao;
  
  import java.sql.Connection;
  import java.sql.DriverManager;
  import java.sql.PreparedStatement;
  import java.sql.ResultSet;
  import java.sql.SQLException;
  import java.util.ArrayList;
  import java.util.List;
  
  import kr.or.connect.jdbcexam.dto.Role;
  
  public class RoleDao {
  	
  	//db에 접속하는 url, user, password 을 getRole메서드에서만 사용할게 아니라 
  	//계속 중복돼서 사용할것이기때문에 상수처럼 바깥에다가 선언한다.
  	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
  	private static String dbUser = "connectuser";
  	private static String dbpasswd = "connect123!@#";
  	
  	public Role getRole(Integer roleId) { //이 메서드의 리턴타입은 Role 임
  		Role role = null;
  		Connection conn = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		
  		try { // 연결하는 과정에서 생길 에러나 예외 처리를 예방하기 위해 try catch로 감
  			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql 버전 8.x.x일 경우 "com.mysql.cj.jdbc.Driver" 임.
  			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
  			// where 조건 부분의 인자 값은 매번 바뀔것이다.
  			// 이런 부분을 사용할때 물음표를 대신해서 사용해주는 것이 preparedStatement 라는 쿼리의 특징이다.
  			// 이렇게 사용하게 되면 쿼리 자체는 계속 변경되지 않고 물음표가 바인딩 되는 부분만 바뀐다.
  			String sql = "SELECT description,role_id FROM role WHERE role_id = ?";
  			ps = conn.prepareStatement(sql);
  			//v role_id컬럼이 Integer기 때문에 setInt이다.
  			ps.setInt(1, roleId); //parameterIndex는 물음표의 순서이다. 첫번째 물음표에 roleId 값(getRole()의 인자)을 넣으라는 의미 
  			rs = ps.executeQuery(); // 실행해주세요 라는 의미 
  			
  			if(rs.next()) { //next()의 리턴값이 boolean타입임.
  				// 아래 값을 꺼내는 순서는 실제 테이블의 컬럼 순서가 아니라 위에서 작성한 쿼리문의 컬럼 순서로 적어줘야 한다.
  				String description = rs.getString(1); // 첫번째 컬럼의 값 
  				int id = rs.getInt("role_id");// 2를 써도 되고, 여기서는 컬럼의 이름을 적었다.
  				role = new Role(id,description);
  			}
  			
  		}catch (Exception e) {
  			e.printStackTrace();
  		}finally { // 닫는 코드 실행시 발생할 예외 처리를 해줌 
  			if(rs != null) {//만약 ps에서 예외가 발생한다면 rs는 null일것이다. 이런 상태에서  rs.close()를 실행시키면 Nullpoint 예외가 발생한다. 이를 방지해주기 위해 이런 코드를 작
  				try {
  					rs.close(); 
  				} catch (SQLException e) {
  					e.printStackTrace();
  				}
  			}
  			if(ps != null) {
  				try {
  					ps.close(); 
  				} catch (SQLException e) {
  					e.printStackTrace();
  				}
  			}
  			if(conn != null) {
  				try {
  					conn.close(); 
  				} catch (SQLException e) {
  					e.printStackTrace();
  				}
  			}
  		}
  		
  		
  		return role;
  	}
  	
  	
  	
  	// 모두 SELECT 하는 메서드
  	//try-with resource 이용 - 자원 쉽게 반납, 자원 자동해제
  	public List<Role> getRoles() { // 리턴타입 List<Role>로 바뀜 Role의 list를 리턴 
  		List<Role> list = new ArrayList<>();
  
  		try {
  			Class.forName("com.mysql.cj.jdbc.Driver");
  		} catch (ClassNotFoundException e) {
  			e.printStackTrace();
  		}
  
  		String sql = "SELECT description, role_id FROM role order by role_id desc";
  		
  		// V 이부분에다가 사용할 리소스를 얻어주는 코드를 만들면 알아서 여기에 들어있는 객체들을 close하는 일을 수행함 
  		try (Connection conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
  				PreparedStatement ps = conn.prepareStatement(sql)) {
  
  			try (ResultSet rs = ps.executeQuery()) {
  
  				while (rs.next()) { 
  					String description = rs.getString(1);
  					int id = rs.getInt("role_id");
  					Role role = new Role(id, description);
  					list.add(role); // list에 반복할때마다 Role인스턴스를 생성하여 list에 추가한다.
  				}
  			} catch (Exception e) {
  				e.printStackTrace();
  			}
  		} catch (Exception ex) {
  			ex.printStackTrace();
  		}
  		return list;
  	}
  }
  ~~~

+ 실행

  ~~~java
  // JDBCExam3.java
  
  package kr.or.connect.jdbcexam;
  
  import java.util.List;
  
  import kr.or.connect.jdbcexam.dao.RoleDao;
  import kr.or.connect.jdbcexam.dto.Role;
  
  public class JDBCExam3 {
  	public static void main(String[] args) {
  		RoleDao dao = new RoleDao();
  		List<Role> list = dao.getRoles();
  		
  		for(Role role : list) {
  			System.out.println(role);
  		}
  		
  	}
  }
  ~~~

  ~~~
  실행결과(콘솔)
  
  Role [roleId=102, description=Project manager]
  Role [roleId=101, description=Researcher]
  Role [roleId=100, description=Developer]
  ~~~

  



