# HTML 태그



## HTML 태그 소개 사이트

+ https://developer.mozilla.org/en-US/docs/Web/HTML/Element
+ http://html5doctor.com/element-index/
+ https://www.w3schools.com/tags/default.asp



## 제목과 단락요소

### 제목(heading) 태그

+ 제목(heading) 태그는 문서 내에 제목을 표현할 때 사용하는 태그입니다.

  태그 이름은 heading을 줄여서 h로 쓰며, 제목의 레벨에 따라서 <h1>~<h6>까지 있습니다.

  보통 <h1>은 해당 페이지를 대표하는 큰 제목으로 주로 사용되며, 숫자가 올라갈수록 조금 더 낮은 수준의 소제목을 나타내게 됩니다.

  하지만 현재 웹 페이지의 내용은 제목과 본문 식의 문서 형태보다는 주로 이미지나 그림처럼 시각적인 형태로 표현되고 있어서 제목 태그를 <h6>까지 쓰는 경우는 거의 없습니다.

  ~~~html
  <h1>역사</h1>
  <h2>개발</h2>
  1980년, 유럽 입자 물리 연구소(CERN)의 계약자였었던 물리학자 팀 버너스리가 HTML의 원형인 인콰이어를 제안하였다.
  ... 이하 생략
  <h2>최초 규격</h2>
  HTML 최초의 일반 공개 설명은 1991년 말에 버너스리가 처음으로 인터넷에서 문서를 "HTML 태그"(HTML tag)로 부르면서 시작되었다.
  ... 이하 생략
  ~~~



### 단락 태그

+ 단락(paragraph) 태그는 paragraph를 줄여서 p로 씁니다.

~~~html
<h1>역사</h1>
<h2>개발</h2>
<p>
    1980년, 유럽 입자 물리 연구소(CERN)의 계약자였었던 물리학자 팀 버너스리가 HTML의 원형인 인콰이어를 제안하였다.
    ... 이하 생략
</p>
<h2>최초 규격</h2>
<p>
    HTML 최초의 일반 공개 설명은 1991년 말에 버너스리가 처음으로 인터넷에서 문서를 "HTML 태그"(HTML tag)로 부르면서 시작되었다.
    ... 이하 생략
</p>
~~~



### 개행 - \<br\> 태그

+ \<p\>를 사용해서 단락으로 구분하면 자연스럽게 개행이 됩니다.그럼 \<p\> 내부에서 임의로 개행을 하려면 어떻게 해야 할까?

+ 개행을 위해 쓰이는 태그가 바로 \<br\>입니다. (linebreak 를 줄여서 br 이라고 합니다.)



## 텍스트 표현 태그

- \<b\> : bold 태그는 글자를 굵게 표현하는 태그입니다.
- \<i\> : italic 태그는 글자를 기울여서 표현하는 태그입니다.
- \<u\> : underline 태그는 글자의 밑줄을 표현하는 태그입니다.
- \<s\> : strike 태그는 글자의 중간선을 표현하는 태그입니다. (예전에 존재했던 strike 태그와는 다른 태그로, strike 태그는 폐기되어 더는 사용할 수 없습니다.)

~~~html
<p>
    <b>Lorem</b> <i>ipsum</i> dolor sit amet<br>
    <u>Lorem</u> <s>ipsum</s> dolor sit amet
</p>
~~~



## 앵커 요소

### 앵커(Anchor)

~~~html
<a href="http://www.naver.com/">네이버</a>

~~~

+ 링크를 만들기 위해 <a>는 반드시 href(hypertext reference) 속성을 가지고 있어야 합니다.
+ href 속성의 값은 링크의 목적지가 되는 URL입니다.

+ **target 속성**

  + target 속성은 링크된 리소스를 어디에 표시할지를 나타내는 속성이다.

  + 속성값으로는 \_self,\_blank,\_parent,\_top이 있다.

  + \_self는 현재 화면에 표시한다는 의미로, target 속성이 선언되지 않으면 기본적으로 self와 같이 동작한다.

  + \_blank는 새로운 창에 표시한다는 의미로 외부 페이지가 나타나게끔 하는 속성이다.

  + 예시

    ~~~html
    <a href="http://www.naver.com/" target="_blank">네이버</a>
    
    <!-- 새탭에서 링크가 열림 -->
    ~~~

+ **내부링크**

  + \<a\>를 통해 페이지 내부의 특정 요소로 초점을 이동할 수 있는데, 이를 내부 링크라고 한다.

  + 내부 링크를 사용할 때는 href 속성값에 #을 쓰고 그 뒤에 페이지 내에서 이동하고자 하는 요소의 Id 속성값을 적으면 된다.

  + 보통 페이지에서 내용이 많아 스크롤이 길어질 경우 빠르게 화면 최상단으로 이동하고자 할때 내부링크를 주로 사용하는데, 웹페이지에서 화면 하단에 있는 'top' 또는 '맨 위로 이동하기' 버튼이 이에 해당한다.

    ~~~html
    <a href="#some-element-id">회사 소개로 이동하기</a>
    
    ... 중략.
    
    <h1 id="some-element-id">회사 소개</h1>
    ~~~



## 의미가 없는 컨테이너요소

### 의미없이 요소를 묶기 위한 태그(Container)

~~~html
<div>
  <span>Lorem</span> ipsum dolor sit
</div>
~~~

+ 별다른 의미없이 다른 목적으로 필요할 때 사용한다.

+ Div : block-level
+ Span : inline-level



## 리스트 요소

### UL : unodered list로 순서가 없는 리스트

~~~html
콩나물국 레시피
<ul>
  <li>콩나물국</li>
  <li>파</li>
  <li>국 간장</li>
</ul>
~~~



### OL : ordered list로 순서가 있는 리스트

~~~html
콩나물국 레시피
<ol>
  <li>냄비에 국물용 멸치를 넣고 한소끔 끓여 멸치 육수를 7컵 만든다.</li>
  <li>콩나물을 넣고 뚜껑을 덮어 콩나물이 익을 때까지 끓인다.</li>
  <li>뚜껑을 열고 대파, 마늘, 고춧가루를 넣고 끓인다.</li>
</ol>
~~~



### DL

~~~html
<dl>
	<dt>리플리 증후군</dt>
  <dd>허구의 세계를 진실이라 믿고 거짓된 말과 행동을 상습적으로 반복하는 반사회적 성격장애를 뜻하는 용어</dd>
  <dd>리플리 증후군에 대한 또다른 설명입니다.</dd>
  <dt>피그말리온 효과</dt>
  <dd>타인의 기대나 관심으로 인하여 능률이 오르거나 결과가 좋아지는 현상</dd>
  <dt>언더독 효과</dt>
  <dd>사람들이 약자라고 믿는 주체를 응원하게 되는 현상</dd>
</dl>
~~~

+ Dl : description list로 용어를 설명하는 리스트
+ Dt : definition term로 용어를 구분한다.
+ dd : definition description로 용어의 정의를 나타낸다.



## 이미지 요소

### 이미지(IMAGE)

~~~html
<img src="./images/pizza.jpg" alt="피자">
~~~

+ 문서에 이미지를 삽입한다.
+ src : 이미지 경로를 지정한다.
+ alt :  이미지의 대체 텍스트를 입력한다. 
  + 대체 텍스트는 이미지를 설명하는 글을 이야기 한다. 
  + 일반적인 상황에서는 노출이 되지 않고, 웹 접근성 측면에서 상당히 중요한 속성이다.
+ Width / height : 이미지의 크기를 지정한다.



### 상대 경로와 절대 경로

src 속성에는 이미지의 경로가 들어가며, 이미지의 상대경로와 절대경로가 있습니다.

상대경로는 현재 웹 페이지를 기준으로 상대적으로 이미지의 위치를 나타내는 경로이고, 절대경로는 실제 그 이미지가 위치한 곳의 전체 경로입니다.

~~~html
<!-- 상대경로 -->
<img src="./images/pizza.png" alt="피자">

<!-- 절대경로 -->
<img src="C:/users/document/images/pizza.png" alt="피자">
<img src="http://www.naver.com/pizza.png" alt="피자">
~~~

+ ./ - 현재 페이지가 있는 폴더를 나타냄
+ ../ - 상위 폴더로 한번 이동하라는 명령임
+ folder/이미지파일 - 현재 폴더에서 하위 폴더로 갈때는 폴더명만 입력해주면 된다.



## 테이블 요소

### 표의 구성 요소

표는 셀(내용이 들어가는 하나의 칸)로 이루어져 있습니다.

표의 행(가로 방향)을 row, 열(세로 방향)을 column이라 합니다.

- <table> : 표를 나타내는 태그
- <tr> : 행을 나타내는 태그
- <th> : 제목 셀을 나타내는 태그
- <td> : 셀을 나타내는 태그

~~~html
<!-- 기본적인 테이블의 형태 -->

<head>
  <style>
      th, td { border: 1px solid; }
  </style>
</head>

<body>
  <table>
    <tr>
        <td>1</td>
        <td>2</td>
        <td>3</td>
        <td>4</td>
    </tr>
    <tr>
        <td>5</td>
        <td>6</td>
        <td>7</td>
        <td>8</td>
    </tr>
    <tr>
        <td>9</td>
        <td>10</td>
        <td>11</td>
        <td>12</td>
    </tr>
    <tr>
        <td>13</td>
        <td>14</td>
        <td>15</td>
        <td>16</td>
    </tr>
  </table>
</body>

~~~



<img width="862" alt="스크린샷 2022-03-15 13 05 23" src="https://user-images.githubusercontent.com/88477839/158304273-553189eb-ecf4-4bf9-b28c-c3b34d3bb81a.png">



<img width="749" alt="스크린샷 2022-03-15 13 12 26" src="https://user-images.githubusercontent.com/88477839/158305028-3f7484a7-3817-41b1-ad30-3ab3ac3fd0b8.png">



+ 실습

  ~~~html
  <!doctype html>
  <html lang="ko">
    <head>
      <meta charset="UTF-8">
      <title>테이블</title>
      <style>
        th,td { border: 1px solid; width: 50px; height:50px}
      </style>
    </head>
    <body>
      <table>
        <caption>Monthly Savings</caption>
        <thead>
        	<tr>
          	<th>Month</th>
            <th>Savings</th>
          </tr>
        </thead>
        <tfoot>
        	<tr>
          	<td colspan="2">sum</td>
          </tr>
        </tfoot>
        <tbody>
        	<tr>
          	<td>January</td>
            <td rowspan="2">$100</td>
          </tr>
          <tr>
          	<td>Fabruary</td>
          </tr>
        </tbody>
      </table>
    </body>
  </html>
  
  
  ~~~



### 테이블 기타 태그 및 속성

- <colgroup>
- <col>
- scope 속성
- header 속성



## 폼 요소

> 텍스트를 입력받거나 선택을 하게끔 하는등 사용자로부터 데이터를 받아야하는 경우 사용되는 요소들을 폼 요소라고 한다.



### INPUT 요소

대표적인 폼 요소로, 다양한 type 속성으로 여러 종류의 입력 양식으로 나타난다.

~~~html
<input type= " ">
~~~

+ text, password, radio, checkbox, tile, image, submit, reset, button...

+ https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input



#### type = "text"

~~~html
<input type="text" placeholder="영문으로만 써주세요">
~~~

+ placeholder 속성 : 사용자가 입력하기 전 미리 화면에 노출하는 값으로, 양식 가이드 역할을 한다.



#### type = "password"

~~~html
<input type="password">
~~~

+ 실제로 입력할 때 값이 노출되지 않는다.



#### type="radio"

~~~html
<input type="radio" name="gender" checked >남자
<input type="radio" name="gender">여자
~~~

+ 라디오 버튼은 중복 선택이 불가능 하며 하나의 항목만을 선택해야 하며, 하나를 선택하면 나머지들은 자동으로 선택취소가 된다.
+ name이라는 속성으로 같은 radio 버튼임을 명시해줘야 한다.(그렇지 않으면 중복선택이 가능하게 됨)
+ Checke 속성므로 미리 선택되어 있게끔 할 수도 있다.



#### type="checkbox"

~~~html
<input type="checkbox" name="hobby" checked>등산
<input type="checkbox" name="hobby">독서
<input type="checkbox" name="hobby">운동
~~~

+ 체크 박스는 중복선택이 가능하다.
+ radio와 마찬가지로 name 값을 동일하게해 묶어 줘야 한다.
+ Checked 속성으로 미리 선택되어 있게끔 할 수도 있다.



### type="file"

~~~html
<input type="file">
~~~

+ 파일을 서버에 올릴 때 사용한다.



#### type="submit" / type="reset"

~~~html
<form action="./text.html">
  메시지 : <input type="text" name="message"><br>
  <input type="submit" value="전송"><br>
  <input type="reset" value="취소">
</form>
~~~

+ submit : form의 값을 서버로 전송하는 버튼
+ reset : form의 값을 초기화 하는 버튼
+ Value 속성의 값으로 이름을 변경할 수 있다.
  + submit과 reset은 이름이 변경되더라도 기본으로 갖고 있는 동작은 변함이 없다.



#### type="button"

~~~html
<form action="./text.html">
  메시지 : <input type="text" name="message"><br>
  <input type="button" value="등록"><br>
</form>
~~~

+ button type은 아무런 기본 동작이 없다.
  + 그렇기 때문에 개발자가 직접 커스텀 해서 기능을 추가해줘야 할때 사용한다.



#### type="image"

~~~html
<form action="./text.html">
  메시지 : <input type="text" name="message"><br>
  <input type="image" src="" alt="로그인" width="100px" height="50px">
</form>
~~~

+ 기능은 submit과 동일하게 값을 전송하는 것임
+ 버튼의 모양에 이미지를 삽입할 수 있음



### SELECT 요소

> 선택 목록상자 (콤보박스) - 누르면 아래로 옵션들이 뜨는 것

~~~html
<select>
  <option>서울</option>
  <option selected>경기</option>
  <option>강원</option>
</select>
~~~

+ Selected 속성이 되어있으면 기본값으로 selected 된 옵션이 노출됨



### TEXTAREA요소

> 여러 줄 텍스트 입력 상자

~~~html
<textarea cols="30" rows="5" placeholder="자기소개는 짧게 해주세요">
</textarea>
~~~

+ \<input type="text"\> 는 한줄만 입력 받을 수 있었던 반면 textarea는 여러줄을 입력 받을 수 있음

+ cols(가로)와 rows(세로)로 박스 크기를 조절 할 수 있다.



### BUTTON 요소

~~~html
<button type=" ">...</button>
~~~

~~~html
<form action="./text.html">
  메시지 : <input type="text" name="message"><br>
  <button type ="submit">전송</button>
  <button type="reset">취소</button>
</form>
~~~

+ \<input type="submit">과 \<input type="reset">과 역할이 동일함.

+ button type="button"도 있음



### LABEL 요소

> 폼 컨트롤과 연결시켜주기 위함으로 웹 접근성 향상에 도움이 된다.(필수적)

~~~html
<label for="username">이름:</label>
<input type="text" id="username">

<label for="userlocation">사는곳:</label>
<select id="userlocation">
  <option>서울</option>
</select>

<label for="usercomment">하고 싶은말:</label>
<textarea id="usercomment">

</textarea>
~~~

+ 연결해주고자 하는 폼 요소의 id 속성의 값과 해당 label 요소의 for 속성의 값을 동일하게 적어줘야 한다.



### FIELDSET, LEGEND 요소

~~~html
<form>
  <fieldset>
    <legend>기본 정보</legend>
		<input type="...">
  </fieldset>
  
  <fieldset>
    <legend>부가 정보</legend>
		<input type="...">
  </fieldset>
</form>

~~~

+ \<fieldset> : 여러 개의 폼 요소를 그룹화하여 구조적으로 만들기 위해 사용
+ \<legend> : 폼 요소의 제목으로 \<fieldset> 요소 내부에 작성
  + \<fieldset> 태그로 묶인 그룹의 이름을 지정해주는 태그임
  + \<fieldset>요소에 가장 먼저 자식으로 선언되어야 함



### FORM 요소

> 폼 데이터를 그룹화하여 서버에 전송한다.

~~~html
<form action="..." method="...">
  
</form>
~~~

+ action : 폼 데이터를 처리하기 위한 서버의 주소
+ Method : 데이터를 전송하는 방식을 지정(get, post)
+ 폼 태그는  fieldset 요소까지 다 감싸줌