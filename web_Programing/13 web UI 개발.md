# WEB UI 개발 - FE



## window 객체

~~~javascript
window.setTimeout()
setTimeout() //window는 전연객체라서 생략가능하다.
~~~

+ window는 디폴트 개념임으로 생략할 수 있다.



## DOM과 querySelector



## Browser Event, Event object, Event handler



### 이벤트 등록

+ addEventListener함수를 사용하여 이벤트를 등록할 수 있다.

~~~javascript
var el = document.getElementById("outside");
el.addEventListener("click", function(){
  //do something..
}, false);
~~~

+ addEventListener함수의 두번재 인자는 함수다. 
+ 이 함 수는 나중에 이벤트가 발생할때 실행되는 함수로 이벤트핸들러 또는 이벤트리스너 또는 콜백함수라고 한다.

+ Event Handler(Event Listener)콜백함수는 이벤트가 발생할 때 실행 됨



## Ajax 통신의 이해

> 브라우저의 새로고침 없이 데이터를 얻어오는 방법



### AJAX 실행코드

~~~javascript
function ajax(data){
  var oReq = new XMLHttpRequest();
  oReq.addEventListener("load", function(){
    console.log(this.responseText);
  });
  oReq.open("GET", "http://www.example.org/getData?data=data"); //parameter를 붙여서 보낼 수 있음
	oReq.send();
}

~~~

+ XMLHttpRequest객체를 생성해서, open메서드로 요청을 준비하고, send메서드로 서버로 보낸다.
+ 요청처리가 완료되면(서버로 응답이 오면) load이벤트가 발생하고, 콜백함수가 실행된다. 콜백함수가 실행될때는 이미 ajax함수는 반한되고 콜스택에서 사라진 상태다.