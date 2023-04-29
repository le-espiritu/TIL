# sql

> mysql 사용



---

### 다른 테이블의 최신 auto_increment 값을 활용하여 insert 하기

+ 예시 테이블 구조

  ~~~sql
  create table practice_photo(
  practice_no int not null,
  name varchar(30) not null);
  ~~~

  

+ 방법1

  ~~~sql
  insert into practice_photo(
  practice_no,name
  ) values(
  (SELECT AUTO_INCREMENT
  FROM information_schema.tables
  WHERE table_name = 'practice'
  AND table_schema = DATABASE( ))-1,
  'photo1'
  );
  ~~~

+ 방법2 - select max( auto_incre 적용된 컬럼명 ) from 테이블명

  ~~~sql
  insert into practice_photo values(
  (select max(no) from practice),
  'photo2');
  ~~~

  



