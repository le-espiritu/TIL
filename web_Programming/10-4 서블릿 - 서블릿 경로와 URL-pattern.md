# 10-4 서블릿 경로와 URL-pattern

### 참고 자료

- 서블릿 가상경로 - https://lordofkangs.tistory.com/35
  - 서블릿 매핑을 통해 가상경로(url-pattern)을 설정할 수 있다.
  - 서블릿은 내부 자원이고 외부에서 접근이 불가능해야 한다.
  - 따라서 서버만이 서블릿의 진짜 경로를 통해 서블릿에 접근할 수 있고, 외부에서는  일반적인 경로(진짜 경로)를 통해서는 서블릿에 접근 할 수 없다.
  - 외부에서 서버에 요청을 하기 위해 서블릿 접근이 필요한 경우에는 가상경로를 활용하면 된다.
    -  서블릿 매핑으로 설정된 url-pattern이 가상경로가 된다.
  - 진짜 경로는 오직 서버만이 접근 가능하기 때문에 외부로 부터 서버의 로직을 보호한다.



+ 서블릿 url-pattern - https://windorsky.tistory.com/entry/%EC%84%9C%EB%B8%94%EB%A6%BF-URL-pattern

  + URL-pattern은 서블릿 매핑으로 사용되는 가상의 이름(가상의 경로) 이다.

  + 클라이언트가 브라우저에 요청할 때 사용된다.

  + 반드시 /(슬래시)로 시작해야 한다.

    + ex) /test
    + ex) /first

  + Html 이나 jsp 의 form 태그에서 사용할때

    + ~~~html
      <form method="post" action="test"> 
      ~~~

    + ~~~html
      <form method="post" action="first"> 
      ~~~



+ 디폴트 서블릿 참고 자료 - https://pangtrue.tistory.com/108