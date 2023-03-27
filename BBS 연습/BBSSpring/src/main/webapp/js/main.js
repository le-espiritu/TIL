/**
 * 
 */

function goMain(){
	location.href="/BBSSpring/main";
	/*location.href="main";*/
	/*절대경로(위), 상대경로(아래) 차이*/
	/*postView.jsp의 @PathVariable와 관련하여 위 경로로 바꿔줌 */
}

function logout(){
	alert("로그아웃 요청을 받았습니다.");
	
	let f =document.createElement('form');
	f.setAttribute('method','post');
	f.setAttribute('action','/BBSSpring/user');
	
	let i =document.createElement('input');
	i.setAttribute('type','hidden');
	i.setAttribute('name','_method');
	i.setAttribute('value','delete');
	
	f.appendChild(i);
	
	document.body.appendChild(f);
	
	f.submit();
	
}