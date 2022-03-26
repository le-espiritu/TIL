# WEB UI



## 서비스 개발을 위한 디렉토리 구성



### JavaScript파일 구성

+ 간단한 내용의 JavaScript라면 한 페이지에 모두 표현하는 것도 좋다.

+ 그렇지 않다면 의미에 맞게 구분하는 방법이 더 좋다.



### HTML안에 JavaScript 구성하기

+ CSS는 head태그 안에 상단에 위치합니다.
  + DOM을 렌더링 하기 위해서 미리 CSS파일을 로딩해야 하기 때문

+ JavaScript는 body 태그가 닫히기 전에 소스파일 간 의존성을 이해해서 순서대로 배치합니다.
  + CSS와 HTML을 이용한 화면의 배치 크기를 렌더링 할 때  방해를 하지 않기 위함



### DOMContentLoaded

+ DOMContentLoaded : HTML이 모두 로드되고 DOM 트리가 완성되었지만 외부 리소스(image, etc)가 아직 로드되지 않은 시점에 발생한다. DOM이 준비된 상태이므로 DOM 노드 등을 제어할 수 있지만 image의 사이즈를 가져오는 작업 등은 할 수 없다. 

  반면에 load는 DOM을 포함해 image, style 등 모든 리소스가 로드된 후에 발생한다. 

  대부분의 경우 모든 리소스를 기다릴 필요가 없기 때문에, 좀 더 빠른 실행을 위해 load 대신에 DOMContentLoaded 혹은 같은 시점에 발생하는 jQuery의 ready를 사용한다. (*DOMContentLoaded는 ie 8 이하에서 지원되지 않음)



### 예제

~~~html
<html>
  <head>
    <link rel="styleSheet" href="./css/main.css"> <!-- rel은 relation을 의미-->
  </head>
  <body>
    <h1>hello, boostcourse web!</h1>
    <p> blah blah </p>
    
    <script src="js/common.js"></script>
    <script src="js/main.js"></script>
   
  </body>
</html>
~~~

~~~css
/* main.css */

h1{
  color : red;
}
~~~

~~~javascript
// main.js

var elHeading = document.querySelector("h1");
//console.log('heading tag is', elHeading); 
foo(elHeading);
~~~

~~~javascript
// common.js

function foo(msg){
  console.log(msg);
}
~~~



## DOMContentLoaded 이벤트



### load와 DOMContentLoaded의 차이 확인

+ 웹사이트에 접속해서 크롬 개발자도구로 이를 확인할 수 있다.
+ 크롬 개발자도구의 Network panel을 열어서 하단에  DOMContentLoded, load를 확인해보자
  + 두 개의 시간이 조금 다르다.
+ DOM Tree 분석이 끝나면 DOMContentLoaded 이벤트가 발생하며, 그 외 모든 자원이 다 받아져서 브라우저에 렌더링(화면 표시)까지 다 끝난 시점에는 Load가 발생한다.
+ 이를 이해하고, 필요한 시점에 두 개의 이벤트를 사용해서 자바스크립트 실행을 할 수 있다.
+ 보통 DOM tree가 다 만들어지면 DOM APIs를 통해서 DOM에 접근할 수 있기 때문에, 실제로 실무에서는 대부분의 자바스크립트코드는 DOMContentLoaded 이후에 동작하도록 구현한다.
+ 그 방법이 로딩속도 성능에 유리하다고 생각하기 때문이다.



### DOMContentLoaded 예제

+ 예제1

~~~javascript
document.addEventListener("DOMContentLoaded",function(){
  startSomething();
  initFoo();
  initBar();
  var el = document.querySelector("div");
});
~~~

+ 예제2

  ~~~javascript
  function init(){
    var target = document.querySelector(".outside");
    var btn = document.querySelector("button");
    btn.addEventListener("click",function(){
      var pre = parseInt(target.style.left);
      target.style.left = pre + 200 + "px";
    });
  }
  
  document.addEventListener("DOMContentLoaded", function(){
    console.log("DOM Loaded");
    init();
  });
  ~~~

  

### load 이벤트 이후에 작업하는게 좋은 경우는?

+ 이미지가 다 보이고나서 사용자에게 알림을 보내준다던가 하는 경우
+ load를 쓸일은 그렇게 많지는 않다.



## Event delegation



### 다수의 리스트에 이벤트 등록

+ 가로로 배치된 책 리스트가 있고, 각각 리스트에 클릭을 할때 어떤 이벤트가 발생해야 한다고 가정한다.

  ~~~html
  <ul>
    <li>
      <img src="https://images-na.,,,,,/513hgbYgL._AC_SY400_.jpg" class="product-image" >    	 </li>
    <li>
      <img src="https://images-n,,,,,/41HoczB2L._AC_SY400_.jpg" class="product-image" >    	 </li>
    <li>
      <img src="https://images-na.,,,,51AEisFiL._AC_SY400_.jpg" class="product-image" >  	 	 </li>
   	<li>
      <img src="https://images-na,,,,/51JVpV3ZL._AC_SY400_.jpg" class="product-image" >
   	</li>
  </ul>
  
  <section class="log"></section>
  ~~~

+ for문을 이용해 li 각각에 addEventListener로 이벤트를 등록한다.

  ~~~javascript
  var log = document.querySelector(".log");
  var lists = document.querySelectorAll("ul>li");
  
  for(var i=0,len=lists.length; i<len; i++){
  	lists[i].addEventListener("click",function(evt){
      log.innerHTML = "clicke"+evt.currentTarget.firstElementChild.src;
    });
  }
  ~~~

+ 위 코드에서 브라우저는 4개의 이벤트 리스너를 기억하고 있다.

+ 그런데 list가 훨씬 더 많다면 브라우저는 기억해야 할 이벤트 리스너도 그만큼 많아진다. 이는 비효율적이다.

  + 브라우저 입장에서는 기억해야 될 정보의 수가 늘어나면서 메모리도 많이 사용하게 된다.

+ 만약 list가 한 개 더 동적으로 추가되면 추가된 엘리먼트에 addEvntListener를 해줘야 하기 때문에 문제가 또 생긴다.



### 이벤트 버블링

+ 위의 문제를 target 정보로 해결할 수 있다.

  ~~~javascript
  ul.addEventListener("click", function(evt){
    console.log(evt.currentTarget.tagName, evt.target.tagName);
  });
  
  // 출력 결과 => UL IMG
  ~~~

  + 이럴 경우 li안에 이미지를 클릭하면 위 결과는 무엇일까요?

    만약 ul > li > img 태그를 클릭했다면 어떤 결과가 나올까요?

    그 전에 이벤트는 실행은 될까요? 정답은 '네' 입니다. 

  + li나 img태그는 ul태그에 속하기도 한다.

  + 따라서 UL에 등록한 이벤트 리스너도 실행이된다.

  + 이것을 이벤트 버블링이라고 한다.

  + 클릭한 지점이 하위엘리먼트라고 하여도, 그것을 감사고 있는 상위 엘리먼트까지 올라가면서 이벤트리스너가 있는지 찾는 과정이다.

  + 만약 img, li, ul에 각각 이벤트를 등록했었다면, 3개의 이벤트 리스터가 실행됐을 것이다.

  + 비슷하게 Capturing이라는 것도 있습니다. 반대로 이벤트가 발생하는 것인데요.

  + 기본적으로는 Bubbling 순서로 이벤트가 발생합니다.

  + Capturing 단계에서 이벤트 발생을 시키고 싶다면 addEventListener 메서드의 3번째 인자에 값을 true로 주면 됩니다. 

  + 즉 target 정보는 실제 클릭된 하위 엘리먼트를 알려준다.

  ![3-5-3_Event_Bubbling](https://user-images.githubusercontent.com/88477839/159832451-b6594845-cce3-425b-b53e-92ac1f63f92f.png)

  + 즉 target 정보는 실제 클릭된 하위 엘리먼트를 알려준다.

  ~~~javascript
  var log = document.querySelector(".log");
  var ul = document.querySelector("ul");
  
  ul.addEventListener("click", function(evt){
    var target = evt.target;
    if(target.tagname === "IMG"){
      log.innerHTML = "IMG URL은, " + target.src;
    }else if (target.tagname === "LI"){
      log.innerHTML = "IMG URL은, " + target.firstElementChild.src;
    }
  })
  ~~~

  

### 이벤트 델리게이션이란?

+ 자식 태그에 발생해야 될 이벤트를 위쪽 부모에 위임하는 것

~~~javascript
var ul = document.querySelector("ul");
ul.addEventListener("click",function(evt) {
  if(evt.target.tagName ==="IMG"){
    log.innerHTML = "clicked" + evt.target.src;
  }
});
~~~

+ 이제 addEventListener 메서드를 한번만 쓰면서 모든 list의 image정보를 확인 할 수 있다.
+ 더구나 list태그가 하나 더 추가된다고 하여도 문제 없이 작동한다.



## HTML templating



### HTML Templating 작업이란?

+ 아래 화면에 데이터를 Ajax로 JSON 형태로 받아와서 화면에 추가해야 한다고 생각해보자
+ 아래 리스트들의 내용은 모두 다 비슷하다. list태그로 html을 구현해보면 사진,가격, 이름, 별점, 추가정보(있거나 없거나)를 비슷한 tag를 사용해서 표현하면 된다.

<img width="1666" alt="3-5-4-1_product_list" src="https://user-images.githubusercontent.com/88477839/159923968-eda58120-a809-493a-b4c6-cc5d382c14bb.png">

+ HTML Templating 작업이란
  + 반복적인 HTML부분을 template로 만들어두고, 서버에서 온 데이터(주로 JSON)을 결합해서, 화면에 추가하는 작업이라고 할 수 있다.

<img width="829" alt="3-5-4-2_about_templating" src="https://user-images.githubusercontent.com/88477839/159924562-0c24fb98-7b3c-401c-98cf-66b5efdb8ce5.png">



### 결합하기

+ replace()메서드 활용하기

~~~javascript
var data = {
  title : "hello",
  content : "lorem dkfief",
  price : 2000
};

var html = "<li><h4>{title}</h4><p>{content}</p><div>{price}</div></li>";

var resultHTML = html.replace("{title}", data.title)
										 .replace("{content}", data.content)
										 .replace("{price}", data.price);
// 메서드 체이닝
~~~

+ replace메서드 이외에도 regular expression이라는게 있다.



## HTML templating 실습



### HTML Template의 보관

~~~javascript
var html = "<li><h4>{title}</h4><p>{content}</p><div>{price}</div></li>";
~~~

+ 위와 같은 html 문자열을 어딘가 보관해야 한다. javascript코드안에서 이런 정적인 데이터를 보관하는 것은 좋지 않기때문이다.
  + 서버에서 file로 보관하고 Ajax로 요청해서 받아온다.
    + a.template, b.template 이런 식으로 여러 개 만들어서 각각 필요한 템플릿을 서버에서 받아온다.
  + HTML코드안에 순겨둔다.
  + 간단한 것이라면 HTML안에 숨겨둘 수가 있다.
  + 숨겨야 할 데이터가 많다면 별도 파일로 분리해서 Ajax로 가져오는 방법도 좋다.



### Templating

~~~html
<script id = "template-list-item" type="text/template">
	<li>
		<h4>{title}</h4><p>{content}</p><div>{price}</div>
	</li>
</script>
~~~

+ HTML중 script태그는 type이 javascript가 아니라면, 브라우저는 렌더링하지 않고 무시하는데 이러한 성질을 이용한다.
  + type이름은 마음대로 지어도 된다.



+ 실습

  ~~~html
  <html>
    <head>
      <style>
        .content > li{ 
          border: 1px solic gray;
          list-style: none;
        }
      </style>
    </head>
    <body>
      <h2>template test example</h2>
      <ul class="content"></ul>
    </body>
    
    <script id="template-list-item" type="text/template">		
    	<li>
    		<h4>{title}</h4><p>{content}</p><div>{price}</div>
    	</li>
    </script>
    
    <script>
      //mock data , 원래는 Ajax로 데이터 가지고 와야함
      var data = [
        {title : "hello", content : "lorem", price: 2000},
        {title : "hello", content : "lorem", price: 2000}
      ];
      
      //html 에 script에서 가져온 html template
    	var html = document.querySelector("#template-list-item").innerHTML; <!-- 안의 내용물을 문자열로 가지고옴 -->
      
      var resultHTML="";
      for(var i =0; i<data.length; i++){
        resultHTML += html.replace("{title}",data[i].title)
        									.replace("{content}",data[i].content)
        									.replace("{price}",data[i].price);
      }
      
      document.querySelector(".content").innerHTML = resultHTML;
    </script>
  </html>
  ~~~



\* EX6에는 템플릿 리터럴이라는 것도 있다. 템플릿 리터럴을 사용하면 replace 메서드 없이 쉽게 템플릿 작업을 할 수 있다.



