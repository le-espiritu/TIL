

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

