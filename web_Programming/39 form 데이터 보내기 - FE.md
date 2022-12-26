# form 데이터 보내기 

>회원가입이나 사용자 정보를 입력 후 서버로 보내야 하는 일은 자주 있다.
>
>로그인, 설문조사, 회원가입, 주문정보 입력 등 우리가 웹을 통해서 서버로 데이터를 보내야 하는 경우가 많이 있다.
>
>HTML은 form태그를 통해서 이를 지원한다.
>
>값 체크와 서버로 보내는 부분까지 실무에서 많이 사용되는 방법이다.



## form 데이터 보내기



### form 태그를 사용한 html

+ form 태그를 사용해서 사용자입력을 받을 수 있다.

+ 브라우저는 form태그를 사용해서 사용자 입력을 받으면 이를 쉽게 전송해준다.

  ~~~html
  <form action="/join" method="post">
    <div class="inputWrap">
      <div class="email">
        <span> Email </span> <input type="text" name="email"><br/>
      </div>
      <div class="password">
        <span> Password </span> <input type="passWord" name="password"><br/>
      </div>
    </div>
    <input class="sendbtn" type="submit">
  </form>
  ~~~

  + 위 예제코드처럼 input 태그를 사용해서 값을 입력받을 수 있으며, input 태그의 type에 따라서 다양한 입력을 받을 수가 있다.
  + input의 name은 키값이 된다.



### 어떻게 서버로 전송이 될까?

+ input type이 submit (혹은 button type이 submit) 인 엘리먼트가 있을 경우, 해당 엘리먼트를 클릭하거나 다른 폼 엘리먼트 요소에서 엔터를 치면 form에 입력한 정보가 자동으로 서버로 넘어간다.



### 그럼 어디로 전송이 될까?

+ form태그의 action속성에 적어주면 된다.



### 어떤 값이 전송될까?

+ Input 데이터가 합쳐져서 서버로 전송이 됩니다. POST방식과 GET방식을 우리가 이해해야 한다.

+ form으로 전송하는 데이터는 POST 방식으로 전송하는 게 일반적인 방법입니다.
+ GET은 우리가 브라우저 주소창에 입력하는 정보처럼 서버로 무언가를 요청하는 경우 default로 GET요청입니다.
+ 하지만 POST는 서버로 어떤 데이터를 보내줄 때 사용하는 HTTP Method입니다.
+ 이 두 개의 차이점을 좀 더 알아보면 좋습니다.
+ 이 상태에서 submit버튼을 누르면 /join으로 데이터를 보낼 수 있습니다.
+ 물론 서버에서 이요청을 받아서 처리하도록 routing 처리를 해야 할 겁니다.
+ 참고로 서버에서 하는 일을 짧게 요약하면 다음과 같습니다.
  + , '/join' 으로 request url이 탐지되면, 이제 클라이언트에서 보낸 데이터를 획득하고(request 객체에 담겨서 온 값) 그 값이 올바른지 확인하거나 아니면 DB에 그 값을 추가하는 등의 작업을 할 겁니다.
  + 이후에는 다시 클라이언트에 어떤 결과 페이지(html)를 만들어서 응답해야겠죠.
  + 예를 들어 '회원가입이다!'라고 하면 회원가입 정보를 받은 후에 회원가입 완료가 잘 됐다는 메시지 화면을 보내주거나, 아니면 서비스페이지의 메인화면으로 이동시켜줘야 할 겁니다.
  + 그런식으로 요청을 받은 후 응답(response)을 주는 행동을 서버가 해야 합니다.
  + 그러면 브라우저에서는 그 응답을 받아서 다시 화면을 새롭게 노출하게 되는 것이죠.



## form 데이터 유효성 검증하기

> form 데이터는 올바르지 않을 수 있다.
>
> 예를 들어 email정보를 입력하는 란에 '@' 문자가 빠졌다면 어떻게 될까?
>
> 사용자에게 에러 메시지를 잘 노출해야 할 것이다.
>
> 그런데 이 부분을 클라이언트에서 처리해야 할까? 아니면 백엔드에서 처리해야 할까??



### form 데이터 유효성 검증

~~~html
<form action="/join" method="post">
    <div class="inputWrap">
        <div class="email">
            <span> Email </span> <input type="text" name="email"><br/>
        </div>
        <div class="password">
            <span> Password </span> <input type="password" name="password"><br/>
        </div>
    </div>
    <input class="sendbtn" type="submit">
</form>
~~~

+ 위 form 코드에서 email 정보가 올바른지 유효성검증을 하고자 할때 이 부분 처리를 서버에서 한다면 사용자는 꽤 답답할 것이다.
  + 왜냐하면, 서버에 갈 때까지 email 정보가 틀렸는지 알 수가 없기 때문이다.
  + 예를 들어 다른 값을 모두 다 넣고 확인을 눌러서 서버로 데이터를 보냈는데, email 정보가 틀렸다고 메시지가 뒤늦게 나온다면 사용자는 당황할 것이다.
  + 좀 더 좋은 UX를 제공하기 위해서는 에러 메시지를 더 빨리 사용자에게 노출해주는 것이 좋다.
+ form 검증방법은 아래와 같이 구현할 수가 있다.

~~~html
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name= "description" content="">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>join!</title>
    <link rel="sytlesheet" href="/css/ui.css">
  </head>
  <body>
    <h1>join my website!</h1>
    <div class="formWrap">
      <form action="/join" method="post" id="myform">
        <div class="inputWrap">
          <div class="email">
            <span>Email</span> <input type="text" name="email"><br/>
          </div>
          <div class="password">
            <span>Password</span> <input type="password" name="password"><br/>
          </div>
        </div>
        <input class="sendbtn" type="submit">
      </form>
    </div>
    
    <section class="result"></section>
    <script>
    	var btn = document.querySelector(".sendbtn");
      var result = document.querySelector(".result");
      btn.addEventListener("click", function(evt){
        evt.preventDefault(); // 디폴트 행동을 막아준다. 여기 코드에서 디폴트 행동은 submit을 클릭했을때 자동으로 브라우저가 이벤트가 발생해서 action으로(서버로) 날라가는 행위를 말함
        console.log("clicked");
        var emailValue = document.querySelector("[name='email']").value;
        var bValid = (/^[\w+_]\w+@\w+\.\w+$/).test(emailValue); // 정규표현식 사용
        if(!bValid){
          result.innerHTML = "올바르지 않은 이메일입니다";
        } else {
          result.innerHTML="이메일 정보가 올바릅니다.";
          document.querySelector("#myform").submit(); // 이렇게 코드를 적어주면 서버로 날라감
        }
      });
    </script>
  </body>
</html>
~~~

+ 위 코드에서는 addEvnetListener에서 click이벤트를 사용했지만 다른 방법도 있다.

+ 'submit' 이벤트를 통해서 역시 동일하게 form체크와 데이터 전송을 할 수 있다.

  ~~~javascript
  document.querySelector("#myform").addEventListener("submit",function(evt){
    console.log(evt.target);
  });
  ~~~



+ form 데이터 관련해서 input 값이 변경되면 발생하는, change 이벤트 타입이 있습니다. 이를 이용하면 더 빨리 form 값이 올바른지 검증할 수가 있습니다. 이를 사용해서 form 검증을 하면 좀 더 세련된 방법으로 할 수 있을 겁니다. 생각뿐 아니라 이번엔 꼭 이를 구현해보세요!

  ~~~javascript
  nameElememnt.addEventListener("change", function(evt) {...});
  ~~~

  

