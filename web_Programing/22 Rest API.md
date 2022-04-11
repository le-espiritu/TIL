# Rest API

> 클라이언트의 종류가 웹 브라우저, 안드로이드 앱, IOS 앱 등 다양해지면서 이러한 클라이언트들에게 정보를 제공하는 방식을 하나로 일원하시키고 싶어졌습니다.
>
> 일원하시키는 방식 중에 대표적인 방식이 HTTP 프로토콜로 API를 제공하는 것이다.
>
> **HTTP프로토콜로 제공하는 API를 REST API라고 한다.**





### API란?

+ API는 Application Programming Interface의 약자입니다.

+ wiki를 보면 API에 대한 설명이 다음과 같이 되어 있습니다.

  + “API(Application Programming Interface, 응용 프로그램 프로그래밍 인터페이스)는 응용 프로그램에서 사용할 수 있도록, 운영 체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스를 뜻합니다.

    주로 파일 제어, 창 제어, 화상 처리, 문자 제어 등을 위한 인터페이스를 제공합니다.

+ 설명만 봐서는 어떤 뜻인지 이해하기 어렵네요.

  이번엔 다음의 URL주소를 보도록 하겠습니다.

+ [참고 바로가기](https://docs.oracle.com/javase/8/docs/api/)

+ 위의 URL주소를 가면 java 8의 API문서를 볼 수 있습니다.

​		자바 언어가 제공하는 클래스와 인터페이스에 대한 설명이 API문서입니다. 

​		자바 프로그래밍을 위해서는 자바 언어가 제공하는 것들이 어떤 것이 있는지를 알아야 합니다.

​		그래야, 사용할 수 있겠죠?

​		절대값을 구하기 위해서는 어떻게 해야 할까요?

​		Java API문서를 읽어보면 답을 알 수 있습니다.

​		Math클래스의 abs()메소드를 사용하면 된다는 것을 알 수 있죠.

​		해당 메소드가 어떻게 내부적으로 구현되어 있는지는 문서를 봐도 알 수 없습니다.

​		**하지만, 해당 라이브러리를 사용할 때 구현코드를 알지 못해도 인터페이스만 알면 사용할 수 있습니다.**

+ **이렇게 프로그래밍을 할 때 필요한 인터페이스를 API라고 합니다.**



### REST API란?

+ REST는 Representational State Transfer라는 용어의 약자로서 2000년도에 로이 필딩 (Roy Fielding)의 박사학위 논문에서 최초로 소개되었습니다.

+ REST API란 말 그대로 REST형식의 API를 말합니다. 

+ REST API란 핵심 컨텐츠 및 기능을 외부 사이트에서 활용할 수 있도록 제공되는 인터페이스입니다.

+ 예를 들어, 네이버에서 블로그에 글을 저장하거나, 글 목록을 읽어갈 수 있도록 외부에 기능을 제공하거나 우체국에서 우편번호를 조회할 수 있는 기능을 제공하거나, 구글에서 구글 지도를 사용할 수 있도록 제공하는 것들을 말합니다.



<img width="1008" alt="001" src="https://user-images.githubusercontent.com/88477839/158933944-9e40c85e-7ade-43bc-a7e5-9f952e9d6261.png">

+ 웹 브라우저 뿐만 아니라 앱 등 다양한 클라이언트가 등장하면서 그러한 클라이언트들에게 대응하기 위해 REST API가 널리 사용되기 시작하였습니다.

+ 서비스 업체들이 다양한 REST API를 제공함으로써, 클라이언트는 이러한 REST API들을 조합한 어플리케이션을 만들 수 있게 되었습니다.

+ 이를 매시업(Mashup)이라고 합니다.

+ 다음은 유명 사이트에서 제공하는 API 관련된 문서 링크입니다.

  - [네이버 API 소개 바로가기](https://developers.naver.com/products/intro/plan/)

  - [페이스북의 그래프 API 문서 바로가기](https://developers.facebook.com/docs/graph-api)

  - [공공 데이터 포털 바로가기](https://www.data.go.kr/)



### **이것은 REST가 아니다.**

+ 이렇게 REST API가 널리 사용되었지만, REST를 논문으로 최초 소개한 로이필딩은 대부분의 REST API라고 하는 것들이 REST API가 아니라고 말합니다.

+ REST는 다음과 같은 스타일을 반드시 지켜야 한다고 말합니다.

  - client-server

  - stateless

  - cache

  - uniform interface

  - layered system

  - code-on-demand (optional)

+ 여기서 스타일이란 제약조건의 집합을 의미합니다.

  

+ 즉, 위에서 언급한 내용을 잘 지켜야만 REST라고 말할 수 있다는 의미입니다.

+ HTTP프로토콜을 사용한다면 client-server, stateless, cache, lared system, code-on-demand 등에 대해서는 모두 쉽게 구현 가능합니다.

+ 하지만, 문제는 uniform interface입니다.

+ uniform interface의 스타일

  - 리소스가 URI로 식별되야 합니다.

  - 리소스를 생성,수정,추가하고자 할 때 HTTP메시지에 표현을 해서 전송해야 합니다.

  - 메시지는 스스로 설명할 수 있어야 합니다. (Self-descriptive message)

  - 애플리케이션의 상태는 Hyperlink를 이용해 전이되야 합니다.(HATEOAS)

+ 첫 번째와 두 번째 항목은 지키기 어렵지 않은데, 메시지가 스스로 설명할 수 있어야 하는 부분과 HATEOAS를 지원하는 것은 웹과는 다르게 API로는 쉽지가 않습니다.

+ 응답 결과에 보통 JSON 메시지를 사용하게 되는데, 이 JSON메시지가 어디에 전달되는지 그리고 JSON메시지를 구성하는 것이 어떤 의미를 표현해야만 메시지 스스로 설명할 수 있다고 말할 수 있는데, 그게 쉽지 않습니다.

+ 우리가 웹 게시판을 사용할 때, 리스트 보기를 보면, 상세보기나 글쓰기로 이동할 수 있는 링크가 있습니다.

+ 상세보기에서는 글 수정이나 글 삭제로 갈 수 있는 링크가 있습니다.

+ 이렇게 웹 페이지를 보면, 웹 페이지 자체에 관련된 링크가 있는것을 알 수 있는데 이를 HATEOAS라고 말합니다.

+ 이런 HATEOAS를 API에서 제공하는 것은 쉽지 않습니다.



### **REST API는 쉽지 않다. 그래서, 보통은 Web API(혹은 HTTP API)를 사용한다.**

+ REST의 uniform interface를 지원하는 것은 쉽지 않기 때문에, 많은 서비스가 REST에서 바라는 것을 모두 지원하지 않고 API를 만들게 됩니다.

  - REST의 모든 것을 제공하지 않으면서 REST API라고 말하는 경우도 있습니다.

  - REST의 모든 것을 제공하지 않고 Web API 혹은 HTTP API라고 부르는 경우가 있습니다.

+ 우리는 2번째 방식을 따르려고 합니다.



### 참고 자료

+ https://meetup.toast.com/posts/92
+ REST API 유튜브 설명 - https://www.youtube.com/watch?v=RP_f5dMoHFc