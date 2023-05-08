<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

보드 페이지 입니다. 


	<div>
		<form action="/mvcxml2/board" method="post" enctype="multipart/form-data"> 
		<!-- 파일 업로드를 위해 반드시 enctype="multipart/form-data" 적어줘야함 -->
			<h2>파일 여러개 등록 및 db 저장하기 </h2>
			<h3>input 태그에 multiple 키워드가 있는 경우 파일을 여러개 선택할 수 있다.</h3>
			<div>
				<label>제목</label>
				<input type="text" placeholder="글 제목" name="boardTitle">
			</div>
			<div>
				<label>내용</label>
				<textarea placeholder="글 내용" name="boardContent" maxlength="2048"></textarea>
			</div>
			<div>
				<label>파일</label>
				<input type="file" name="files" multiple>
				<!-- multiple 키워드를 붙여주면 파일을 선택할때 여러개를 선택할 수 있다.
				그리고 커맨드 객체(DTO,VO) 특성상 input 태그의 name과 DTO의 필드명을 일치시켜줘야 컨트롤러에서 DTO로 넘겨받을 수 있다.  -->
			</div>
			
			<input type="submit" value="제출하기">
		</form>
	</div>


	<br>
	<br>
	
	
	<div>
		<form action="/mvcxml2/board/upload1" method="post" enctype="multipart/form-data"> 
		<!-- 파일 업로드를 위해 반드시 enctype="multipart/form-data" 적어줘야함 -->
			<h2>파일 1개만 등록</h2>
			<div>
				<label>제목</label>
				<input type="text" placeholder="글 제목" name="boardTitle">
			</div>
			<div>
				<label>내용</label>
				<textarea placeholder="글 내용" name="boardContent" maxlength="2048"></textarea>
			</div>
			<div>
				<label>파일</label>
				<input type="file" name="file">
			</div>
			
			<input type="submit" value="제출하기">
		</form>
	</div>
	
	<br>
	<br>
	
	<div>
		<form action="/mvcxml2/board/upload2" method="post" enctype="multipart/form-data"> 
		<!-- 파일 업로드를 위해 반드시 enctype="multipart/form-data" 적어줘야함 -->
			<h2>파일 여러개 등록 테스트</h2>
			<div>
				<label>제목</label>
				<input type="text" placeholder="글 제목" name="boardTitle">
			</div>
			<div>
				<label>내용</label>
				<textarea placeholder="글 내용" name="boardContent" maxlength="2048"></textarea>
			</div>
			<div>
				<label>파일1</label>
				<input type="file" name="file">
			</div>
			<div>
				<label>파일2</label>
				<input type="file" name="file">
			</div>
			<!-- multiple 키워드가 없어서 파일을 선택할때 여러개를 선택할 수 없다.
			그러나 file 타입의 input을 여러개 만들어서, input의 개수 만큼 파일을 업로드할 수 있다.
			그리고 커맨드 객체(DTO,VO) 특성상 input 태그의 name과 DTO의 필드명을 일치시켜줘야 컨트롤러에서 DTO로 넘겨받을 수 있다.  -->
			
			<input type="submit" value="제출하기">
		</form>
	</div>

	

</body>
</html>