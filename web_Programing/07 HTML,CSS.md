

# HTML



### HTML 레이아웃 태그

+ Semantic Tag - HTML5에서 처음 등장하는 tag들 (엄밀히 말하면 그 이전에 form, table, img 등이 있었음)
+ 시멘틱 태그는 의미가 있는 태그라는 뜻이다.
+ 관련된 레이아웃 태그들

![5086 HTML5PageLayout_2](https://user-images.githubusercontent.com/88477839/155254133-530d1976-1a68-4476-b758-b2ace7f7c289.jpg)

+ 시멘틱 태그 참고 자료 - https://velog.io/@gil0127/Semantic-Tag-%EC%A0%95%EB%A6%AC



### HTML 구조 설계

~~~html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width">
    <title>JS Bin</title>
  </head>
  <body>
    <header>
    	<h1>Company Name</h1>
      <img src="..." alt="logo">
    </header>
    
    <div id = "container">
      <nav>
      	<ul>
          <li>Home</li>
          <li>Home</li>
          <li>About</li>
          <li>Map</li>
        </ul>
      </nav>
      
      <div id = "roll-section">
        <button></button>
        <div><img src="dd" alt=""></div>
        <div><img src="dd" alt=""></div>
        <div><img src="dd" alt=""></div>
        <button></button>
      </div>
      
      <div>
        <ul>
          <li class="our_description">
          	<h3>What we do</h3>
            <div>bla bla bla</div>
          </li>
          <li class="our_description">
          	<h3>What we do</h3>
            <div>bla bla bla</div>
          </li>
          <li class="our_description">
          	<h3>What we do</h3>
            <div>bla bla bla</div>
          </li>
        </ul>
      </div>
    </div>
    <footer>
    	<span>Copyright @codewquad</span>
    </footer>
  </body>
</html>
~~~



### class와 id 속성

+ ID
  + 고유한 속성으로 한 HTML 문서에 하나만 사용 가능하다
  + 고유한 ID값이 있으면 하나하나에 특별한 제어를 할 수 있으며 검색에도 용이하다.
+ Class
  + 하나의 HTML문서 안에 중복해서 사용 가능하다.
  + 하나의 태그에 여러 개의 다른 Class 이름을 공백을 기준으로 나열할 수가 있다.
  + 홈페이지 전체적인 스타일을 일관성 있게 지정하기 위해서는 class 사용이 필수적이다.



# CSS



### CSS 선언방법

<img width="969" alt="스크린샷 2022-02-23 22 55 03" src="https://user-images.githubusercontent.com/88477839/155865002-75c8c1a9-0c4b-410f-8de0-8ccd8ca351cd.png">



### style을 HTML페이지에 적용하는 세 가지 방법

+ inline - html태그 안에다 넣는 방법

  + ~~~html
    <span style = "color:red;"> Hello world! </span>
    ~~~

  + 구조를 표현하는 HTML에 CSS가 섞여있어서 유지보수가 어렵고 관리하기가 어렵다는 단점이 있다.

+ internal - style 태그로 지정하기

  + ~~~html
    
    <head>
      <meta charset="utf-8"/>
      <title>World !</title>
      <style>
        span {
          color : red;
        }
      </style>
    </head>
    ~~~

  + 별도의 CSS파일을 관리하지 않아도 된다는 장점이 있다.

  + 서버에 CSS 파일을 부르기 위해서 별도의 브라우저가 요청을 또 보내는 일을 하지 않는다는 장점이 있다.

+ external - 외부파일(.css), 즉 별도의 CSS파일을 만들어서 지정하기

  + ~~~html
    <html>
      <head>
        <link rel="stylesheet" href="main.css">
      </head>
      <body>
        <span>hello world!</span>
      </body>
    </html>
    ~~~

  + ~~~css
    <!-- main.css 파일 -->
    
    span{
      color : red;
    }
    ~~~

  + 보통 이런식으로 HTML안에 CSS를 넣지 않고 별도 파일을 분리해서 관리한다.



### CSS 상속

+ 상위에서 적용한 스타일은 하위에도 반영이 된다.
+ Box-model이라고 불리는 속성들(width, height, margin, padding, border)과 같이 크기와 배치 관련된 속성들은 하위엘리먼트로 상속이 되지 않는다.



### CSS 캐스캐이딩

+ 같은 선택자(selector)라면 나중에 선언한 것이 반영된다.
+ 선택자의 표현이 구체적인 것이 있다면 먼저 적용됩니다.
  + body > span (o)
  + Span (x)
+ id > class > element 순으로 우선순위를 가지며 반영된다.



### CSS selector

+ tag로 지정하기

  ~~~html
  <style>
    span{
      color:red;
    }
  </style>
  ~~~

+ id로 지정하기

  ~~~html
  <style>
    #spantag{
      color:red;
    }
  </style>
  
  <body>
    <span id = "spantag"> Hello World! </span>
  </body>
  ~~~

+ class로 지정하기

  ~~~html
  <style>
    .spanClass{
      color:red
    }
  </style>
  
  <body>
    <span class="spanClass"> Hello World! </span>
  </body>
  ~~~

+ 그룹 선택

  ~~~css
  h1, span, div{color:red}
  h1, span, div#id{color:red}
  h1,span, div.classname{color:red}
  ~~~

+ 자손 선택(공백)

  + 아래 모든 span태그에 red색상이 적용된다.

    ~~~html
    <style>
      #jisu span{color:red}
    </style>
    
    <body>
      <div id="jisu">
        <div>
          <span> span tag </span>
        </div>
        <span> span tag2</span>
      </div>
    </body>
    ~~~

+ 자식 선택(>)

  + 자식은 바로 하위엘리먼트를 가르킴

  + span tag2만 red 색상이 적용된다

    ~~~html
    <style>
      #jisu > span{color:red}
    </style>
    
    <body>
      <div id="jisu">
        <div>
          <span> span tag </span>
        </div>
        <span> span tag2</span>
      </div>
    </body>
    ~~~

+ n번째 자식요소 선택 (nth-child)

  ~~~html
  <style>
    #jisu > p:nth-child(2){color:red}
  </style>
  
  <body>
    <div id="jisu">
      <h2>단락 선택</h2>
      <p>첫번째 단락입니다.</p>
      <p>두번째 단락입니다.</p>
      <p>세번째 단락입니다.</p>
      <p>네번째 단락입니다.</p>
    </div> 
  </body>
  ~~~

  + #jisu > p:nth-child(2){color:red}
  
    + id가 jisu인 태그의 자식 태그들 중에서 2번째 자식 태그가 p이면 색상을 red로 변경
    + 위 코드에서 \<p>첫번째 단락입니다.\</p> 가 red로 변경됨
  
    

### CSS 기본 Style 변경하기

+ em

  + 기본값 대비 상대적인 크기를 배수로 지정할 수 있다.
  + 1em은 상속받은 크기 *1 이라는 뜻임
  + 아래코드에서 상속받은 픽셀이 32px 이고 2em 이기 때문에 2em은 64px이 됨
  + 상속을 받지않았을때는 기본적으로 16px이다. 

  ~~~html
  <html>
    <head>
      <style>
        body > div{
          font - size : 32px;
          font-family: sans-serif;
        }
        .myspan {
          color: red;
          font-size : 2em;
        }
      </style>
    </head>
    
    <body>
      <div>
        <sapn class="myspan">my text is upper!</sapn>
      </div>
    </body>
  </html>
  ~~~

  

