package kr.or.bbs.BBSSpring.dao;

public class UserDaoSqls {
	public static final String LOGIN = "select userPassword from user where userID = :userID";
}
