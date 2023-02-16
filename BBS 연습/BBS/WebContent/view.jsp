<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="bbs.BbsDAO" %>
<%@ page import="bbs.Bbs" %>
<%@ page import="java.io.PrintWriter" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<title>Insert title here</title>
</head>
<body>

	<%
		String userID=null;
		if(session.getAttribute("userID")!=null){
			userID=(String)session.getAttribute("userID");
		}
		
		int bbsID=0;
		if(request.getParameter("bbsID")!=null){
			bbsID=Integer.parseInt(request.getParameter("bbsID"));
		}
		
		if(bbsID==0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("location.href='bbs.jsp'");
			script.println("</script>");
		}
		
		BbsDAO bbsDAO = new BbsDAO();
		Bbs bbs = bbsDAO.getBbs(bbsID);
		
	%>

	<nav>
		<div>
			<button>
				<span></span>
				<span></span>
				<span></span>
			</button>
			<a href='main.jsp'>JSP 게시판 웹 사이트</a>
		</div>
		<div>
			<ul>
				<li><a href='main.jsp'>메인</a></li>
				<li><a href='bbs.jsp'>게시판</a></li>
			</ul>
			
			<%
				if(userID==null){
			%>
				
				<ul>
					<li>
						<a href='#'>접속하기</a>
						<ul>
							<li><a href='login.jsp'>로그인</a></li>
							<li><a href='join.jsp'>회원가입</a>
						</ul>
					</li>
				</ul>
			<%
				}else{
			%>
				<ul>
					<li>
						<a href='#'>회원관리</a>
						<ul>
							<li><a href='logoutAction.jsp'>로그아웃</a></li>
						</ul>
					</li>
				</ul>
			<%		
				}
			%>
			
		</div>
	</nav>
	
	<div class="container">
		<div>
			<table style="text-align:center; border:solid 1px #eeeeee">
				<thead>
					<tr>
						<th colspan="3" style="background-color:#dddddd">게시글</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td style="width: 20%">작성자</td>
						<td colspan="2"><%=bbs.getUserID() %></td>
					</tr>
					<tr>
						<td >제목</td>
						<td colspan="2"><%=bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n","<br>") %></td>
					</tr>
					<tr>
						<td>작성일</td>
						<td colspan="2"><%=bbs.getBbsDate().substring(0,11)+bbs.getBbsDate().substring(11,13)+"시"+bbs.getBbsDate().substring(14,16)+"분" %></td>
					</tr>
					<tr>
						<td>내용</td>
						<td colspan="2" style="min-height: 200px; text-align : left;"><%=bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>
					</tr>
					
				
				</tbody>
			</table>
			
			<%
				if(userID!=null && userID.equals(bbs.getUserID())){
			%>
				<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="deleteAction.jsp?bbsID=<%=bbsID%>">삭제하기</a>
				<a href="update.jsp?bbsID=<%=bbsID%>">수정하기</a>
			<%
				}
			%>
			
			<a href='bbs.jsp'>목록가기</a>
		</div>
	</div>

</body>
</html>