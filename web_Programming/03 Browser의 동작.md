# Browser의 동작

> 웹을 통해서 전달되는 데이터는 어딘가에서 해석돼야 한다.
>
> 서버에서 전송한 데이터(HTML과 같은)가 클라이언트에 도착해야 할 곳은 'Browser' 이다. Browser에는 데이터를 해석해주는 파서와 데이터를 화면에 표현해주는 렌더링엔진이 포함되어 있 다.



## Browser

### Browser

+ 브라우저는 월드와이드 웹(www)에서 정보를 검색, 표현하고 탐색하기 위한 소프트웨어이다.
+ 인터넷에서 특정 정보로 이동할 수 있는 주소 입력창이 있고, 서버와 HTTP로 정보를 주고 받을 수 있는 네트워크 모듈도 포함하고 있다.
+ 서버에서 받은 문서(HTML, CSS, JavaScript)를 해석하고 실행하여 화면에 표현하기 위한 해석가(Parser)들을 가지고 있다.
+ 브라우저 마다 서로 다른 엔진을 포함하고 있다.



### Browser 구성 (Browser components)

+ UI(User Interface) - 뒤로가기 버튼 x버튼 등
+ Browser engine - 소스코드를 실행해서 화면에 보여줄 수 있는 엔진, 브라우저 소프트웨어를 동작 시켜주는 핵심 엔진
+ Rendering engine - 화면에 위치를 잡고 색칠을 해주고, 픽셀 단위로 다양한 형태를 표현해줌
+ Data persistence - 브라우저도 일부의 데이터를 캐시를 하고 저장하고 관리한다.
+ Networking - 브라우저가 HTTP를 통해서 특정 인터넷 주소를 해석한 다음에 그 주소로 웹서버와 통신한다.
+ JavaScript Interpreter - JavaScript코드를 해석
+ UI Backend - UI영역을 처리할 수 있는 백엔드 영역



### 브라우저 엔진의 메인 플로(main flow)

+ Parsing HTML to construct the DOM tree - HTML을 파싱(문자단위로 해석해서 의미 파악)해서 DOM 트리 구조로 변환

+ Render tree construction - 렌더 트리 만듬

+ Layout of the render tree - 렌더 트리를 기준으로 CSS 합침(스타일 정보와 구조를 합쳐서 매칭)

+ Painting the render tree - 화면에 직접 그림을 그림

  

![webkitflow](https://user-images.githubusercontent.com/88477839/154785593-a24e89ec-a11d-445b-a9ed-7d86cd3db490.png)

##### WebKit main flow

+ HTML을 해석(HTML Parser)해서  DOM Tree를 만들고, CSS를 해석(CSS Parser)해서 CSS Tree(CSS Object Mode)을 만든다.
  + Parsing - 만약 2 + 3 -1 을 파싱한다고 했을때 이를 , 2,+,3,-,1로 잘게 분리함. 토큰이라고도함
  + 토큰 단위로 해석되는 방식은 일반적인 소스코드의 컴파일 과정이라고 보면 됨.
  + 토큰 단위로 다 잘라서 의미를 해석한 다음에 의미에 따라서 어떤 실행을 해주는 것
+ DOM Tree와 CSS Tree, 이 두 개는 연관되어 있으므로 Render Tree로 다시 조합된다.
+ 이렇게 조합된 결과는 화면에 어떻게 배치할지 크기와 위치 정보를 담고 있다.
+ 이후에 이렇게 구성된 Render Tree정보를 통해서 화면에 어떤 부분에 어떻게 색칠을 할지 Painting 과정을 거치게 된다.

+ 마크업(html)이 브라우저에서는 DOM트리 형식으로 보관됨
+ 토크나이저를 통해 HTML도 파싱이 된다.



### 참고 자료

+ https://www.html5rocks.com/en/tutorials/internals/howbrowserswork/
+ https://d2.naver.com/helloworld/59361 - 번역본