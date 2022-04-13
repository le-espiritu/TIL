# JS 라이브러리 활용과 클린코드



## JavaScript 라이브러리



+ 자바스크립트 라이브러리는 자바스크립트 개발을 도와주는 보조적인 함수나 메서드들의 집합이다.



### **jQuery 의 10년**

지난 10년간, JS라이브러리는 jQuery가 그 인기를 독차지했었습니다.

2018년 현재 그 인기는 많이 줄었습니다.

어떤 이는 이제 더 배울 필요가 없다고 이야기되고 합니다.

(실제로는 기존에 적용된 코드를 수정하느라 웹개발자들이 쉽게 jQuery를 떠날 수는 없을 겁니다.)

jQuery가 인기 있던 이유는 꽤 많은데요, 몇 가지 꼽으면 다음과 같습니다.

- IE6,7,8를 포함해서 다양하게 동작하는 웹브라우저의 API 간의 차이를 줄여주었습니다.
  - 인터넷 익스플로러가 독점하던 시기에는 인터넷 익스플로러가 어떤 웹의 표준을 많이 지키지는 못하고 여러 가지 다른 방법들로 어떤 기능을 구현했었다.
  - 그러다 보니 개발자들이 구현한 코드가 잘 동작하지 않는 경우가 많이 있었다.
  - 이런것들을 브라우저 간의 차이, 브라우저 호환성 이슈라고 한다.
  - 브라우저 호환성 이슈를 JQuery가 많이 줄여줬었다.
- 복잡하고 어려운 DOM APIs를 추상화해서 제공해서 쉽게 DOM 조작이 가능합니다.
- 사고의 흐름에 맞춰 프로그래밍할 수 있습니다.

jQuery에서 DOM조작을 어떻게 처리하는지 찾아보면 꽤 편리합니다.

물론 그전에 DOM을 native방식으로 접근해야, jQuery가 얼마나 편리한지 알겠지만요.

아래 코드는 '사고의 흐름'이라는 관점에서 이해할 수 있는 jQuery코드 입니다.

~~~javascript
//p1아이디를 가진 엘리먼트를 찾아서, color를 red 로 하고, slideup을 2초간, slideDown을 2초간 한다.
$("#p1").css("color", "red").slideUp(2000).slideDown(2000); 
~~~

주석에 적힌 내용대로 코드를 그대로 구현할 수 있습니다.

메서드를 연속적으로 부르는 방식을 method chaining이라고 합니다.

이러한 방식은 jQuery뿐만 아니라 많은 라이브러리에서 제공하고 있습니다.



### **jQuery 인기가 하락하는 이유**

인기 있던 이유를 하나씩 현재 다시 보면 이렇습니다.

- 점차 브라우저 호환성 이슈가 줄었습니다.
  - 인터넷 익스플로러 점유율이 많이 떨어짐
- DOM APIs는 그대로지만, 7~8년 전부터 등장한 JS Frameworks 들이 좀 더 추상화된 방식으로 DOM 접근을 도와주고 있습니다.
- 다른 라이브러리들도 이런 방식을 지원도 합니다.

그 외에도 ECMAScript 2015(ES6)부터 편리한 함수들이 많이 제공되고 있습니다.

jQuery가 제공했던 유용한 기능들이 JavaScript표준방법으로 사용할 수 있게 된 것이죠.



### **Framework**

짧게 요약하면 웹에서 할 수 있는 것들이 많아지면서, Single Page Application이라는 서비스개념이 등장했습니다.

즉 웹에서 데이터처리나 복잡한 Ajax처리, routing처리 등이 많아지면서, 이를 편리하게 해주는 Framework가 나왔습니다.

React, Angular, Vue, Ember와 같은 것들이 그런 것입니다.

이를 사용하면 좀 더 쉽게 DOM제어를 할 수 있고, Data조작을 View에서 분리해서 관리할 수 있습니다.

그리고 component방식으로 개발할 수 있어 재사용가 능한 코드를 만들 수도 있고요.

라이브러리가 유용한 함수들을 제공한다고 할 수 있다면, Framework는 큰 애플리케이션의 구조를 잡는 것을 도와주는 역할을 하죠.

특히 데스크탑 웹 개발에서 더 유용하게 사용할 수 있습니다.



###  **그 밖에**

라이브러리 수준에서 유용한 것들은 이제는 Framework에서 이를 얼마나 사용하는가에 의해서 인기가 달라집니다.

다시 말해서, '어떤 Framework가 인기가 있는가?'에 의해서 그 Framework이 가진 철학에 따라서 필요한 라이브러리가 의존적으로 많이 쓰이고 인기를 얻고 있습니다.

그렇다보니, Framework과 관련 없이 많이 쓰일만한 라이브러리는 이제 별로 없습니다.

Observables을 처리해주는 RxJS나, Lodash와 같은 데이터를 쉽게 처리해주는 유틸리티 등이 인기를 얻는 정도입니다.

나머지 라이브러리는 해당 Framework가 사용하는 것이 무엇인가에 따라 달라집니다.

예를 들어 React에서는 Immutable.js나 Redux 등이 인기를 얻고 있는 것처럼요.



### **jQuery를 사용해야 한다면,**

많은 웹서비스의 legacy코드는 아직도 jQuery나 오래된 라이브러리를 사용합니다.

당분간 이를 유지 보수해야 하는 개발자는 상당히 많을 것입니다.

jQuery를 다뤄야 할 때는 몇 가지 가이드를 기억해두면 좋습니다.

jQuery의 버전을 잘 확인하고, 그 버전에 맞는 적절한 메서드를 선택합니다.

한 페이지에 여러 가지 jQuery버전이 없도록 합니다.

가급적 대체 가능한 메서드는 표준 JavaScript메서드를 사용하면서 jQuery의존도를 줄여나갑니다.



### **결론**

라이브러리나 프레임워크는 필요한 시점에 적절한 것을 골라서 사용하면 됩니다.

그리기 위해서는 라이브러리나 프레임워크가 어떤 목적을 가지고 있는 것인지 관심을 두는 게 좋습니다.

그래야 우리에게 필요한 것을 잘 선택할 수 있습니다.

어쩌면 라이브러리가 필요하지 않은 경우도 상당히 많습니다.



## handlebar를 활용한 템플릿 작업

> handlebar라는 라이브러리를 활용한 템플레이팅 작업.



### 기본 예제

+ 템플릿 코드 작성

  ~~~html
  <html>
    <head>
      <style>
        li{
          list-style : none;
        	padding: 3%;
          border-top: 1px solid gray;
        }
      </style>
    </head>
    <body>
      <h1>template using handlebar</h1>
      
      <section class="show">
      
      </section>
      
      <script type="myTemplate" id="listTemplate">
        <li>
          <div>게시자 : {{name}}</div> 
          <div class="content">{{content}}</div>
          <div>좋아요 갯수 <span> {{like}} </span></div>
          <div class="comment">
            <div>{{comment}}</div>
          </div>
        </li>
      </script>
      
      <!-- handlebar 설치-->
      <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.11/handlebars.min.js"></script>
      
      <script>
        
        var data = {
            "id" : 88,
            "name" : "crong",
            "content" : "새로운글을 올렸어요",
            "like" : 5, 
            "comment" : "댓글이다"
        };
  
      	var template = document.querySelector("#listTemplate").innerText;
        //V pre-compile
        var bindTemplate = Hanlebars.compile(template); //bindTemplate는 메서드이다.
        //complie() 함수는 함수를 반환하는 함수
        var resultHTML = bindTemplate(data);
        var show = document.querySelector(".show");
        show.innerHTML = resultHTML;
      </script>
      
    </body>
  </html>
  
  
  ~~~

  + {{name}} - brace 표기법?



## handlebar를 활용한 템플릿 작업2



### 배열이 포함된 데이터의 처리

+ 예를 들어 comment가 1개 이상일 수 있다.

  ~~~javascript
  var data = {
    	"id" : 88,
      "name" : "crong",
      "content" : "새로운글을 올렸어요",
      "like" : 5, 
      "comment" : ["댓글이다", "멋진글이네요", "잘봤습니다"]
  };
  
  bindTemplate(data);
  ~~~

+ 이런경우 아래와 같이 반복문을 넣을 수도 있다.

  ~~~html
  <script type="myTemplate" id="listTemplate">
    <li>
    	<div>게시자 : {{name}}</div>
  		<div class="content">{{content}}</div>
  		<div class="comment">
        <h3>댓글목록</h3>
  			{{#each comment}}
  				<div>{{@index}}번째 댓글 : {{this}}</div>
  			{{/each}}
      </div>
    <li>
  </script>
  ~~~

  + #each - 이 토큰을 기준으로 반복이 일어난다.(/each를 만날때까지)



### data자체가 많아진 경우의 처리

+ ~~~javascript
  var data = [
  	{"id" : 88, "name" : "crong", "content" : "새로운글을 올렸어요", "like" : 5, "comment" : ["댓글이다", "잘했어요"]},
  	{"id" : 28, "name" : "hary", "content" : "전 오늘도 노래를 불렀어요", "like" : 0, "comment" : ["제발고만..","듣고싶네요 그노래"]},
  	{"id" : 23, "name" : "pororo", "content" : "크롱이 항상 말썽을 피워서 행복해~", "like" : 4, "comment" : []},
  	{"id" : 5, "name" : "pobi", "content" : "물고기를 한마리도 잡지 못하다니..", "like" : 5, "comment" : ["댓글이다", "멋진글이네요", "잘봤습니다"]}
  ];
  ~~~

  ~~~javascript
  var template = document.querySelector("#listTemplate").innerText;
  var bindTemplate = Handlebars.compile(template);
  
  var resultHTML = data.reduce(function(prev,next){
    return prev+bindTemplate(next); //prev는 누적 계산값, next(두번째 인수)는 현재값
  },""); // ""은 초기값
  
  var show = document.querySelector(".show");
  show.innerHTML = resultHTML;
  ~~~

  + Reduce()는 누적된 합을 계산할때 유용하다.



### 조건 상황에따른 처리

+ 댓글이 없는 경우에는 다른 메시지를 처리해야하는 상황

+ template는 고정되어 있는것이지만 handlebar에서는 이를 지원함

  ~~~html
  <script type="myTemplate" id="listTemplate">
  
    <li>
      <div>게시자 : {{name}}</div>
      <div class="content">{{content}}</div>
      <div>좋아요 갯수 <span> {{like}}</span></div>
      <div class="comment">
        <h3>댓글목록</h3>
        {{#if comment}}
          {{#each comment}}
            <div>{{@index}}번째 댓글 : {{this}}</div>
          {{/each}}
        {{else}}
          <div>댓글이 아직 없군요</div>
        {{/if}}
      </div>
    </li>
  
  </script>
  ~~~

  

## handlebar를 활용한 템플릿 작업3



### Help function 사용

+ 좀 더 다양한 상황을 처리해야 한다면, help function을 통해서 처리할 수도 있다.

+ 좋아요 갯수가 5개 이상이면 "축하해요. 좋아요가 5개 이상 입니다." 라는 문자열을 추가하겠다.

+ template에 'likes'라는 커스텀 항목을 추가했다.

  ~~~html
  <script type="myTemplate" id="listTemplate">
  
    <li>
      <div>게시자 : {{name}}</div>
      <div class="content">{{content}}</div>
  
      {{#likes like}}
        {{like}}
      {{/likes}}
  
      <div class="comment">
        <h3>댓글목록</h3>
        {{#if comment}}
          {{#each comment}}
            <div>{{@index}}번째 댓글 : {{this}}</div>
          {{/each}}
        {{else}}
          <div>댓글이 아직 없군요</div>
        {{/if}}
      </div>
    </li>	
  
  </script>
  
  
  ~~~

+ likes helper 만들기

  ~~~javascript
  Handlebars.registerHelper("likes",function(like){
    if (like>4){
      return "<span>축하해요 좋아요가 " + like + "개 이상입니다!</span>";
    }else if (like<1){
      return "아직 아무도 좋아하지 않아요.. ";
    }else{
      return like + "개의 좋아요가 있네요";
    }
  });
  ~~~

  + likes는 키값이 되고 function(like){}는 밸류값이 된다.

  ~~~html
  <script>
    
    var data = [
      {"id" : 88, "name" : "crong", "content" : "새로운글을 올렸어요", "like" : 5, "comment" : ["댓글이다", "잘했어요"]},
      {"id" : 28, "name" : "hary", "content" : "전 오늘도 노래를 불렀어요", "like" : 0, "comment" : ["제발고만..","듣고싶네요 그노래"]},
      {"id" : 23, "name" : "pororo", "content" : "크롱이 항상 말썽을 피워서 행복해~", "like" : 4, "comment" : []},
      {"id" : 5, "name" : "pobi", "content" : "물고기를 한마리도 잡지 못하다니..", "like" : 5, "comment" : ["댓글이다", "멋진글이네요", "잘봤습니다"]}
    ];  
    
    Handlebars.registerHelper("likes",function(like){
      if (like>4){
        return "<span>축하해요 좋아요가 " + like + "개 이상입니다!</span>";
      }else if (like<1){
        return "아직 아무도 좋아하지 않아요.. ";
      }else{
        return like + "개의 좋아요가 있네요";
      }
    });  
    
    
    var template = document.querySelector("#listTemplate").innerText;
    var bindTemplate = Handlebars.compile(template);
  
    var resultHTML = data.reduce(function(prev,next){
      return prev+bindTemplate(next); //prev는 누적 계산값, next(두번째 인수)는 현재값
    },""); // ""은 초기값
  
    var show = document.querySelector(".show");
    show.innerHTML = resultHTML;  
    
    
  </script>
  ~~~

  

## 클린코드

> 프래그래밍 코드는 컴퓨터가 올바르게 해석할 수 있도록 구현해야 한다.
>
> 그런데 결국 코드는 사람들이 같이 수정하고 공유하는 것이기 때문에 궁극적으로는 사람이 이해할 수 있는 코드를 구현하는 것이 중요하다.



### **클린코드**

클린 코드라는 것은 읽기 좋은 코드, 가독성 있는 코드를 말합니다.

사람마다 더 좋은 코드가 무엇인지는 조금씩 다를 수 있습니다.

클린코드 내용을 담고 있는 책이나 글을 많이 보면, 어떤 코드가 좋은지 점점 알 수가 있습니다.

더 좋은 방법은 많은 사람에게 코드리뷰를 받는 것입니다.

아래 몇몇 예시는 클린코드를 만드는 몇 가지 examples에 지나지 않습니다.

이를 참고로 클린코드가 어떤 것인지 알게 되길 바랍니다.



### **이름 (코딩컨벤션)**

이름을 짓는 건 쉬운 일은 아니지만, 읽기 좋은 코드를 만드는데 필수 요소입니다.

이런 부분을 고려하세요.

- 함수는 목적에 맞게 이름이 지어져 있는가?
- 함수 안의 내용은 이름에 어울리게 하나의 로직을 담고 있는가?
- 함수는 동사 + 명사이며 함수의 의도를 충분히 반영하고 있는가?
- 함수는 카멜표기법 또는 _를 중간에 사용했는가?
- 변수는 명사이며 의미 있는 이름을 지었는가?

 

### **의도가 드러난 구현패턴**

어떤 코드가 좋은지 고민이라면, 내 코드를 들여다보고 그 의도가 잘 보이는지 확인해봅니다.

예를 들어, var a = value * 19.2; 이라는 코드가 있다고 봅시다.

도대체 19.2가 무엇을 의미하는지? 알 수가 없습니다.

대신 변수로 저장하고, 변수에 적절한 이름을 쓰면 더 좋습니다.(ex) var paddingValue=19.2;)



## **지역변수로 넣으면 될 걸 전역공간에 두지 말기**

함수 내에서만 사용이 필요로 한 것은 지역변수로 만들어야 합니다.

불필요한 전역변수는 최소화해야 코드가 많아지고 수정할 때 복잡함을 줄일 수 있습니다.

~~~javascript
var a = 'hello';

function print() {
   return data;
}

function exec() {
   var data = "world";
   a = a + " ";
   print(a + data)
}
~~~



### **빨리 반환해서 if문 중첩 없애기**

아래 코드는 중복된 if문을 어떻게 개선할 수 있나요?

~~~javascript
function foo(pobi,crong) {
  if(pobi) { 
    if(crong) {
      return true;
    }
  }
}
~~~

if문을 아래처럼 한다면 쉽게 중첩된 코드를 없앨 수 있습니다.

return문을 잘 쓰세요.

~~~javascript
function foo(pobi,crong) {
  if(!pobi) return;
  if(crong) {
    return true;
  }
}
~~~



### **전역변수를 줄이자**

자바스크립트에서 var키워드를 함수 안에서 사용하면, 그 변수는 함수안에서만 유용합니다.

이를 함수 scope라고 합니다.

하지만 함수 안에서 var 키워드를 사용하지 않으면 전역변수가 됩니다.

물론 함수 밖에서 var 키워드를 사용해서 변수를 선언해도 이건 전역변수입니다.

다양한 함수에서 같이 어떤 값을 공유하면서 사용해야 한다면 전역변수로 두고 쓸 수 있습니다.

이는 전역공간을 더럽히는 것으로 나중에 디버깅이 어려울 수 있습니다.

 

### **정적 분석 도구**

eslint와 같은 도구는 코드를 읽어서 잠재적인 문제와 anit-pattern을 알려줍니다.

이는 개발도구에서도 plugin을 연동해서 활용할 수가 있습니다.

내 코드가 어떤 문제가 없는지 확인해보세요.

