package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/bbs";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword) {
		String SQL = "select userPassword from user where userID =?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if( rs.next()) { // 쿼리 결과값이 있음 -> 아이디가 있음  
				if(rs.getString(1).equals(userPassword)) {
					return 1; // 로그인 성공  
				}else {
					return 0; // 비밀번호 불일치  
				}
			}
			return -1; // 아이디가 없음 
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return -2; // 데이터베이스 오류  
	}
	
	public int join(User user) {
		String sql = "insert into user values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return-1; // 데이터베이스 오류 났을때 
		// userID가 기본키이고, 기본키는 중복될 수 없기때문에 만약 아이디가 같다면 쿼리문이 제대로 실행되지 않고
		// 오류가 나게 된다. 
	}

}
