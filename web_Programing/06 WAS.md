# WAS



## WAS가 등장하게 된 배경

### 클라이언트 / 서버 구조

![05](https://user-images.githubusercontent.com/88477839/154940232-26f65d39-c1ec-45fe-8870-966aed8c0642.png)



+ 클라이언트는 서비스를 제공하는 서버에게 정보를 요청하여 응답 받은 결과를 사용한다.



### DBMS(database management system)

+ 다수의 사용자들이 데이터베이스 내의 데이터를 접근할 수 있도록 해주는 소프트웨어
+ DBMS가 등장하기 이전에는 개발자들이 파일에 데이터를 저장하고 읽어들이는 등의 기능을 모두 구현해야 했다. 이런 불편함을 해결하기위해 DBMS라는 소트프웨어가 등장하게 되었다.
+ MySQL, MariaDB, Oracle, PostgreSQL등 다양한 DBMS가 있다.
+ DBMS는 보통 서버 형태로 서비스를 제공하기 때문에 DBMS에 접속해서 동작하는 클라이언트 프로그램이 한때 많이 만들어졌었다.
+ 하지만 이러한 방식의 문제점은 클라이언트의 로직이 많아지고 클라이언트 프로그램의 크기가 커진다는 문제가 있었다. 프로그램 로직이 변경이 되면 클라이언트가 매번 배포되어야 한다는 문제가 있었고, 대부분의 로직이 클라이언트에 포함되어 배포가 되기 때문에 보안이 나쁘다는 문제가 있었다.



### 미들웨어( MiddleWare)

+ 위 문제를 해결하기 위해 등장하였다.(클라이언트 쪽에 비즈니스 로직이 많을 경우, 클라이언트 관리(배포 등)로 인해 비용이 많이 발생하는 문제가 발생)

  ![1_1_7_](https://user-images.githubusercontent.com/88477839/154941705-fa1c45fa-017b-4bbe-85a3-3adc67554ff1.png)

+ 비즈니스 로직을 클라이언트와 DBMS사이의 미들웨어 서버에서 동작하도록 함으로써 클라이언트는 입력과 출력만 담당하도록 함

+ 클라이언트는 단순히 요청만 중앙에 있는 서버에게 보내고 중앙에 있는 서버(미들웨어)에서는 대부분의 로직을 수행한다. 이때, 데이터를 조작할 일이 있으면 DBMS에게 부탁한다. 그리고 그 결과를 클라이언트에게 전송하면 클라이언트(웹 브라우저 등)는 그 결과를 화면에 보여주게 된다.



### WAS(Web Application Server)

+ WAS는 일종의 미들웨어로 웹 클라이언트(보통 웹 브라우저)의 요청 중 보통 웹 애플리케이션이 동작하도록 지원하는 목적을 가진다.

  ![1_1_7_was](https://user-images.githubusercontent.com/88477839/154942784-df7a99b3-74b4-4a87-a66a-be6fb10f5142.png)

+ WAS가 가진 중요한 기본 기능 세가지

  1. 프로그램 실행 환경과 데이터 베이스 접속 기능을 제공한다.
  2. 여러 개의 트랜잭션을 관리한다.
  3. 업무를 처리하는 비즈니스 로직을 수행한다.



### 웹 서버 vs WAS

+ WAS도 보통 자체적으로 웹 서버 기능을 내장하고 있다.
  + 웹 서버는 보통 정적인 콘텐츠를 웹 브라우저에게 전송하는 역할을 한다.
  + WAS는 프로그램의 동적인 결과를 웹 브라우저에게 전송하는 역할을 담당한다.
+ 현재는 WAS가 가지고 있는 웹 서버도 정적인 컨텐츠를 처리하는데 있어서 성능상 큰 차이가 없다.
+ 규모가 커질수록 웹 서버와 WAS를 분리한다. 그 목적은 장애 극복 기능(failover)인 경우가 많다.
+ 자원 이용의 효율성 및 장애 극복, 배포 및 유지보수의 편의성을 위해 웹서버와 WAS를 대체로 분리한다.
  + 웹 서버는 WAS 앞단에 위치시킨다. - 웹 서버는 상대적으로 WAS보다 간단한 구조로 만들어져 있다. WAS에서 동작하도록 만든 프로그램이 오작동해서 WAS 자체에 문제가 발생하는 경우가 있다.  이 경우, WAS를 재시작해야 되는 경우가 생기는데 이때 앞단의 웹 서버에서 먼저 해당 WAS를 이용하지 못하도록 하고 WAS를 재시작한다면 해당 웹 애플리케이션을 사용하는 사람은 WAS의 문제가 발생하였는지를 모르고 이용할 수 있다. 이런 처리를 장애 극복 기능이라고 하는데 대용량 웹 애플리케이션에는 무중단으로 운영하기 위해서 상당히 중요한 기능이다.

