/**
 * 
 */

function update(ID){
	alert("업데이트 자바스크립트 함수가 잘 실행되었습니다.");
	let f = document.createElement('form');
	f.setAttribute('method','post');
	f.setAttribute('action','/BBSSpring/posts/'+ID);
	
	let i = document.createElement('input');
	i.setAttribute('type','hidden');
	i.setAttribute('name','_method');
	i.setAttribute('value','patch');
	
	f.appendChild(i);
	
	document.body.appendChild(f);
	f.submit();
}