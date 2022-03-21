# CSS



### Cascading Style Sheets

+ cascading - 연속적으로 계속되는 이라는 의미
+ style sheets - 스타일 규칙
+ css는 html(마크업 언어)을 꾸며주는 언어
+ html이 웹페이지의 정보를 표현한다면 css는 html을 보기 좋게 디자인 하는 역할



### CSS 문법

~~~css
h1 { color: yellow; font-size:2em; }

~~~

- 선택자(selector) - h1
- 속성(property) - color , font-size
  - html의 속성은 attribute
  - css의 속성은 property
- 값(value) - yellow , 2em
- 선언(declaration) - "color: yellow", "font-size: 2em"
- 선언부(declaration block) - { color: yellow; font-size:2em; }
- 규칙(rule set) - h1 { color: yellow; font-size:2em; }
  - css는 여러 개의 규칙, 롤 셋들로 이루어져 있다.



### css 주석

~~~css
/* 내용 */
~~~



### CSS 적용방식

+ inline - 해당 요소에 직접 스타일 속성을 이용해서 규칙들을 선언하는것

  ~~~css
  <div style="color:red;"> 내용 </div>
  ~~~

+ internal - \<style> 태그를 활용해서 셀렉터를 활용한 방법

  ~~~css
  <style> 
  	div {color: red;} 
  </style>
  ~~~

+ External - 외부 스타일 시트, 별도의 CSS파일을 만들어서 활용.

  ~~~html
  <link rel="stylesheet" href="css/style.css">
  ~~~

  + rel - 연결되는 파일이 이 html문서와 어떤 관계인지를 명시하는 속성
  
  ~~~css
  div {color: red;}
  ~~~
  
  + 외부 스타일 시트 파일을 이용해야 관라히기도 용이하고 용량도 훨씬 적음
  
+ @import

  ~~~css
  @import url("css/style.css");
  ~~~

  + 스타일 내에서 다른 스타일 시트 파일을 불러오는 것.
  + \<style> 태그 내부 상단이나
    + 외부 스타일 시트 파일 안 상단에 선언한다.
+ 여러 이유로 성능이 좋지 않기 때문에 거의 쓰이지 않음



## 선택자(SELECTOR)



### 요소 선택자

+ 선택자 중에서 가장 기본이 되는 선택자이며, 태그 선택자라고도 한다.

~~~css
h1 { color: yellow; }
h2 { color: yellow; }
h3 { color: yellow; }
h4 { color: yellow; }
h5 { color: yellow; }
h6 { color: yellow; }
~~~



### 그룹화

+ 선택자

  ~~~css
  h1, h2, h3, h4, h5, h6 { color: yellow; }
  ~~~

+ 전체 선택자 - 성능에는 좋지 않으므로 사용을 가급적 지양

  ```css
  * { color: yellow; }
  ```

+ 선언

  ```css
  h1 { color: yellow; font-size: 2em; background-color: gray; }
  ```

+ 선택자& 선언

  ```css
  h1, h2, h3, h4, h5, h6 { color: yellow; font-size: 2em; background-color: gray; }
  ```

  

### CLASS 선택자

+ 요소에 구애 받지 않고 스타일 규칙을 적용할 수 있는 가장 일반적인 방법

+ css

  ~~~css
  .foo { font-size: 30px; }
  ~~~

+ HTML

  ~~~html
  <p class="foo"> ... </p>
  ~~~



### 다중 CLASS

+ 하나의 요소에 여러 개의 class를 넣을 수 있다.

+ css

  ~~~css
  .foo{font-size:30px;}
  .bar{color:blue;}
  ~~~

+ Html

  ~~~css
  <p class="foo bar"> ... </p>
  ~~~



### ID 선택자

+ 문서내에 유일한 요소에 사용
+ 구체성

+ css

  ~~~css
  #bar{ background-color:yellow; }
  ~~~

+ Html

  ~~~html
  <p id="bar"> ... </p>
  ~~~

  

### 선택자의 조합

~~~css
/* 요소와 class의 조합 - 이 경우에는 <p>이면서 class 속성에 bar가 있어야 적용 */
p.bar { ... }

/* 다중 class - class 속성에 foo와 bar가 모두 있어야 적용 */
.foo.bar { ... }

/* id와 class의 조합 - 이 경우에는 id가 foo이며 class가 bar인 요소에 적용*/
#foo.bar { ... }
~~~



### 속성 선택자

+ 단순 속성으로 선택
+ 정확한 속성값으로 선택
+ 부분 속성값으로 선택



### 단순 속성으로 선택

~~~css
p[class] {color:silver;} /* 대괄호 안에 속성의 이름이 들어감 */
p[class][id] {text-decoration :underline;}
~~~

~~~html
<p class="foo">Hello</p>
<p class="bar">CSS</p>
<p class="bas" id="title">HTML</p>
~~~



### 정확한 속성값으로 선택

~~~css
p[class="foo"] {color:silver;}
p[id="title"] {text-decoration : underline;}
~~~

~~~html
<p class="foo">Hello</p>
<p class="bar">CSS</p>
<p class="bas" id="title">HTML</p>
~~~



### 부분 속성값으로 선택

- [class~="bar"] : class 속성의 값이 공백으로 구분한 "bar" 단어가 포함되는 요소 선택
- [class^="bar"] : class 속성의 값이 "bar"로 시작하는 요소 선택
  - 단어와 상관없이 bar라는 글자로 시작하면 적용됨.
- [class$="bar"] : class 속성의 값이 "bar"로 끝나는 요소 선택
- [class*="bar"] : class 속성의 값이 "bar" 문자가 포함되는 요소 선택
  - 단어와 상관없이 bar라는 글자가 포함되어 있으면 적용됨

~~~css
p[class~="color"] { font-style: italic; }
p[class^="color"] { font-style: italic; }
p[class$="color"] { font-style: italic; }
p[class*="color"] { font-style: italic; }
~~~

~~~html
<p class="color hot">red</p>
<p class="cool color">blue</p>
<p class="colorful nature">rainbow</p>
~~~



## 문서 구조 관련 선택자

> 선택자 중에는 문서의 구조를 이용하여 요소를 선택하는 선택자도 있습니다.
>
> 문맥이나 요소의 구조를 기반으로 하여 선택자를 조합하는 것을 "조합자" 또는 "결정자" 라고 부릅니다.



### 부모와 자식 관계 이해하기

~~~html
<html>
  <head>
    
  </head>
  <body>
    <div>
      <h1><span>HTML</span>:Hyper Text Markup Language</h1>
      <span>CSS는 문서를 꾸며줍니다.</span>
    </div>
    <span>Javascript는 문서를 동적으로 제어할 수 있습니다.</span>
    <p>HTML과 CSS와 JAVASCRIPT를 이용해서 멋진 웹 사이트를 제작할 수 있습니다.</p>
  </body>
</html>
~~~

+ \<html>요소는 \<head> 요소와 \<body> 요소. 두 개의 자식 요소가 있다.
+ \<body> 요소에는 \<div>, \<span>, \<p> 요소. 세 개의 자식 요소가 있다.
  + \<bdoy>요소의 부모 요소는 \<html>이다.
+ \<div>요소는  \<h1>, \<span> 요소가 자식요소로 있다.
  + 부모 요소는 \<body>
+ \<h1>요소도 \<div>요소의 자식 요소이자, \<span> 요소의 부모 요소이다.



### **조상과 자손**

+ 조상과 자손의 관계는 부모와 자식의 관계와 비슷합니다.
+ 정확히 얘기하면 부모와 자식의 관계를 포함한 확장된 관계라고 생각하면 됩니다.
+ 조상 요소는 그 요소를 포함하는 모든 요소로, 부모 요소를 포함하여 여러 개일 수도 있습니다.
+ 자손 요소는 그 반대로, 그 요소가 포함하고 있는 모든 요소가 자손 요소입니다.



### 형제

+ 같은 부모를 가지고 있는 요소들은 서로 형제 관계에 있습니다.
+ 형제 중에는 인접한 관계도 있다.
  + 형제 관계에 있는 요소들 중에 바로 뒤에 이어 나오는 요소를 인접해 있다고 한다.
  + 위 코드에서 \<span> 요소가 \<div>요소의 형제 요소이자 인접한 형제 요소이다.



### 문서 구조를 이용한 선택자

+ 자손 선택자
+ 자식 선택자
+ 인접 형제 선택자



### 자손 선택자

~~~css
div span{color : red;}
~~~

+ 선택자와 선택자 사이에 아무런 기호 없이 공백으로 구분해주면 된다.
+ 위 선택자는 \<div>요소의 자손, \<span> 요소를 선택하는 선택자



### 자식 선택자

~~~css
div > span {color:red;}
~~~

+ 자식 선택자는 선택자 사이에 꺽쇠(>)기호를 넣어주면 된다.



### 인접 형제 선택자

~~~css
div + span {color:red;}
~~~

+ 선택자 사이에 +(plus) 기호를 쓰면 된다.



### 조합해서 사용

~~~css
body > div table +ul {...}
~~~

+ body 요소의 자식인 div요소의 자손인 table 요소 바로 뒤에 인접한 ul 요소 선택



## 가상 선택자



