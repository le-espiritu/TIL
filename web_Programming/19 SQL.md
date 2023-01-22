# SQL



## SQL이란?

> DBMS에 명령을 내리는 언어를 SQL이라고 한다.



### SQL(Structured Query Language)

+ SQL은 데이터를 보다 쉽게 검색하고 추가, 삭제, 수정같은 조작을 할 수 있도록 고안된 컴퓨터 언어이다.
+ 관계형 데이터베이스에서 데이터를 조작하고 쿼리하는 표준 수단이다.

+ **DML (Data Manipulation Language) : 조작어**
  + 데이터를 조작하기 위해 사용한다.
  + Insert, Update, Delete, Select 등이 여기에 해당한다.
+ **DDL (Data Definition Language) : 정의어**
  + 데이터베이스의 스키마를 정의 하거나 조작하기 위해 사용한다.
    + 데이터베이스 내에 어떤 구조로 데이터가 저장되는지를 나타내는 데이터베이스 구조를 스키마라고 칭한다.
  + Create, Drop, Alter 등이 여기에 해당한다.
+ **DCL (Data Control Language) : 제어어**
  + 데이터를 제어하는 언어이다.
  + 권한을 관리하고, 데이터의 보안, 무결성등을 정의한다.
  + Grant, revoke 등이 여기에 해당한다.
+ 기본적인 *SQL*문을 *CRUD*(Create Read Update Delete)라고 부릅니다



### mysql DBMS 접속하기

+ 콘솔에다 다음과 같이 명령을 실행한다. 

  ~~~
  mysql -uroot -p
  ~~~

  + MySQL 관리자 계정인 root로 데이터베이스 관리 시스템에 접속하겠다는 것이다.
  + uroot : 유저는 root이다 라는 의미
  + -p 옵션은 패스워드 옵션
  + window 사용자는 설치시에 입력했던 암호를 입력한다. 맥사용자는 암호가 없으니 그냥 엔터를 입력하면 된다.(brew services start mysql 로 실행했을때)
    + (나중에 root 계정 암호 생성함 비밀번호는 root)



### Dagabase 생성하기

+ 관리자 계정으로 MySQL에 접속했다면, 다음과 같은 명령으로 데이터베이스를 생성한다.

  + 데이터베이스를 생성할 수 있는 유저는 권한이 따로 있는데, root 유저는 다 가능함

  ~~~
   mysql> create database DB이름;
  ~~~

+ 우리는 다음과 같은 명령을 실행하여 DB이름을 "connectdb"로 생성하도록 하겠다.

  ~~~
  mysql> create database connectdb;
  ~~~

  

### Database 사용자 생성과 권한 주기

+ Database를 생성했다면, 해당 데이터베이스를 사용하는 계정을 생성해야 한다. 또한, 해당 계정이 데이터베이스를 이용할 수 있는 권한을 줘야 한다. 아래와 같은 명령을 이용해서 사용자 생성과 권한을 줄 수 있다.

  + db이름 뒤의 *는 모든 권한을 의미한다. 
  + @'%' 는 어떤 클라이언트에서든 접근가능하다는 의미이다.
  + @'localhost'는 해당 컴퓨터에서만 접근가능하다는 의미이다.

+ flush privileges는 DBMS에게 적용하라는 의미이다. 해당 명령을 반드시 실행해줘야 한다.

  ~~~
  grant all privileges on db이름.* to 계정이름@'%' identified by ＇암호’;
  grant all privileges on db이름.* to 계정이름@'localhost' identified by ＇암호’;
  flush privileges;
  ~~~

  + Grant - 권한을 주는 명령어이다.

+ 실습에서 사용자 계정이름은 'connectuser', 암호는 'connect123!@#'로 하겠다.

  + 해당 사용자가 사용하는 데이터베이스는 'connectdb'로 계정을 생성하려면 다음과 같이 명령을 수행한다.

    ~~~
    grant all privileges on connectdb.* to connectuser@'%' identified by 'connect123!@#';
    
    grant all privileges on connectdb.* to connectuser@'localhost' identified by 'connect123!@#';
    
    flush privileges;
    ~~~

    + 실습에서는 grant all privileges on connectdb.* to connectuser@'%' identified by 'connect123!@#'; 명령어 사용함

  + **MySQL 버전이 8이상일때**

    ~~~
    
    create user 'connectuser'@'%' identified by 'connect123!@#';
    create user 'connectuser'@'localhost' identified by 'connect123!@#';
    
    grant all privileges on connectdb.* to 'connectuser'@'%';
    grant all privileges on connectdb.* to 'connectuser'@'localhost';
    
    
    flush privileges;
    ~~~

    

### 생성한 Database에 접속하기

+ 아래와 같이 명령을 실행하여 원하는 데이터베이스에 접속할 수 있다.

  ~~~
  mysql -h호스트명 -uDB계정명 -p 데이터베이스이름
  ~~~

+ db이름이 connectdb, db계정이 connectuser, 암호가 connect123!@#일 경우 콘솔창에 다음과 같이 입력한다.

  ~~~
  mysql -h127.0.0.1 -uconnectuser -p connectdb
  ~~~

  + p바로옆에 암호를 적어줘도 되지만 암호가 노출되는것이 별로 좋은 것은 아니기 때문에 위 명령어를 입력하고 엔터 한다음에 패스워드를 입력하라는 표시가 뜨면 그때 패스워드를 입력해서 엔터해주면 된다.

  

### MySQL 연결 끊기

+ 프롬프트에서 quit혹은 exit라고 입력한다.

  ~~~
  mysql> quit
  mysql> exit
  ~~~



### SQL 특징

+ 프롬프트에서는 SQL을 입력합니다.
+ SQL은 semicolon (;)으로 끝납니다.
+ SQL은 쿼리(Query)라고 읽습니다.
+ 쿼리는 DBMS에게 명령을 내릴 때 사용하는 문장이라고 생각하면 쉽습니다.
+ SELECT는 어떤 내용을 조회할 때 사용하는 키워드입니다.
+ MySQL은 쿼리에 해당하는 결과의 전체 row를 출력하고 마지막에 전체 row 수와 쿼리실행에 걸린 시간을 표시합니다.
+ 쿼리를 이용해서 계산식의 결과도 구할 수 있다.



### 키워드는 대소문자를 구별하지 않는다.

~~~
mysql> SELECT VERSION(), CURRENT_DATE;
mysql> select version(), current_date;
mysql> SeLeCt vErSiOn(), current_DATE;
~~~



### 여러 문장을 한 줄에 연속으로 붙여서 실행가능하다.

+ 각 문장에 semicolon만 붙여 주면 된다.

  ~~~
  mysql> select version(); select now();
  ~~~



### 하나의 SQL은 여러 줄로 입력가능하다.

+ MySQL은 문장의 끝을 라인으로 구분하는 것이 아니라 semicolon(;)으로 구분하기 때문에 여러줄에 거쳐 문장을 쓰는 것도 가능하다.

### SQL을 입력하는 도중에 취소할 수 있다.

+ 긴 쿼리를 작성하다가 중간에 취소해야하는 경우에는 즉시 \\c를 붙여주면 된다.



### DBMS에 존재하는 데이터베이스 확인하기

+ 작업하기 위한 데이터베이스를 선택하기 위해서는 어떤 데이터베이스가 존재하는 지 알아보아야 한다.
+ 현재 서버에 존재하는 데이터베이스를 찾아보기 위해서 show statement을 사용한다.

~~~
mysql> show databases;
~~~



### 사용중인 데이터베이스 전환하기

+ Database을 선택하기 위해, "use" command 사용

  ~~~
  mysql> use mydb;
  ~~~

+ 데이터베이스를 전환하려면, 이미 데이터베이스가 존재해야하며 현재 접속중인 계정이 해당 데이터베이스를 사용할 수 있는 권한이 있어야 한다.





### 테이블(table)의 구성 요소

+ 관계형 DB는 데이터들이 다 테이블 형태로 저장이 된다.
+ 테이블이 RDBMS(관계형 데이터베이스 관리 시스템)이 가장 기본적인 저장 구조이다.

![2_8_1_(table)_](https://user-images.githubusercontent.com/88477839/158117461-e8339225-0bda-4371-9663-10d526edfdcb.png)

+ 테이블 : RDBMS의 기본적 저장구조. 한 개 이상의 column과 0개 이상의 row로 구성
+ 열(Column): 테이블 상에서의 단일 종류의 데이터를 나타냄.
  + 특정 데이터 타입 및 크기를 가지고 있음
+ 행(Row) : Column들의 값의 조합. 레코드라고 불린다.
  + 기본키(PK)에 의해 구분된다. 기본키는 중복을 허용하지 않으며 없어서는 안 된다.
+ Field : Row와 Column의 교차점으로 Field는 데이터를 포함할 수 있고 없을 때는 NULL값을 가지고 있다.



### 현재 데이터베이스에 존재하는 테이블 목록 확인하기

+ Database를 선택 후, Database의 전체 테이블 목록을 출력

~~~
mysql> show tables;

Empty set (0.02 sec)
~~~

+ "empty set"은 데이터베이스에 어떤 테이블도 아직 생성되지 않았다는 것을 알려주는 것이다.

  

### SQL 연습을 위한 테이블 생성과 값의 저장

+ Examples.sql을 다운로드 한다.

+ 터미널에서 examples.sql이 있는 폴더로 이동한 후(cd 해당 폴더명), 다음과 같이 명령을 수행한다. 명령 수행후 암호 입력한다.

  ~~~
  mysql -uconnectuser -p connectdb < examples.sql
  ~~~

+ Examples.sql에는 연습을 위한 테이블 생성문과 해당 테이블에 값을 저장하는 입력문이 존재한다.

+ 잘 들어갔는지 확인을 위해 아래 명령문을 통해 다시 데이터베이스에 접속한다.

  ~~~
  mysql -uconnectuser -p connectdb;
  ~~~

+ 아래 명령을 통해 접속한 db의 테이블 목록을 확인한다.

  ~~~
  mysql> show tables;
  ~~~

  ![2_8_1_SQL_____](https://user-images.githubusercontent.com/88477839/158121136-7b033b57-3f75-4e42-bf8a-141cc10ebc59.png)



### 테이블 구조를 확인하기 위한 DESCRIBE 명령

+ table 구조를 확인하기 위해, DESCRIBE 명령을 사용할 수 있다.

  ~~~
  mysql> desc employee;
  ~~~

  

## DML(select, insert, update, delete)



### 데이터 조작어(Data Manipulation Language, DML)

#### 데이터 조작어의 종류

+  데이터의 조작어는 모두 동사로 시작한다. 시작하는 동사에 따라서 다음과 같은 4가지 조작어가 있다.
  + select - 검색
  + insert - 등록
  + Update - 수정
  + Delete - 삭제



#### Select 구문의 기본 문형

![2_8_2_select__](https://user-images.githubusercontent.com/88477839/158133303-80443f12-acc0-4cdd-a191-600a3fe0d707.png)

+ 전체 데이터 검색

  ~~~
  select * from department;
  ~~~

  

#### select 구문 예제 - 컬럼에 alias부여하기

+ 컬럼에 대한 alias(별칭)을 부여해서 나타내는 칼럼의 heading을 변경할 수 있다.

+ 예제

  ~~~
  select empno as 사번, name as 이름, job as 직업 from employee;
  
  select deptno 부서번호, name 부서명 from department;
  ~~~

  

#### select 구문 예제(컬럼의 합성(Concatenation))

+ 문자열 결합함수 concat 사용

+ 예제 : employee 테이블에서 사번과 부서번호를 하나의 칼럼으로 출력

  ~~~
  select concat(empno,'-',deptno) as '사번-부서번호' from employee;
  ~~~

  <img width="563" alt="2_8_2_select__(_)" src="https://user-images.githubusercontent.com/88477839/158135567-87bbd2fe-39cd-46aa-b896-587af4169e99.png">



#### Select 구문 예제 (중복행의 제거)

+ 중복되는 행이 출력되는 경우,  Distinct 키워드로 중복행을 제거

+ 예제1 : 사원 테이블의 모든 부서번호 출력 (사원수만큼 출력된다.)

  ~~~sql
  select deptno from employee;
  ~~~

+ 예제2 : 사원 테이블의 부서번호를 중복되지 않게 출력

  ~~~sql
  select distinct deptno from employee;
  ~~~

  

#### Select 구문 예제(정렬하기)

![2](https://user-images.githubusercontent.com/88477839/158136889-8be58d53-0f3b-4219-80d1-35b30f2096ff.png)

+ 예제 : employee 테이블에서 직원의 사번(empno), 이름(name), 직업(job)을 출력하시오. 단, 이름을 기준을 오름차순 정렬한다.

  ~~~sql
  select empno, name, job from employee order by name;
  
  select empno as 사번, name as 이름, job as 직업 from employee order by 이름;
  ~~~



### select 구문 예제 (특정 행 검색 - where절)

![3](https://user-images.githubusercontent.com/88477839/158150171-c6a36e41-079e-4681-8fc8-2009ae7a0f78.png)

+ 산술비교 연산자

  + 예제 : employee 테이블에서 고용일(hiredate)이 1981년 이전의 사원 이름과 고용일을 출력하시오

    ~~~sql
    select name, hiredate from employee where hiredate<'1981-01-01';
    ~~~

+ 논리연산자

  + 예제 : employee 테이블에서 부서번호가 30인 사원이름과 부서번호를 출력하시오

  ~~~sql
  select name, deptno from employee where deptno = 30;
  ~~~

  + 예제 : employee테이블에서 부서번호가 10이고 샐러리가 1500보다 작은 사원의 정보를 출력하시오

  ~~~sql
  select * from employee where deptno = 10 and salary < 1500;
  ~~~

  

+ IN키워드

  + 예제 : employee 테이블에서 부서번호가 10 또는 30인 사원이름과 부서번호를 출력하시오

  ~~~sql
  select name, deptno from employee where deptno in (10, 30);
  ~~~

  ~~~sql
  위와 같음
  select name, deptno from employee where deptno = 10 or deptno = 30;
  ~~~

  

+ LIKE키워드

  + 와일드 카드를 사용하여 특정 문자를 포함한 값에 대한 조건을 처리
  + % 는 0에서부터 여러 개의 문자열을 나타냄
  + _는 단 하나의 문자를 나타내는 와일드 카드

  예제 : employee테이블에서 이름에 'A'가 포함된 사원의 이름(name)과 직업(job)을 출력하시오

  ~~~sql
  select name, job from employee where name like '%A%';
  ~~~

  + 이름이 A로 시작하던 A로 끝나던 어쨌든 A를 포함하고 있는 사원

  ~~~sql
  select name, job from employee where name like 'A%';
  ~~~

  + 이름이 A로 시작하는 사원 찾기

  ~~~sql
  select name, job from employee where name like '%A';
  ~~~

  + 이름이 A로 끝나는 사원 찾기

  ~~~sql
  select * from employee where name like '_A%';
  ~~~

  + 이름의 두번째 글자가 A인 사원



#### select 구문 예제 (함수의 사용)

+ UCASE,UPPER - 대문자로 바꿔줌

  + 예제

  ~~~
  mysql> SELECT UPPER('SEoul'), UCASE('seOUL');
   +-----------------+-----------------+
    | UPPER('SEoul') | UCASE('seOUL') |
    +-----------------+-----------------+
    | SEOUL            | SEOUL            |
    +-----------------+-----------------+
  ~~~

  + 오라클인 경우 select UPPER('SEoul'), UCASE('seOUL') from dual 임 (반드시 from절이 있어야 하고 그래서 임시 테이블인 dual이 옴)

  

+ LCASE,LOWER - 소문자로 바꿔줌

  + 예제

  ~~~
  mysql> SELECT LOWER('SEoul'), LCASE('seOUL');
   +-----------------+-----------------+
    | LOWER('SEoul') | LCASE('seOUL') |
    +-----------------+-----------------+
    | seoul           | seoul           |
    +-----------------+-----------------+
  ~~~

  

+ Substring - 특정 문자만 끊어줌

  + 예제

  ~~~
  mysql> SELECT SUBSTRING('Happy Day',3,2);
    +-----------------+-----------------+
    | SUBSTRING('Happy Day',3,2)        |
    +-----------------+-----------------+
    | pp                                |
    +-----------------+-----------------+
  ~~~

  + 자바는 0번 인덱스부터 시작하지만 mysql은 1번 인덱스부터 시작함
  + 위 쿼리문은 Happy day의 3번 인덱스부터 2개만 select하라는 의미임



+ LPAD,RPAD - 왼쪽이나 오른쪽 여백에 뭔가 채우는 것

  + 예제

  ~~~
  mysql> SELECT LPAD('hi',5,'?'),LPAD('joe',7,'*');
    +------------------+-------------------+
    | LPAD('hi',5,'?')    | LPAD('joe',7,'*')   |
    +------------------+-------------------+
    | ???hi               |           ****joe    |
    +------------------+-------------------+
  ~~~

  + hi를 5글자로 출력할건데 나머지 왼쪽 빈 여백을 ?로 채워달라
  + joe를 7글자로 출력할건데 나머지 왼쪽 빈 여백을 *로 채워달라

  ~~~sql
  select LPAD(name,10,'+') from employee;
  ~~~

  

+ TRIM,LTRIM,RTRIM - 공백을 없애는 것

  + 예제

  ~~~
  mysql> SELECT LTRIM(' hello '), RTRIM(' hello ');
  +-------------------------------------+
  | LTRIM(' hello ') | RTRIM(' hello ')  |
  +-------------------------------------+
  | 'hello '         | '  hello‘        |
  +-------------------------------------+
  ~~~

  ~~~
  mysql> SELECT TRIM(' hi '),TRIM(BOTH 'x' FROM 'xxxhixxx');
  +----------------+-----------------------------------+
  | TRIM(' hi ')   | TRIM(BOTH 'x' FROM 'xxxhixxx')    |
  +----------------+-----------------------------------+
  | hi             | hi                                |
  +----------------+-----------------------------------+
  ~~~



+ ABS(x) : x의 절대값을 구한다.

  + 예제

  ~~~
  mysql> SELECT ABS(2), ABS(-2);
  +-----------+------------+ 
  | ABS(2)    | ABS(-2)    | 
  +-----------+------------+ 
  | 2         | 2          | 
  +-----------+------------+
  ~~~



+ MOD(n,m) , n%m : n을 m으로 나눈 나머지 값을 출력한다.

  + 예제

  ~~~
  mysql> SELECT MOD(234,10), 253 % 7, MOD(29,9);
  +----------------+------------+-------------+ 
  | MOD(234,10)    | 253 % 7    | MOD(29,9)   | 
  +----------------+------------+-------------+ 
  |      4.        |       1    |      2      | 
  +----------------+------------+-------------+
  ~~~

+ FLOOR(x) : x보다 크지 않은 가장 큰 정수를 반환합니다. BIGINT로 자동 변환합니다.

+ CEILING(x) : x보다 작지 않은 가장 작은 정수를 반환합니다.

+ ROUND(x) : x에 가장 근접한 정수를 반환합니다.

+ POW(x,y) POWER(x,y) : x의 y 제곱 승을 반환합니다.

+ GREATEST(x,y,...) : 가장 큰 값을 반환합니다.

+ LEAST(x,y,...) : 가장 작은 값을 반환합니다.

+ CURDATE(),CURRENT_DATE : 오늘 날짜를 YYYY-MM-DD나 YYYYMMDD 형식으로 반환합니다.

+ CURTIME(), CURRENT_TIME : 현재 시각을 HH:MM:SS나 HHMMSS 형식으로 반환합니다.

+ NOW(), SYSDATE() , CURRENT_TIMESTAMP : 오늘 현시각을 YYYY-MM-DD HH:MM:SS나 YYYYMMDDHHMMSS 형식으로 반환합니다. 

+ DATE_FORMAT(date,format) : 입력된 date를 format 형식으로 반환합니다.

+ PERIOD_DIFF(p1,p2) : YYMM이나 YYYYMM으로 표기되는 p1과 p2의 차이 개월을 반환합니다.



### SELECT 구문 (CAST 형변환)

![4](https://user-images.githubusercontent.com/88477839/158158691-200a726d-2858-4170-988d-0d8011cb1fda.png)

예제

~~~
mysql> select cast(now() as date);
+---------------------+
| cast(now() as date) |
+---------------------+
| 2003-09-25          |
+---------------------+
1 row in set (0.00 sec)
~~~

~~~
mysql> select cast(1-2 as unsigned);
+----------------------------+
|   cast(1-2 as unsigned)    |
+----------------------------+
|  18446744073709551615 |
+----------------------------+
~~~



#### Select 구문 (그룹함수)

![2_8_2_select_()](https://user-images.githubusercontent.com/88477839/158167961-1cf63f9d-05f4-4c15-a8d2-3f78d2fbe4af.png)

+ 예제 : employee 테이블에서 부서번호가 30인 직원의 급여 평균과 총합계를 출력

  ~~~sql
  select AVG(salary), sum(salary) from employee where deptno = 30;
  ~~~



#### select 구문 예제(그룹함수와 groupby 절)

예제 : employee 테이블에서 부서별 직원의 부서번호, 급여 평균과 총합계를 출력

~~~sql
select deptno, avg(salary), sum(salary) from employee group by deptno;
~~~



### 데이터 입력 (INSERT문)

~~~
INSERT INTO 테이블명(필드1, 필드2, 필드3, 필드4, … ) 
        VALUES ( 필드1의 값, 필드2의 값, 필드3의 값, 필드4의 값, … )

INSERT INTO 테이블명
        VALUES ( 필드1의 값, 필드2의 값, 필드3의 값, 필드4의 값, … )
~~~

- 필드명을 지정해주는 방식은 디폴트 값이 세팅되는 필드는 생력할 수 있다.
- 필드명을 지정해주는 방식은 추 후, 필드가 추가/변경/수정 되는 변경에 유연하게 대처 가능하다.
- 필드명을 생략했을 경우에는 모든 필드 값을 반드시 입력해야 한다.

예제 - Role 테이블에 role_id는 200, description에는 'CEO'로 한건의 데이터를 저장하시오

~~~sql
insert into Role (role_id,description) values(200,'ceo');
~~~



### 데이터 입력(Update문)

~~~
 UPDATE  테이블명
        SET  필드1=필드1의값, 필드2=필드2의값, 필드3=필드3의값, …
   WHERE  조건식
~~~

- 조건식을 통해 특정 row만 변경할 수 있습니다.
- 조건식을 주지 않으면 전체 로우에 영향을 미치니 조심해서 사용하도록 합니다.

예제 - ROLE 테이블에 role_id가 200일 경우 description을 'CTO'로 수정하시오

~~~sql
update role set description = 'cto' where role_id=200;
~~~



### 데이터 삭제(DELETE문)

~~~
DELETE
      FROM  테이블명
WHERE  조건식
~~~

- 조건식을 통해 특정 row만 삭제할 수 있습니다.
- 조건식을 주지 않으면 전체 로우에 영향을 미치니 조심해서 사용하도록 한다.

예제 - Role테이블에서 role_id는 200인 정보를 삭제하시오

~~~
delete from Role where role_id = 200;
~~~



## DDL(create, drop)

> 데이터가 저장되는 틀인 테이블은 생성, 수정, 삭제하기



### 데이터 정의어 ( Data definition language, DDL)

+ 데이터베이스에 스키마 객체를 생성하거나 변경하거나 제거하거나 하는 일들을 수행하는 것을 데이터 정의어 라고 한다.



### MySQL 데이터 타입

![2_8_3_MySQL__-1](https://user-images.githubusercontent.com/88477839/158178278-2729d660-31d3-4149-9d89-1dcd92a73f52.png)

![2_8_3_MySQL__-2](https://user-images.githubusercontent.com/88477839/158178324-bae1ac21-d279-4cde-885c-20422f03c3da.png)



#### 테이블 생성

~~~
create table 테이블명( 
          필드명1 타입 [NULL | NOT NULL][DEFAULT ][AUTO_INCREMENT], 
          필드명2 타입 [NULL | NOT NULL][DEFAULT ][AUTO_INCREMENT], 
          필드명3 타입 [NULL | NOT NULL][DEFAULT ][AUTO_INCREMENT], 
          ........... 
          PRIMARY KEY(필드명) 
          );
~~~

- 데이터 형 외에도 속성값의 빈 값 허용 여부는 NULL 또는 NOT NULL로 설정
- DEFAULT 키워드와 함께 입력하지 않았을 때의 초기값을 지정
- 입력하지 않고 자동으로 1씩 증가하는 번호를 위한 AUTO_INCREMENT

+ 테이블 생성 실습

  ~~~sql
  create table employee2(
  	empno integer not null primary key,
    name varchar(10),
    job varchar(9),
    boss Integer,
    hiredate varchar(12),
    salary decimal(7,2),
    comm decimal(7,2),
    deptno integer);
  
  ~~~



#### 테이블 수정(컬럼 추가 / 삭제)

~~~
alter table 테이블명
          add  필드명 타입 [NULL | NOT NULL][DEFAULT ][AUTO_INCREMENT];

alter table 테이블명
         drop  필드명;
~~~

+ 실습 - employee2테이블에 생일(birthdate)컬럼을 varchar(12) 형식으로 추가하시오

  ~~~sql
  alter table employee2 add birthdate varchar(12);
  ~~~

+ 실습 - employee2 테이블의 생일(birthdate)컬럼을 삭제하시오

  ~~~sql
  alter table employee2 drop birthdate;
  ~~~



#### 테이블 수정(컬럼 수정)

~~~
alter table  테이블명
     change  필드명  새필드명 타입 [NULL | NOT NULL][DEFAULT ][AUTO_INCREMENT];
~~~

- change 키워드를 사용하고 칼럼을 새롭게 재정의 (이름부터 속성까지 전부)

+ 실습 - Employee2 테이블의 부서번호(deptno)를 dept_no로 수정 하시오

  ~~~sql
  alter table employee2 change deptno dept_no int(11);
  ~~~



#### 테이블 이름 변경

~~~sql
alter table 테이블명 rename 변경이름;
~~~

+ 실습 - employee2 테이블의 이름을 employee3로 변경하시오

  ~~~sql
  alter table employee2 rename employee3;
  ~~~



#### 테이블 삭제하기

~~~
drop table 테이블 이름
~~~

+  참고로, 제약 조건이 있을 경우에는 drop table 명령으로도 테이블이 삭제 되지 않을 수도 있다. 그럴 경우는 테이블을 생성한 반대 순서로 삭제를 해야 한다.
  + foreign key 관계

