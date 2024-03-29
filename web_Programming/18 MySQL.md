# MySQL



### 데이터베이스와 데이터베이스 관리 시스템

+ 데이터 - 책
+ 데이터베이스(DB) - 책들이 모여있는 것
+ 데이터베이스 관리 시스템(DBMS) - 책들을 관리해주는 엄마



### 데이터베이스의 기본개념 (정의)

+ 데이터의 집합 ( a Set of Data)
+ 여러 응용 시스템(프로그램)들의 통합된 정보들을 저장하여 운영할 수 있는 공용(share)데이터의 집합
+ 효율적으로 저장, 검색, 갱신할 수 있도록 데이터 집합들끼리 연관시키고 조직화 되어야 한다.



### 데이터베이스의 특성

+ 실시간 접근성(Real-time Accessability)
  + 사용자의 요구를 즉시 처리할 수 있다.
+ 계속적인 변화(Continuous Evolution)
  + 정확한 값을 유지하려고 삽입,삭제,수정 작업등을 이용해 데이터를 지속적으로 갱신할 수 있다.
+ 동시 공유성(Concurrent Sharing)
  + 사용자마다 서로 다른 목적으로 사용하므로 동시에 여러 사람이 동일한 데이터에 접근하고 이용할 수 있다.
+ 내용 참조(Content Reference)
  + 저장한 데이터 레코드의 위치나 주소가 아닌 사용자가 요구하는 데이터의 내용, 즉 데이터 값에 따라 참조할 수 있어야 한다.



### 데이터 베이스 관리 시스템 (Database Management System = DBMS)

+ 데이터베이스를 관리하는 소프트웨어
+ 여러 응용 소프트웨어(프로그램) 또는 시스템이 동시에 데이터베이스에 접근하여 사용할 수 있게 한다.
+ 필수 3기능
  + 정의기능 : 데이터 베이스의 논리적, 물리적 구조를 정의
  + 조작기능 : 데이터를 검색, 삭제, 갱신, 삽입, 삭제하는 기능
  + 제어기능 : 데이터베이스의 내용 정확성과 안전성을 유지하도록 제어하는 기능
+ Oracle,SQL Server, MySQL,DB2등의 상용 또는 공개 DBMS가 있다.



### 데이터 베이스 관리 시스템 장/단점

+ 장점
  + 데이터 중복이 최소화
  + 데이터의 일관성 및 무결성 유지
  + 데이터 보안 보장
+ 단점
  + 운영비가 비싸다
  + 백업 및 복구에 대한 관리가 복잡
  + 부분적 데이터베이스 손실이 전체 시스템을 정지



## MySQL 실행하기

### Mac에서 실행하기

+ HomeBrew를 이용해서 MySQL을 설치했다면, 실행과 중지가 상당히 간편하다. 환경 변수 설정 등이 모두 자동으로 이뤄지기 때문이다.

+ MySQL 서버 실행하기

  + mysql서버를 실행하려면 터미널을 실행한 후 아래와 같은 명령을 실행한다.

    ~~~
     mysql.server start
    ~~~

  + 위 명령을 내리면 아래와 같은 메시지가 실행된다

    ~~~
    Starting MySQL
    . SUCCESS!
    ~~~

+ MySQL을 데몬으로 실행하기

  + 데몬으로 실행하면 운영체제에 로그인 하여 사용자가 실행시키지 않아도, 자동으로 실행되기 때문에 
    서버가 재부팅 될 때마다 별도로 실행 할 필요가 없으므로 편리 하다.

  + 운영체제의 백그라운드로 MySQL이 계속 실행되도록 하고 싶다면 HomeBrew가 제공하는 명령을 이용하면 된다.

  + HomeBrew로 다음과 같이 mysql 데몬을 실행한다.

  + 아래와 같이 명령을 수행하면 간단하게 mysql을 데몬형태로 실행할 수 있다.

    ~~~
    brew services start mysql
    ~~~

  + 서비스 재시작도 HomeBrew가 제공하는 명령을 이용하면 된다.

  + 아래와 같이 명령을 수행하면 된다.

    ~~~
    brew services restart mysql
    ~~~

  + 데몬으로 실행되고 있는 프로그램들이 궁금하다면 아래와 같이 명령을 실행하면 된다.

    ~~~
    brew services list
    ~~~



## MySQL 종료

### 맥에서의 MySQL종료하기

~~~
mysql.server start
~~~

+ 터미널에서 위의 명령으로 서버를 실행했다면

~~~
mysql.server stop
~~~

+ 위의 명령으로 서버를 종료할 수 있다.

~~~
Shutting down MySQL

.. SUCCESS!
~~~

+ MySQL이 종료되면서 위와 같은 메시지가 보여진다.
+ 만약 HomeBrew를 이용해서 다음과 같이 데몬으로 실행했다면

~~~
brew services start mysql
~~~

+ 아래와 같은 명령으로 데몬 형태로 실행되고 있는 MySQL을 종료할 수 있다.

~~~
brew services stop mysql
~~~



## mysql 비밀번호 변경하기

~~~
mysql.server stop
~~~

+ 위 명령어로 기존에 실행되고 있던 mysql 서버를 종료한다.

~~~
sudo mysqld_safe --skip-grant-tables
~~~

+ 위 명령어를 사용하여 안전모드로 mysql 서버를 실행한다.
+ 위와 별개로 새로운 터미널을 하나 더 연다 (command + t)

~~~
mysql -u root
~~~

+ 위 명령어를 사용하여 비밀번호 없이 mysql에 접속한다.

 ~~~
 udate mysql.user set authentication_string=null where user='root';
 flush privileges;
 exit;
 ~~~

+ 위 명령어를 사용하여 root 계정의 비밀번호를 없애주고 mysql을 빠져나온다.

~~~
mysql -u root
alter user 'root'@'localhost' identified with caching_sha2_password by'바꿀 비밀번호';
flush privileges;
exit
~~~

+ 다시 root 계정으로 mysql에 접속하고
+ Root 계정의 비밀번호를 설정해주고 빠져나온다.

~~~
mysql.server stop
~~~

+ mysql 서버를 종료해주고

~~~
mysql.server start
~~~

+ mysql 서버를 시작하여준다.



+ 참고 - https://kwangkyun-world.tistory.com/entry/mysql-80-%ED%8C%A8%EC%8A%A4%EC%9B%8C%EB%93%9C-%EB%B3%80%EA%B2%BD-%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8-%EC%B4%88%EA%B8%B0%ED%99%94-mac
