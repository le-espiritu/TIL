# SQL



## join 조인

+ 데이터를 select 할때 원하는 결과가 두 개 이상의 테이블을 엮어야 나오는 경우가 있다.
+ 이런 경우 조인을 쓰면 두 개의 테이블을 엮어서 원하는 데이터를 추출할 수 있다.
+ 두 테이블의 조인을 위해서는 기본키와 외래키 관계로 맺어져야 한다.



### 기본 문법 - inner join

> 두 테이블을 연결할 때 가장 많이 사용하는 것이 내부 조인이다. 그냥 조인이라고부르면 보통 내부 조인을 의미한다.

+ 컴마 연산자와 비교 연산자 사용

  ~~~sql
  select table1.column, table2.column
  from table1, table2
  where table1.column1 = table2.column2
  and 추가 조건
  ~~~

+ inner join 키워드와 on 키워드

  ~~~sql
  select table1.column, table2.column
  from table1 inner join table2
  on table1.column1 = table2.column2; 
  where [검색 조건]
  ~~~

  ~~~sql
  select [열 목록]
  from [첫 번째 테이블] inner join [두 번재 테이블]
  on [조인될 조건]
  where [검색 조건]
  ~~~

  

### 참고 및 보충 자료

+ https://hongong.hanbit.co.kr/sql-%EA%B8%B0%EB%B3%B8-%EB%AC%B8%EB%B2%95-joininner-outer-cross-self-join/
+ https://jhnyang.tistory.com/331



## 서브쿼리

+ 서브쿼리란 하나의 SQL문 안에 포함되는 또 다른 SQL문을 의미한다.
+ 즉 서브쿼리의 실행 결과를 메인 쿼리의 데이터 검색 조건으로 이용하는 것이라고 이해할 수 있다.



### 서브쿼리 사용 주의 사항

+ 서브쿼리는 괄호로 감싸서 사용한다.
+ 단일행, 복수행 비교연산자와 함께 사용가능하다.
+ 서브쿼리는 order by가 불가능하다.
+ 단일행 비교 연산자는 서브쿼리의 결과가 반드시 1건 이하여야 한다.



### 단일행 서브쿼리

+ 단일행 서브쿼리는 실행 결과가 항상 1건 이하인 서브쿼리를 의미한다.



### 서브쿼리 참고자료

+ https://kimdingko-world.tistory.com/197



## 날짜 관련 sql 함수

+ 날짜와 시간은 date와 string을 왔다갔다 하므로 잘 비교해줘야 한다.

+ 날짜를 문자형 형태로 비교하기 위해서는 date 타입을 string으로 변환

  

### TO_CHAR() 함수 사용 - 오라클

+ 날짜를 문자형 형태로 비교하기 위해서는 date 타입을 string으로 변환한다.

  ~~~
  to_char(날짜, 포맷)
  ~~~

  ~~~sql
  select to_char(SYSDATE, 'yyyy/mm/dd hh24:mi:ss') from dual;
  
  -> 2021/07/17 13:29:33
  ~~~

  ~~~sql
  select  to_char(SYSDATE, 'yyyy') from dual;
  
  -> 2022
  ~~~



### To_date() 함수 - 오라클

+ String 형태를 date 형태로 바꾸고 싶은 경우

  ~~~
  to_date(문자열, 포맷)
  ~~~

  ~~~sql
  select to_date('20220115', 'yyyy-mm-dd') from dual
  
  -> 2022-01-15
  ~~~

  

### 날짜 관련 오라클 함수 참고 자료 

+ https://velog.io/@syh0397/SQL-%EB%82%A0%EC%A7%9C%EC%99%80-%EC%8B%9C%EA%B0%84-%EB%AC%B8%EC%9E%90%EC%97%B4-%EB%8B%A4%EB%A3%A8%EA%B8%B0
+ https://wyatt37.tistory.com/53



### DATE_FORMAT - MYSQL

+ mysql에서 날짜를 문자열 형태로 바꿀때 사용한다.

  ~~~sql
  DATE_FORMAT(날짜, 출력 형식)
  ~~~

  ~~~sql
  select date_format('2019-09-16 20:23:12', '%y/%m/%d')
  
  -> 2019/09/16 출력
  ~~~



### STR_TO_DATE - MYSQL

+ mysql에서 문자열을 날짜로 변환할 때 사용한다.

  ~~~sql
  STR_TO_DATE(문자, 출력 형식)
  ~~~

  ~~~sql
  select str_to_date('20080101', '%y-%m-%d')
  
  -> 20080101이라는 문자를 2008-01-01의 형태의 날짜로 리턴
  ~~~

  

### mysql에서 년도, 월, 일 등 만 따로 불러오는 함수

+ mysql 에는 날짜에서 년, 월, 일을 분리할 수 있는 함수가 있다.

+ **year()** - 년도만 분리하기

  ~~~sql
  select year(getdate());
  ~~~

+ **month()** - 월만 분리하기

  ~~~sql
  select month(getdate());
  ~~~

+ **day()**- 일만 분리하기

  ~~~sql
  select day(getdate());
  ~~~

+ 참고로 오라클에서 현재날짜를 구하는 sysdate()가 mysql에서는 getdate()이다.



### mysql 날짜함수 관련 참고자료

+ https://wyatt37.tistory.com/55



~~~sql
select b.month, a.name, max(b.cnt)
from places as a,
(select dto_char(date,'mm') as month, place_id, count(coment) as cnt from place_reviews group by month, place_id where to_char(date,'yyyy') = '2018') as b
group by b.month
where a.id = b.place_id;
~~~

