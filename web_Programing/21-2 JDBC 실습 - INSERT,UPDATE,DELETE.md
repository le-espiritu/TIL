# JDBC

> JDBC는 JAVA 언어를 이용해서 DBMS로 부터 정보를 조회하는 방법이다.



## JDBC 실습 - INSERT



### Insert - RoleDao.java

~~~java
package kr.or.connect.jdbcexam.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.connect.jdbcexam.dto.Role;

public class RoleDao {
	private static String dburl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";

	public int addRole(Role role) {
    //Insert, Update, Delete문을 수행했을때 숫자 값으로 한건 입력했습니다 또는 세건 수정했습니다.
    //라는 식으로 결과를 주는데 이 결과를 담은 int형 변수 insertCount를 선언함
		int insertCount = 0;
    
    Connection conn = null;
    PreparedStatement ps = null;
    //Insert 문이기 때문에 ResutSet 객체는 없음 (결과 값을 ResultSet으로 가져오지 않기 때문 )

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
      
      conn = DriverManager.getConnection(dburl,dbUser,dbpasswd);
		
			String sql = "INSERT INTO role (role_id, description) VALUES ( ?, ? )";
			
      ps = conn.prepareStatement(sql);
		
			ps.setInt(1, role.getRoleId()); //인자로 받아온 Role 객체가 갖고 있는 Role_id
			ps.setString(2, role.getDescription()); //인자로 받아온 Role 객체가 갖고 있는 Description

			insertCount = ps.executeUpdate(); 
      // 쿼리가 실행됐을 때 Int값을 리턴 받
      // 쿼리 실행할때 Insert, Update, Delete는 executeUpdate()메서드 사용

		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
      if(ps != null){
        try{
          ps.close();
        }catch(Exception ex){}
      }
      
      if(conn != null){
        try{
          conn.close();
        }catch(Exception ex){}
      }
    }
		return insertCount;
	}
}
~~~



+ 테스트 코드

  ~~~java
  // JDBCExam2.java
  
  package kr.or.connect.jdbcexam;
  
  import kr.or.connect.jdbcexam.dao.RoleDao;
  import kr.or.connect.jdbcexam.dto.Role;
  
  public class JDBCExam2 {
  	public static void main(String[] args) {
  		int roleId = 500;
  		String description = "CTO";
  		
  		Role role = new Role(roleId, description);
  		
  		RoleDao dao = new RoleDao();
  		int insertCount = dao.addRole(role);
  
  		System.out.println(insertCount);
  	}
  }
  ~~~

  