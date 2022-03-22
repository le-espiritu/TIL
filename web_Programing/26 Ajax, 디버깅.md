# Ajax



## Ajax 응답 처리와 비동기

> 브라우저의 새로고침 없이 데이터를 얻어오는 방법



### AJAX와 비동기

~~~javascript
function ajax(){
    var oReq = new XMLHttpRequest();
    
    oReq.addEventListener("load",function(){ //4라인
        console.log(this.responseText);
    });
    
    oReq.open("GET","http//www.example.org/example.txt"); //8라인
    oReq.send(); //9라인
}
~~~

+ 4라인의 익명함수는 8라인, 9라인보다 더 늦게 실행되는 함수이다.

  + 실제로 addEventListener()는 빨리 실행이 된다.
  + "load"시점에 익명함수를 실행해야 하는 구나 라고해서 익명함수를 이벤트 큐(메모리 공간)에 보관한다.(그리고나서  addEventListener()의 역할은 끝)
  + 그리고 나서  oReq.open()과 oReq.send()가 실행

+ 이 익명함수는 비동기로 실행되며, 이벤트큐에 보관되다가 load 이벤트가 발생하면(서버로부터 데이터를 브라우저가 받으면) 그때 call stack에 실행되고 있는 함수가 없어서 비어있다면 stack에 올라와서 실행된다.

+ #### **동기/비동기에 대한 것은 아래 자료를 참고**

  + [영상 바로가기](https://www.youtube.com/watch?v=8aGhZQkoFbQ)

  + [자료 (그림과 코드위주로 이해)](http://www.phpmind.com/blog/2017/05/synchronous-and-asynchronous/)

  + 아래 그림은 AJAX통신(jQuery라이브러리를 사용한 예제다)을 코드단위로 어떻게 비동기로 처리되는지 보여줍니다.

  + Ajax 통신(Jquery 라이브러리 사용 예제)

    <img width="1525" alt="3-3-1_Ajax(Jquery__)" src="https://user-images.githubusercontent.com/88477839/159431581-3966fcb4-bd09-4672-8fd5-a82a1b5aef78.png">

### Ajax 응답처리

+ 서버로부터 받아온 JSON 데이터는 문자열 형태임으로 브라우저에서 바로 실행할 수가 없다.

+ 따라서 문자열을 자바스크립트 객체로 변환해야 데이터에 접근할 수가 있다. 이를 하려면 문자열 파싱을 일일히 해야 하는 불편함이 있다.

  ~~~javascript
  var oReq = new XMLHttpRequest();
  oReq.addEventListener("load", function(){
      console.log(this.responseText);
  });
  oReq.open("GET", "./json.txt");
  oReq.send();
  ~~~

+ 브라우저에서는 JSON 객체를 제공하며 이를 활용해서 자바스크립트 객체로 변환할 수가 있다.

  ~~~javascript
  var json객체로변환된값 = JSON.parse("서버에서 받은 JSON 문자열");
  ~~~

  ~~~javascript
  var oReq = new XMLHttpRequest();
  oReq.addEventListener("load", function(){
      var jsonobj = JSON.parse(this.responseText);
      console.log(jsonobj);
  });
  
  oReq.open("GET", "./json.txt");
  oReq.send();
  ~~~

+ 활용

  ~~~javascript
  var oReq = new XMLHttpRequest();
  oReq.addEventListener("load", function(){
      var jsonobj = JSON.parse(this.responseText);
      var fruit = jsonobj.favorites[1];
      var outside = document.querySelector(".outside");
      outside.innerHTML += ("<span>"+fruit+"</span>");
  });
  
  oReq.open("GET", "./json.txt");
  oReq.send();
  ~~~

  ~~~html
  <html>
      <head>
          
      </head>
      <body>
          <div class="outside">제가 좋아하는 과일은요...</div>
      </body>
      <script src="test.js"></script>
  </html>
  ~~~

  

### cross domain 문제

+ XHR통신은 다른 도메인 간에는 보안을 이유로 요청이 안 됩니다.

  즉 A도메인에서 B도메인으로 XHR통신, Ajax 통신을 할 수 없습니다.

  이를 회피하기 위해서 JSONP라는 방식이 널리 사용되고 있습니다.

  최근에는 CORS라는 표준적인 방법이 제공되고 있어 이를 활용하는 경우도 등장했습니다. 

  CORS를 사용하기 위해서는 프로그램 코드에서 별도로 해야 할 것이 없고, 백엔드코드에서 헤더 설정을 해야 하는 번거로움이 있습니다.

  CORS와 JSONP의 적용 방법을 찾아보는 것도 좋은 방법입니다.

  JSONP는 아직도 많은 곳에서 사용하는 비표준이지만 사실상 표준으로 사용하는 것으로, CORS로 가기 전에 많은 곳에서 사용 중입니다.

  그 사용법을 참고로 알아보면 좋습니다.



## 디버깅 - 크롬 개발자 도구

> 웹 개발을 하다 보면 Ajax와 같은 요청처리에 대해서 문제가 생길 수 있습니다. 
>
> Ajax 통신에서 로직이 문제인지, 아니면 서버 쪽의 문제인지 등 궁금할 때가 많습니다.
>
> 이런 부분을 소스코드상으로는 디버깅을 할 수 없습니다. 
>
> Ajax뿐만은 아닙니다.
>
> 네트워크 통신과정의 상황을 지켜보면서 디버깅하는 방법을 알아봅니다.



### 크롬 개발자 도구의 네크워크 패털

+ 크롬 개발자도구는 여러 가지 기능을 제공합니다.
+ 녹화기능을 통해서 HTML, CSS, JavaScript, image파일을 내려받는 상황을 알 수 있습니다.
+ 흔히 겪는 404와 같은 응답 오류에 대해서 문제를 쉽게 찾을 수 있습니다.
+ 얼마나 서버로부터 응답이 걸리는지도 알 수 있습니다.
+ 즉 성능개선을 위해서 진단할 수 있는 도구 역할을 하는 것이죠. 

![3-3-2____](https://user-images.githubusercontent.com/88477839/159441989-ecf9dd96-1ab2-4e76-91dc-adb96cc3937a.png)

+ 다양한 탭을 통해서 XHR, JS 등 통신 항목만 추려서 그 결과를 확인할 수 있으므로, 디버깅을 쉽게 할 수 있습니다.
+ 또한, Name, status, type과 같은 항목도 내 맘대로 설정해서 노출할 수가 있습니다.
+ 전체적으로 HTTP통신과정에서 생기는 문제는 여기서 대부분 실마리를 찾을 수 있을 겁니다. 