# 34-2 post 방식으로 페이지 이동하기



### 1. HTML의 form 이용

~~~html
<form name="myForm" action="http://httpbin.org/post" method="POST">
  <input type="hidden" name="some_key1" value="some_value1" />
  <input type="hidden" name="some_key2" value="some_value2" />
</form>
~~~



### 2. JavaScript 함수 이용

~~~html
<script>
    function page_move(url, some_data) {
        var form = document.createElement("form");
        var parm = new Array();
        var input = new Array();

        form.action = url;
        form.method = "post";


        parm.push( ['some_key1', 'some_value1'] );
        parm.push( ['some_key2', 'some_value2'] );
        parm.push( ['some_data', some_data] );


        for (var i = 0; i < parm.length; i++) {
            input[i] = document.createElement("input");
            input[i].setAttribute("type", "hidden");
            input[i].setAttribute('name', parm[i][0]);
            input[i].setAttribute("value", parm[i][1]);
            form.appendChild(input[i]);
        }
        document.body.appendChild(form);
        form.submit();
    }
</script>

<a href="#" onclick="javascript:page_move('http://httpbin.org/post', 'foobar');">click</a>
~~~

참고 출처 - https://xn--lg3bt3ss6d.com/9

### 3. a 태그에서 Post 방식으로 페이지 이동하기

+ Html

  ~~~java
  <a href="javascript:void(0)" onClick="javascript:goPost()">클릭시 POST로 페이지 </a>
  ~~~

+ js

  ~~~javascript
  function goPost(){
      let f = document.createElement('form');
      f.setAttribute('method', 'post');
      f.setAttribute('action', 'goPost.do');
      document.body.appendChild(f);
      f.submit();
  }
  ~~~

+ Java( Controller )

  ~~~java
  @PostMapping("goPost.do")
  public String go_post(){
      return "post";
  }
  ~~~

참고 출처 - https://amongthestar.tistory.com/178



### 4. a 태그에서 인수를 post 방식으로 보내는 방법

~~~html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>a태그 form 전송</title>
    <script>
    // content, cate, index를 인수로 받아 form 태그로 전송하는 함수
    function goPage(content, cate, index = 0) {
      // name이 paging인 태그
      var f = document.paging;

      // form 태그의 하위 태그 값 매개 변수로 대입
      f.content.value = content;
      f.cate.value = cate;
      f.index.value = index;

      // input태그의 값들을 전송하는 주소
      f.action = "./content.do"

      // 전송 방식 : post
      f.method = "post"
      f.submit();
    };
    </script>
  </head>
  <body>
    <!-- 페이지 전송 폼 -->
    <form name="paging">
    	<input type="hidden" name="content"/>
    	<input type="hidden" name="index"/>
    	<input type="hidden" name="cate"/>
    </form>
    <!-- a태그로 인수 전달 -->
    <a href="javascript:goPage('look', 'observation', 1);">관람</a>
  </body>
</html>
~~~

 참고 출처 - http://gnujava.com/board/article_view.jsp?board_no=3&article_no=8502