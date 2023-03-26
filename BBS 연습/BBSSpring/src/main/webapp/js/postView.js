/**
 * 
 */

function deletePost(ID){

	let f = document.createElement('form');
	f.setAttribute('method','post');
	f.setAttribute('action','/BBSSpring/posts/'+ID);
	
	let inp = document.createElement('input');
	inp.setAttribute('type','hidden');
	inp.setAttribute('name','_method');
	inp.setAttribute('value','delete');
	
	f.appendChild(inp);
	
	document.body.appendChild(f);
	f.submit();
}