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
  + 이 DAO 객체는 딱 그 일만 하도록 만들어 놓 객체이다.



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



