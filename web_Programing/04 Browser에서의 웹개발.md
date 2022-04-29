# Browser에서의 웹 개발

> 웹 클라이언트 코드는 브라우저 안에서 동작한다.



## HTML 문서구조

### 크롬 개발자도구

+ 크롬 개발자도구 열기 - 윈도우 단축키 (Ctrl + shift + i)
+ 크롬 개발자도구 열기 - 맥 단축키 (Option + Command + i)
+ 개발자 도구에서 Network탭으로 가서 Response탭을 클릭하면 해당 URL로 요청을 보냈을때 보내주는 응답 코드를 볼 수 있음



### HTML 문서의 특징

+ HTML문서는 html이라는 태그로 시작해서 html태그로 끝난다.
+ head - 어떤 HTML 문서에 대한 의 추가적인 설명을 담고 있다.
+ body - 화면에 표현되어야 할 것들이 포함되어 있다. (div등)
+ HTML은 계층적이다.
+ HTML은 tag를 사용해서 표현한다.
+ JavaScript와 CSS가 html안에 여기저기 존재한다.



### 실습코드

+ ~~~html
  <!doctype html>
  <html>
    <head>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>저를소개해요</title>
      <link rel="stylesheet" href="css/style.css">
      <script src="js/start.js"></script>
    </head>
    <body>
      <h1>안녕하세요</h1>
      <div>코드스쿼드 크롱이라고 합니다</div>
      <script src="js/library.js"></script>
      <script src="js/main.js"></script>
    </body>
  </html>
  ~~~

+ JavaScript 코드는 body태그 닫히기 전에 위치하는 것이 렌더링을 방해하지 않아서 좋다.

  + 자바스크립트는 렌더링을 방해 한다.
  + 가령 alert코드를 실행한다고 했을때 head부분에 위치시키면 alert창이 닫히기 전까지 body 부분이 나타나지 않는다.
  + 하지만 body 아래 부분에 스크립트를 위치시키면 body부분이 나타나고 alert창이 뜬다.
  + Script코드 삽입 위치에 따른 여러가지 경우들 - https://jae04099.tistory.com/entry/HTML-script-%ED%83%9C%EA%B7%B8%EB%8A%94-%EC%96%B4%EB%94%94%EC%97%90-%EC%9C%84%EC%B9%98-%ED%95%B4%EC%95%BC-%ED%95%A0%EA%B9%8C

+ CSS코드는 head안에 위치해서 렌더링 처리시에 브라우저가 더 빨리 참고할 수 있게 하는 것이 좋다.



### 참고

+ 웹에서 html, css, JavaScript 를 테스트 할 수 있는 웹사이트 - http://jsbin.com/?html,output

