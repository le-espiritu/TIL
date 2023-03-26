package kr.or.bbs.BBSSpring.dao;

public class BbsDaoSqls {
	public static final String SELECT_PAGING = "SELECT * FROM bbs WHERE bbsAvailable =1 ORDER BY bbsID DESC limit :start, :limit";
	// sql 에서 limit 사용시 offset을 사용하면 가져오고자 하는 행 데이터의 시작지점을 지정할 수 있다.
	// Offset 값은 0부터 시작한다.
	public static final String SELECT_BY_BBSID = "SELECT * FROM bbs WHERE bbsID = :bbsID";
	public static final String SELECT_BBSID = "SELECT bbsID FROM bbs ORDER BY bbsID DESC";
	public static final String UPDATE = "UPDATE bbs set bbsTitle = :bbsTitle, bbsContent= :bbsContent where bbsID = :bbsID";
	public static final String DELETE = "UPDATE bbs set bbsAvailable = 0 where bbsID = :bbsID";
	public static final String SELECT_COUNT = "SELECT count(*) FROM bbs";
}
