# CS - 데이터베이스



### 참고 자료

+ https://www.nossi.dev/interview/cs/db



---

### database 기본 개념

#### database(DB)

+ 전자적으로 저장되고 사용되는, 관련있는 데이터들의 조직화된 집합  



#### DBMS

+ database management systems
+ 사용자에게 DB를 정의하고 만들고 관리하는 기능을 제공하는 소프트웨어 시스템 
+ DB를 정의하다 보면 부가적인 데이터가 발생된다.
  + 이 부가적인 데이터를 metadata라고 부른다.



#### metadata

+ data about data
  + 데이터를 설명하기 위한 데이터
  + 예를 들어 사진(데이터)의 해상도, 포맷 등 사진과 관련된 부가적인 정보 
+ DB에서 metadata란 database를 정의하거나 기술하는(descriptive) data
+ catalog라고도 부름
+ e.g.) 데이터 유형, 구조, 제약 조건, 보안, 저장, 인덱스, 사용자 그룹 등등
+ metadata 또한 DBMS를 통해 저장/관리된다.



#### database system

+ database + DBMS + 연관된 applications
+ 줄여서 database라고도 부름 

---

### data models

+ DB의 구조(structure)를 기술하는데 사용될 수 있는 개념들이 모인 집합
  + DB 구조 : 데이터 유형, 데이터 관계(relationship), 제약 사항(constraints) 등등
+ DB 구조를 추상화해서 표현할 수 있는 수단을 제공한다.
+ Data model은 여러 종류가 있으며 추상화 수준과 DB 구조화 방식이 조금씩 다르다.
+ DB에서 읽고 쓰기 위한 기본적인 동작들(operations)도 포함한다.



#### data models 분류

+ Conceptual data models (high-level data models)
+ logical data models (representational data models)
+ physical data models (low-level data models)



#### conceptual data models

+ 일반 사용자들이 쉽게 이해할 수 있는 개념들로 이뤄진 모델
+ 추상화 수준이 가장 높음
  + 데이터베이스의 구조를 높은 수준으로 추상화 함
+ 비즈니스 요구 사항을 추상화하여 기술할 때 사용

<img width="1197" alt="스크린샷 2023-04-09 21 23 29" src="https://user-images.githubusercontent.com/88477839/230772293-73b5182b-c191-40c6-843f-f5dc5b2ebc48.png">

+ 위 그림에서 Student와 Book은 각각 entity이다.
  + 각각의 entity는 attribute라고 불리우는 속성들이 있다. (Stu id, major, book id 등......)
  + 엔터티는 데이터 모델링에서 사용되는 객체다.
  + 엔터티는 업무에 필요하고 유용한 정보를 저장하고 관리하기 위한 어떤것(thing)이라고 말할 수 있다.
  + 엔터티는 데이터베이스 테이블이다. 
    + 데이터베이스의 레코드가 엔터티에서는 인스턴스라 불리우고,
    + 데이터베이스의 컬럼이 엔터티에서는 어트리뷰트(속성)으로 불리운다. 
+ 이 둘의 관계는 reads라는 관계이다. 
+ ERD ( entity-relationship diagram)



#### logical data models

+ 이해하기 어렵지 않으면서도 디테일하게 DB를 구조화 할 수있는 개념들을 제공
+ 데이터가 컴퓨터에 저장될 때의 구조와 크게 다르지 않게 DB 구조화를 가능하게 함
+ 특정 DBMS나 storage에 종속되지 않는 수준에서 DB를 구조화할 수 있는 모델
  + 어느 정도 추상화가 되어 있음

<img width="1068" alt="스크린샷 2023-04-09 21 32 33" src="https://user-images.githubusercontent.com/88477839/230772783-b4c4b04d-b2a3-4e9d-8e41-53016f461a2f.png">

+ 테이블과 동일 
+ logical data models 종류
  + relational data model - 가장 많이 사용됨
    + 유명한 DBMS들이 relational data model 기반
  + object data model
  + Object-relational data model



#### physical data modles

+ 컴퓨터에 데이터가 어떻게 파일 형태로 저장되는지를 기술할 수 있는 수단을 제공
+ 데이터가 실제로 저장장치에 저장되는 형태에 가장 근접하게 데이터베이스 구조를 표현할 수 있는 데이터 모델 
+ Data format, data orderings, access path 등등
+ access path : 데이터 검색을 빠르게 하기 위한 구조체
  + 대표적인 예로 Index가 있음



---



### database schema

+ data model을 바탕으로 database의 구조를 기술 한 것
+ schema는 database를 설계할 때 정해지며 한번 정해진 후에는 자주 바뀌지 않는다.

<img width="1249" alt="스크린샷 2023-04-09 21 43 32" src="https://user-images.githubusercontent.com/88477839/230773177-8fd51074-1adf-4a9c-bfc9-dd6f1fc8de80.png">



#### database state

+ database에 있는 실제 데이터는 꽤 자주 바뀔 수 있다.
+ 특정 시점에 database에 있는 데이터를 database state 혹은 snapshot이라고 한다.
+ 혹은 database에 있는 현재 instances의 집합이라고도 한다.



#### three-schema architecture

+ Database system을 구축하는 architecture 중의 하나
+ user application으로 부터 물리적인 database를 분리시키는 목적
  + 물리적인 database의 구조가 조금씩 바뀔 수있다.
  + database의 구조가 바뀔때에도 이를 사용하는 user application에는 영향을 끼치지 않게 하기 위해 three-schema architecture를 사용한다. 
+ 세 가지 level이 존재하며 각각의 level마다 schema가 정의되어 있다.
  + external schemas(외부스키마) : external level
  + conceptual schemas(개념스키마) : conceptual level
  + Internal schemas(내부스키마) : internal level

<img width="730" alt="스크린샷 2023-04-09 21 54 41" src="https://user-images.githubusercontent.com/88477839/230773740-2755e6fb-5c3d-4e52-9948-0858fc7209a0.png">

+ **internal schema**(내부 스키마)
  + 물리적으로 데이터가 어떻게 저장되는지 physical data model을 통해 표현
  + data storage, data structure, access path 등등 실체가 있는 내용 기술
  + 내부스키마란 실제 구현에 관한 이야기로, 이 속성이 어떠한 형태(Integer or Varchar or 등등)이며 어느정도의 크기를 갖는지 등에 관해서 기술해둔 스키마를 의미
+ **external schema**(외부 스키마)
  + 실제 유저가 바라보는 schema
  + 일반 사용자나 응용 프로그래머가 접근하는 계층으로 전체 데이터베이스 중 하나의 논리적인 부분을 의미
  + 여러 개의 외부 스키마가 있을 수 있음
  + 서브 스키마(Sub Schema)라고도 하며, View의 개념임
  + external views, user views 라고도 불림
  + 특정 유저들이 필요로 하는 데이터만 표현
  + 그 외 알려줄 필요가 없는 데이터는 숨긴다.
  + logical data model을 통해 표현
+ **conceptual schema**(개념 스키마)
  + 전체 database에 대한 구조를 기술
  + 개념스키마란 개발하는데 필요한 모든 데이터베이스를 정의해 놓은 것
  + internal schema를 한번 추상화 시켜서 표현한 schema
  + 물리적인 저장 구조에 관한 내용은 숨김
  + entities, data types, relationships, user operations, constraints에 집중
  + logical data model을 통해 기술 
+ Three-schema architecture의 목적
  + 안정적으로 데이터베이스 시스템을 운영하기 위해서 사용되는 아키텍쳐이다.
  + 각 레벨을 독립시켜서 어느 레벨에서의 변화가 상위 레벨에 영향을 주지 않기 위함이다.
  + 대부분의 DBMS가 three level을 완벽하게 혹은 명시적으로 나누지는 않음
  + 실제적으로 데이터가 존재하는 곳은 internal level이다.



---



### database Language

#### data definition language (DDL)

+ conceptual schema를 정의하기 위해 사용되는 언어

+ internal schema까지 정의할 수 있는 경우도 있음

+ 사용 예

  + ~~~sql
    create table 테이블명( 
              필드명1 타입 [NULL | NOT NULL][DEFAULT ][AUTO_INCREMENT], 
              필드명2 타입 [NULL | NOT NULL][DEFAULT ][AUTO_INCREMENT], 
              필드명3 타입 [NULL | NOT NULL][DEFAULT ][AUTO_INCREMENT], 
              ........... 
              PRIMARY KEY(필드명) 
              );
    ~~~

  + ~~~sql
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

  + ~~~sql
    alter table employee2 add birthdate varchar(12);
    ~~~

  + ~~~sql
    alter table employee2 drop birthdate;
    ~~~

  + ~~~sql
    alter table employee2 change deptno dept_no int(11);
    ~~~

  + ~~~sql
    drop table 테이블 이름
    ~~~



#### storage definition language (SDL)

+ internal schema를 정의하는 용도로 사용되는 언어
+ 요즘은 특히 relational DBMS에서는 SDL이 거의 없고 파라미터 등의 설정으로 대체됨

#### view definition language (VDL)

+ external schemas를 정의하기 위해 사용되는 언어
+ 대부분의 DBMS에서는 DDL이 VDL 역할까지 수행



#### data manipulation language (DML)

+ Database 에 있는 data를 활용하기 위한 언어 (데이터 조작어)
+ data 추가, 삭제, 수정, 검색 등등의 기능을 제공하는 언어
  + select
  + Insert
  + update
  + delete



**오늘날 DBMS는 DML, VDL, DDL이 따로 존재하기 보다는 통합된 언어로 존재 한다.	**

**대표 적인 예로 relational database language : SQL 이 있다. **



---

### relational database

#### set

+ 서로 다른 elements를 가지는 collection
  + 중복된 elements를 가지지 않는다.
+ 하나의 set에서 elements의 순서는 중요하지 않다.
+ e.g.{1,3,11,4,7}



#### 수학에서의 relation

+ 각 set의 원소들이 관계를 맺으며 리스트를 형성하는 것 
  + 여기서 리스트는 튜플이라 불리울 수 있다.
  + 각 set의 원소들이 관계를 맺어 만들어지는 리스트의  모든 경우의 수를 cartesian product 라고 함 
+ subset of cartesian product
+ set of tuples



#### relational data model에서의 relation

+ relational data model에서의 set은 도매인(엘리먼트, 혹은 밸류들의 집합)을 의미함
+ relational data model에서 역할에 따라 동일한 도매인이 여러개 사용될 수 있으며 그 역할은 attribute를 활용하여 구분한다.



#### relational data model

+ **domain** : set of atomic values
+ **domain name** : domain 이름
+ **attribute** : domain이 relation에서 맡은 역할 이름
+ **tupple** : 각 attribute의 값으로 이루어진 리스트. 일부 값은 NULL일 수 있다.
+ **relation** : set of tuples (이를 표로 표현한것이 table)
+ **relation name** : relation의 이름



#### relation schema

+ relation 의 구조를 나타낸다
+ relation 이름과 attributes 리스트로 표기된다.
  + ex) STUDENT(id, name, grade, major, phone_num, emer_phone_num)
+ attributes와 관련된 constraints도 포함한다.



#### degree of a relation

+ degree란 차수를 의미함
+ relation schema에서 attributes의 수 
  + ex) STUDENT(id, name, grade, major, phone_num, emer_phone_num) -> degree 6



#### relational database

+ relational data model에 기반하여 구조화된 database
+ relational database는 여러 개의 relations로 구성된다.



#### relation의 특징들

+ relation은 중복된 tuple을 가질 수 없다.

  + relation이란  튜플들의 set 이고, set은 중복된 원소를 허용하지 않기 때문이다.

+ relation의 tuple을 식별하기 위해 attribute의 부분 집합을 key로 설정한다.

+ relation에서 tuple의 순서는 중요하지 않다.

+ 하나의 relation에서 attribute의 이름은 중복되면 안된다.

+ 하나의 tuple에서 attribute의 순서는 중요하지 않다.

+ attribute는 atomic 해야 한다. (composite(합성의) or multivalued attribute 허용 안됨)

  

#### DB에서 NULL의 의미

+ 값이 존재하지 않는다.
+ 값이 존재하나 아직 그 값이 무엇인지 알지 못한다
+ 해당 사항과 관련이 없다.



#### DB에서의 key

+ superkey - relation에서 tuples를 unique하게 식별할 수 있는 attributes set
+ candidate key - 어느 한 attribute라도 제거하면 unique하게 tuples를 식별할 수 없는 super key
  + (또는 더이상 쪼개질 수 없는 super key )
+ primary key - relation에서 tupes를 unique하게 식별하기 위해 선택된 candidate key
+ unique key - primary key가 아닌 candidate keys
  + 또는 alternate key(대체키)라고도 함 
+ foreign key - 다른 relation의 pk를 참조하는 attributes set 



#### constraints란?

+ 사전적 의미로 '제약조건'을 뜻함 

+ Relational database의 relations들이 언제나 항상 지켜줘야 하는 제약 사항

+ **implicit constraints** (implicit - 내포된 )
  
  + relational data model 자체가 가지는 constraints
  + relation은 중복되는 tuple을 가질 수 없다.
  + relation 내에서는 같은 이름의 attribute를 가질 수 없다.
  
+ **schema-based constraints**
  + 주로 DDL을 통해 schema에 직접 명시할 수 있는 constraints
  
  + Explicit constraints라고도 함 ( explicit - 명백한, 노골적인 )
  
  + **domain constraints**
    + attribute의 value는 해당 attribute의 domain에 속한 value여야 한다.
    
  + **key constraints**
    
    + 서로 다른 tuples는 같은 value의 key를 가질 수 없다.
    
  + **NULL value constraint**
    + attribute가 NOT NULL로 명시됐다면 NULL을 값으로 가질 수 없다.
    
  + **entity integrity constraint** ( integrity  - 무결성)
    
    + primary key는 value에 NULL을 가질 수 없다.
    
  + **referential integrity constraint** ( referential  - 참조의)
    
    + FK와 PK가 도메인이 같아야 하고 PK에 없는 value를 FK가 값으로 가질 수 없다.
    
    

---

### SQL

#### SQL 뜻

+ Structured Query Language
+ 현업에서 쓰이는 relational DBMS의 표준 언어
+ 종합적인 database 언어 (DDL + DML + VDL)

---



### 트랜잭션

#### 트랜잭션 (Transaction) 이란?

+ 데이터베이스에서의 단일한 논리적인 작업 단위 (a single logical unit of work)
+ 논리적인 이유로 여러 SQL문들을 단일 작업으로 묶어서 나눠질 수 없게 만든 것이 transaction이다.
+ transaction의 SQL문들 중에 일부만 성공해서 DB에 반영되는 일을 일어나지 않는다.
  + 모두 성공해야만 DB에 반영된다. 




#### 트랜잭션 구현

~~~sql
START TRANSACTION;
UPDATE account SET balance = balance-200000 WHERE id = 'J';
UPDATE account SET balance = balance+200000 WHERE id = 'H';
COMMIT;
~~~

+ commit이란?
  + 지금까지 작업한 내용을 DB에 영구적으로(permanently) 저장하라는 의미
  + 동시에 transaction을 종료한다는 의미도 있음
+ ROLLBACK이란?
  + 지금까지 작업들을 모두 취소하고 transaction 이전 상태로 되돌린다는 의미
  + 동시에 transaction을 종료 한다. 



#### AUTOCOMMIT

+ 각각의 SQL문을 자동으로 transaction 처리 해주는 개념
+ SQL문이 성공적으로 실행하면 자동으로 commit 한다.
+ 실행 중에 문제가 있었다면 알아서 rollback 한다.
+ MySQL에서는 default로 autocommit이 활성화되어 있다.
+ 다른 DBMS에서도 대부분 같은 기능을 제공한다.
+ mysql에서는 start transaction 실행과 동시에 autocommit은 off 된다.
  + commit / rollback과 함께 transaction이 종료되면 원래 autocommit 상태로 돌아간다.



#### 일반적인 transaction 사용 패턴

1. transaction을 시작한다.
2. 데이터를 읽거나 쓰는 등의 SQL문들을 포함해서 로직을 수행한다.
3. 일련의 과정들이 문제없이 동작했다면 transaction을 commit 한다.
4. 중간에 문제가 발생했다면 transaction을 rollback 한다.



#### ACID - 트랜잭션의 속성

+ Atomicity
  + 모두 성공하거나 모두 실패하거나 ( ALL or NOTHING)
  + transaction은 논리적으로 쪼개질 수 없는 작업 단위이기 때문에 내부의 SQL문들이 모두 성공해야 한다.
  + 중간에 SQL문이 실패하면 지금까지의 작업을 모두 취소하여 아무 일도 없었던 것처럼 rollback 한다.
+ Consistency
  + transaction은 DB상태를 consistent 상태에서 또 다른 consistent상태로 바꿔줘야 한다.
  + constraints, trigger 등을 통해 DB에 정의된 rules을 transaction이 위반했다면 rollback 해야 한다.
  + transaction이 DB에 정의된 rule을 위반했는지는 DBMS가 commit 전에 확인하고 알려준다.
  + 그 외에 application 관점에서 transaction이 consistent하게 동작하는지는 개발자가 챙겨야 한다.
+ Isolation
  + 여러 transaction들이 동시에 실행될 때도 각각의 transaction이 마치 혼자 실행되는 것처럼 동작하게 만든다.
  + isolation은 엄격하게 구현하면 DB서버의 퍼포먼스가 줄어들기 때문에  DBMS는 여러 종류의 isolation level을 제공한다.
    + isolation level이 높으면 높을수록 보다 엄격하게 격리를 시켜서 다른 트랜잭션으로부터 영향을 받을 경우가 줄어들게 됨
    + 하지만 isolation이 엄격한 만큼 동시에 실행시킬 수 있는 동시성이 떨어지기 때문에 DB서버의 퍼포먼스는 줄어들게 된다. 
  + 개발자는 isolation level 중에 어떤 level로 transaction을 동작시킬지 설정할 수 있다.
  + concurrency control의 주된 목표가 isolation이다.
+ Durability
  + commit된 transaction은 DB에 영구적으로 저장한다.
  + 즉, DB system에 문제(power fail or DB crash)가 생겨도 commit된 transaction은 DB에 남아 있는다.
  + '영구적으로 저장한다' 라고 할 때는 일반적으로 '비휘발성 메모리(HDD, SSD, ...)에 저장함'을 의미한다.
  + 기본적으로 transaction의 durability는 DBMS가 보장한다. 





---



### INDEX

#### Index를 쓰는 이유

+ 특정 조건을 만족하는 튜플(들)을 빠르게 조회하기 위해
+ 빠르게 정렬(order by)하거나 그룹핑(group by) 하기 위해



#### Index 생성 sql 문법

+ 하나의 컬럼에 대해 Index를 생성할 때 (컬럼의 값이 중복을 허용 할때)

  + ~~~sql
    CREATE INDEX 인덱스명 ON 테이블명(컬럼명);
    ~~~

+ 여러개의 컬럼에 대해 Index를 생성할 때 (여러개의 컬럼이 and로 묶여서 튜플을 유니크하게 식별할 때 )

  + ~~~sql
    CREATE UNIQUE INDEX 인덱스명 ON 테이블명 (컬럼명1,컬럼명2);  //컬럼 순서 중요
    ~~~

<img width="732" alt="스크린샷 2023-04-12 09 07 35" src="https://user-images.githubusercontent.com/88477839/231314550-b56b54b8-5c04-42c2-be2f-c97430161933.png">



+ **테이블 생성과 동시에 인덱스 생성하기**

  <img width="715" alt="스크린샷 2023-04-12 09 10 14" src="https://user-images.githubusercontent.com/88477839/231314927-97360e11-d9c1-466a-aeba-71bc5294a09f.png">



+ **primary key에는 index가 자동 생성된다**



#### Index 조회 sql 문법

+ ~~~sql
  SHOW INDEX FROM 테이블명
  ~~~



#### Index 동작 방식

+ Index가 생성되면 Index가 B Tree  기반으로 정렬된다.
+ B Tree 기반으로 정렬이 되면 찾고자 하는 인덱스를 Binay search로 찾게되고, 시간 복잡도는 logN이다.



#### 특정 Index 선택 sql 문법

+ 하나의 컬럼에 대해 여러 인덱스가 생성되어있을 수 있다. (UNIQUE INDEX등)
+ 이 때 어느 인덱스를 사용할 것인지 선택할 수 있다.

+ 특정 인덱스를 사용할 것을 명시하기

  + ~~~sql
    SELECT * FROM 테이블명 USE INDEX (인덱스명)
    WHERE 컬럼명 = 값;
    ~~~

+ 특정 인덱스를 사용할 것을 권장하기

  + ~~~sql
    SELECT * FROM 테이블명 FORCE INDEX (인덱스명)
    WHERE 컬럼명 = 값;
    ~~~



#### INDEX는 많이 만들어도 괜찮을까?

+ table에 insert, update, delete 할 때 마다 index도 변경 된다.
  + 때문에 B Tree의 구조도 다시 정렬 되며 때문에 구조가 조정되는데 시간이 소요된다.
  + 인덱스가 많아지만 많아질수록 insert, update, delete 할 때 마다 오버헤드가 발생한다. 
+ Index가 생성되면 추가적인 저장 공간을 차지한다.
+ 때문에 불필요한 INDEX를 만들지 말아야 한다.



#### Covering index

+ 조회하는 attribute를 index가 모두 cover할 때
+ 조회 성능이 더 빠름 (원래 테이블까지 방문할 필요가 없어서)



#### Full scan이 Index보다 좋은 경우

+ table에 데이터가 조금 있을 때
+ 조회하려는 데이터가 테이블의 상당 부분을 차지할 때 



---

### functional dependency (함수 종속)

#### functional dependency란

+ 한 테이블에 있는 두 개의 attribute(s) 집합(set) 사이의 제약 (a constraint)
+ <img width="749" alt="스크린샷 2023-08-24 20 27 19" src="https://github.com/le-espiritu/TIL/assets/88477839/94291a6f-ca17-426e-9025-3156b11f7061">
  + 특징 : X값이 같다면 Y값도 같다.
  + X값에 따라 Y값이 유일하게 결정될 때 
    + 'X가 Y를 함수적으로 결정한다. (Functionally determine)'
    + 'Y가 X에 함수적으로 의존한다. (Functionally dependent)'
    + 라고 말할 수 있고, 두 집합 사이의 이러한 제약 관계를 
    + Functional dependency(FD) 라고 부른다.



#### Functional dependency(FD) 파악하기

+ 테이블의 스키마를 보고 의미적으로 파악해야 한다.
+ 즉, 테이블의 state를 보고 FD를 파악해서는 안된다.
  + 테이블의 특정 순간의 특정 상태(state)만 보고 functional dependency가 존재한다고 생각하면 안된다.

+ X가 Y를 함수적으로 결정한다고 해서 그 반대의 경우(Y가 X를 함수적으로 결정하는 경우)가 반드시 성립하는 것은 아니다.
  + 그렇다고해서 Y가 X를 함수적으로 결정하는 경우가 반드시 없는것도 아니다. (결정하는 경우도 있다.)



#### Functional dependency의 종류

+ Trivial functional dependency
  + X가 Y를 결정하는 함수 종속이 유효할때( X->Y ) , 만약 Y가 X의 부분집합이라면 X->Y 함수종속은 Trivial functional dependency이다.
    + ex) {a,b,c} -> {c} 는 trivial FD이다. 
    + ex) {a,b,c} -> {a,c} 는 trivial FD이다. 
    + ex) {a,b,c} -> {a,b,c} 는 trivial FD이다. 
+ Non-trivial functional dependency
  + X가 Y를 결정하는 함수 종속이 유효할때( X->Y ) , 만약 Y가 X의 부분집합이 아니라면 X->Y 함수종속은 non-trivial functional dependency이다.
    + ex) {a,b,c} -> {b,c,d} 는 non-trivial FD이다. 
    + ex) {a,b,c} -> {d,e} 는 non-trivial FD이다. 
      + 이 두 집합은 공통된 attribute가 하나도 없다. 이 경우 completely non-trivial FD라고 부른다.

+ Partial functional dependendy
  + when X->Y holds, if  'any proper subset of X'  can determine Y, then X -> Y is partial FD
  + X가 Y를 결정짓는 함수종속이 존재할때 X의 proper subset의 어떤 값 하나라도 Y를 여전히 함수적으로 결정할 수 있다면 partial functional dependency 라고 한다.
  + proper subset이란?
    + 집합 X의 proper subset은 X의 부분 집합이지만 X와 동일하지는 않은 집합이다.
    + 예를 들어, X={a,b,c} 일 때,
      + {a,c}, {a}, {}는 모두 X의 proper subset이다.
      + 반면에 {a,b,c}는 X의 proper subset이 아니다.
  + 예) {empl_id, empl_name} -> {birth_date} 는 partial FD이다.
    + proper subset인 {empl_id} 가 {birth_date}를 dertmine 할 수 있기 때문이다.
+ Full functional dependency
  + when X->Y holds, if  'every proper subset of X'  can NOT determine Y, then X -> Y is full FD
  + X가 Y를 결정짓는 함수종속이 존재할때 X의 모든 proper subset이 Y를 함수적으로 결정할 수 없다면 full functional dependency 라고 한다.
  + 예) when {stu_id, class_id} -> {grade} holds,
  + Because {stu_id}, {class_id}, {} can NOT determine {grade},
  + then this FD is full FD



---



### DB 정규화

#### 정규화(Normalization)란?

+ 데이터 중복과 insertion, update, deletion anomaly(변칙, 이례)를 최소화하기 위해 일련의 normal forms(NF)에 따라 relational DB를 구성하는 과정
  + Normal forms : 정규화 되기 위해 준수해야 하는 몇 가지 rule들이 있는데 이 각각의 rule을 normal form(NF)이라고 부른다.

+ 데이터베이스 정규화란 데이터베이스의 설계를 재구성 하는 테크닉이다.
+ 정규화를 통해 불필요한 데이터를 없앨 수 있고,
+ 삽입/갱신/삭제 시 발생할 수 있는 각종 이상현상들을 방지할 수 있다.
+ 테이블 간 중복된 데이터를 허용하지 않기 때문에
  + 무결성(Integrity)를 유지할 수 있으며
  + DB의 저장 용량 역시 줄일 수 있다.



#### DB 정규화 과정

+ <img width="694" alt="스크린샷 2023-08-25 22 09 46" src="https://github.com/le-espiritu/TIL/assets/88477839/75a0ec60-4231-4be7-9e4c-ba3d420498c7">
  + 1NF~BCNF : FD와 key만으로 정의되는 normal forms이다.
  + 3NF까지 도달하면 정규화 됐다고 말하기도 한다.
  + 보통 실무에서는 3NF 혹은 BCNF까지 진행한다.



#### 제 1 정규화 (1NF)

+ 제 1 정규화란 테이블의 컬럼이 원자값을 갖도록 하는 것이다.
+ attribute의 value는 반드시 나눠질 수 없는 단일한 값이어야 한다.
+ 1NF을 하는 과정에서 한 컬럼의 값을 원자적으로 나누는 과정에서 해당 컬럼을 제외하고 나머지 컬럼들은 모두 중복값을 같는 튜플이 하나 더 생겨나게 된다. 따라서 이를 구분짓기 위해 primary key를 변경하게 되고(기존 primary key에 컬럼 추가), 따라서 모든 non-prime attribute들이 key값에 partially dependent 하게되는 문제가 발생한다. 이를 해결하기 위해 2NF을 해준다. 
  + Non-prime attribute란 prime attribute가 아닌 attribute이다. 즉, 어떤 key에도 속하지 않은 attribute이다.
  + Prime attribute란 임의의 key에 속하는 attribute이다.




#### 제 2 정규화(2NF)

+ 모든 non-prime attribute는 모든 key에 fully functionally dependent 해야 한다. 
+ 제 1 정규화를 진행한 테이블에 대해 완전 함수 종속을 만족하도록 테이블을 분해하는 것
  + 완전 함수 종속은 기본키의 부분집합이 결정자가 되어선 안된다는 것을 의미한다.
+ 2NF부터는 테이블이 나눠진다. 



#### 제 3 정규화

+ 제 2 정규화를 진행한 테이블에 대해 이행적 종속을 없애도록 테이블을 분해하는 것
  + 이행적 종속이란 a -> b, b -> c가 성립할 때 a -> c가 성립되는 것을 의미한다.



#### BCNF 정규화

+ BCNF 정규화란 제 3 정규화를 진행한 테이블에 대해 모든 결정자가 후보키가 되도록 테이블을 분해하는 것이다.



#### 정규화 참고 자료

+ https://mangkyu.tistory.com/110
+ https://3months.tistory.com/193



---



### NoSQL 참고자료

+ https://www.fun-coding.org/mongodb_basic1.html#gsc.tab=0
+ https://chankim.tistory.com/5
+ NoSQL의 한 종류로 MongoDB가 있다.
  + MongoDB는 Document-Oriented DB이다.
  + MongoDB의 Document는 Json의 형태이다.
  + MongoDB의 Collection은 RDB의 Table에 대응된다.
  + MongoDB의 Document는 RDB의 row(레코드)에 대응된다.



### B+ Tree 참고자료

+ https://dataonair.or.kr/db-tech-reference/d-guide/sql/?mod=document&uid=366

+ https://zorba91.tistory.com/293
+ https://velog.io/@emplam27/%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-B-Plus-Tree



### 선택도 설명

선택도를 계산하는 방법에 따라 ''선택도가 높다'', ''선택도가 낮다''라는 두가지 표현으로 나뉘는 듯 하다.

+ 선택도 정의 - https://www.programmerinterview.com/database-sql/selectivity-in-sql-databases/
  + 위 정의에서 사용된 계산방법에 따르면 선택도가 높을 수록 인덱스를 사용하기에 적합하다.

+ https://yurimkoo.github.io/db/2020/03/14/db-index.html
  + 위 정의에서 사용된 계산방법에 따르면 선택도가 낮을 수록 인덱스를 사용하기에 적합하다.
+ 댓글 참고 - https://soft.plusblog.co.kr/87
