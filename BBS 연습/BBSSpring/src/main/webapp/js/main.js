/**
 * 
 */

function goMain(){
	location.href="main";
}

function logout(){
	alert("hello");
	let f = document.createElement('form');
	f.setAttribute('method','post');
	f.setAttribute('action','logout');
	
/*	let i = document.createElement('input');
	i.setAttribute('type','hidden');
	i.setAttribute('name','_method');
	i.setAttribute('value','delete');
	
	f.appendChild(i);*/
	
	document.body.appendChild(f);
	f.submit;
}