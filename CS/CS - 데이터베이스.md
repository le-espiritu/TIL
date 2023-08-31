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
+ 1NF을 하는 과정에서 한 컬럼의 값을 원자적으로 나누는 과정에서 해당 컬럼을 제외하고 나머지 컬럼들은 모두 중복값을 갖는 튜플이 하나 더 생겨나게 된다. 따라서 이를 구분짓기 위해 primary key를 변경하게 되고(기존 primary key에 컬럼 추가), 따라서 모든 non-prime attribute들이 key값에 partially dependent 하게되는 문제가 발생한다. 이를 해결하기 위해 2NF을 해준다. 
  + Non-prime attribute란 prime attribute가 아닌 attribute이다. 즉, 어떤 key에도 속하지 않은 attribute이다.
  + Prime attribute란 임의의 key에 속하는 attribute이다.




#### 제 2 정규화(2NF)

+ 모든 non-prime attribute는 모든 key에 fully functionally dependent 해야 한다. 
+ 제 1 정규화를 진행한 테이블에 대해 완전 함수 종속을 만족하도록 테이블을 분해하는 것
  + 완전 함수 종속은 기본키의 부분집합이 결정자가 되어선 안된다는 것을 의미한다.
+ 2NF부터는 테이블이 나눠진다. 



#### 제 3 정규화

+ 제 2 정규화를 진행한 테이블에 대해 이행적 종속을 없애도록 테이블을 분해하는 것
  + 이행적 종속으로 인해 중복 데이터가 발생하고 이 상황을 해결하기위해 제 3 정규화를 진행한다. 
  + 이행적 종속이란 a -> b, b -> c가 성립할 때 a -> c가 성립되는 것을 의미한다. (transitive FD라고도 부름 )
  
+ if X->Y & Y -> Z holds, then X -> Z is transitive FD
  + unless either Y or Z is NOT subset of any key (Y와 Z가 그 어떤 키에 대해서도 부분집합이 아니어야 한다.)


+ 3NF : 모든 non-prime attribute는 어떤 key에도 transitively dependent 하면 안된다.
  + Non-prime attribute와 non-prime attribute 사이에는 FD가 있으면 안된다. 

#### BCNF 정규화

+ BCNF 정규화란 제 3 정규화를 진행한 테이블에 대해 모든 함수 종속 결정자가 후보키가 되도록 테이블을 분해하는 것이다.
+ 모든 유효한 non-trivial FD가 있을때 X -> Y는 X가 super key여야 한다.
  + X가 Y를 결정하는 함수 종속이 유효할때( X->Y ) , 만약 Y가 X의 부분집합이 아니라면 X->Y 함수종속은 non-trivial functional dependency이다.




#### denormalization

+ 역 정규화 
+ DB를 설계할 때 과도한 조인과 중복 데이터 최소화 사이에서 적정 수준을 잘 선택할 필요가 있다.



#### 정규화 참고 자료

+ https://mangkyu.tistory.com/110
+ https://3months.tistory.com/193



---

### B tree

#### 이진 탐색 트리(BST)

+ 모든 노드의 왼쪽 서브 트리는 해당 노드의 값보다 작은 값들만 가지고 모든 노드의 오른쪽 서브 트리는 해당 노드의 값보다 큰 값들만 가진다.
+ 자녀 노드는 최대 두 개까지 가질 수 있다.



#### B tree

+ 자녀 노드의 최대 개수를 늘리기 위해서 부모 노드에 key를 하나 이상 저장한다.
+ 부모 노드의 key들을 오름차순으로 정렬한다.
+ 정렬된 순서에 따라 자녀 노드들의 key 값의 범위가 결정된다.
+ B tree 방식을 사용하면 자녀 노드의 최대 개수를 입맛에 맞게 결정해서 쓸 수 있다.
+ B tree는 BST를 일반화한 tree라고 표현하기도 함 
+ 최대 몇 개의 자녀 노드를 가질 것인지가 B tree를 사용할 때 중요한 파라미터이다.
+ 모든 leaf 노드들은 같은 레벨에 있다. 
  + B tree는 balanced tree이다. (BST 는 balanced tree가 아니다.)
  + 때문에 검색 avg/worst case의 시간 복잡도가 O(log N)이다. 



#### B tree의 주요 네 가지 파라미터

+ M : 각 노드의 최대 자녀 노드 수
  + 최대 M개의 자녀를 가질 수 있는 B tree를 M차 B tree라 부른다.
+ M-1 : 각 노드의 최대 key 수
+ M/2를 올림한 값 : 각 노드의 최소 자녀 노드 수 
  + root node와 leaf node에서는 제외되는 조건 
+ M/2를 올림한 값 - 1 : 각 노드의 최소 key 수 
  + root node는 제외되는 조건 



#### B tree 노드의 key 수와 자녀 수의 관계

+ internal 노드의 key 수가 x개 라면, 자녀 노드의 수는 언제나 x+1개다. (리프노드는 제외)
+ 노드가 최소 하나의 key는 가지기 때문에 몇 차 B tree인지와 상관 없이 internal 노드는 최소 두 개의 자녀는 가진다.
+ M이 정해지면 root 노드를 제외하고 internal 노드는 최소 M/2를 올림한 값의 개수 만큼의 자녀 노드를 가질 수 있게 된다.
  + Ex) 5차 B tree의 경우 최소 3개의 자녀 노드를 가질 수 있게 된다. 



#### B tree 데이터 삽입

+ 추가는 항상 leaf 노드에 한다.
+ 노드가 넘치면 가운데 key를 기준으로 좌우 key들은 분할하고 가운데 key는 승진한다.



---

### B tree가 DB index로 사용되는 이유

#### B tree와 self-balancing BST의 시간복잡도 비교

+ <img width="761" alt="스크린샷 2023-08-27 10 44 17" src="https://github.com/le-espiritu/TIL/assets/88477839/14871b65-407c-442e-8fb7-1219d7da0ecc">
  + B tree와 self-balancing BST 의 시간복잡도가 동일한데 왜 B tree가 index로 사용되는지를 알기위해서는 computer system을 살펴봐야 한다.



#### computer system

+ <img width="722" alt="스크린샷 2023-08-27 10 50 07" src="https://github.com/le-espiritu/TIL/assets/88477839/5be0bf37-e007-40ea-b74c-955761e21da9">
+ database는 Secondary storage에 저장된다.



#### secondary storage의 특징

+ 데이터를 처리하는 속도가 가장 느리다.
+ 데이터를 저장하는 용량이 가장 크다.
+ block 단위로 데이터를 읽고 쓴다.
  + block이란 file system이 데이터를 읽고 쓰는 논리적인 단위이다.
  + block의 크기는 2의 승수로 표현되며 대표적인 block size는 4KB, 8KB, 16KB 등이 있다.
  + 때문에 불필요한 데이터까지 읽어올 가능성이 있다.



#### DB 관점에서 위 내용 정리

+ DB는 secondary storage에 저장된다.
+ DB에서 데이터를 조회할 때 secondary storage에 최대한 적게 접근하는 것이 성능 면에서 좋다.
+ block 단위로 읽고 쓰기 때문에 연관된 데이터를 모아서 저장하면 더 효율적으로 읽고 쓸 수 있다.



#### B tree 인덱스와 AVL tree(BST) 인덱스 성능 비교

+ <img width="734" alt="스크린샷 2023-08-27 11 02 42" src="https://github.com/le-espiritu/TIL/assets/88477839/48dbf6c3-000b-4cb1-a93a-2be4047ae6b7">
  + AVL tree 같은 경우 자식노드가 최대 2개이고, 노드 안에 데이터는 1개 밖에 없기 때문에 특정 조건의 값을 찾기 위해 노드의 값과 비교하는 과정에서 자녀 노드를 secondary storage에서 불러오기 위해서 접근하는 횟수가 증가한다.(1/2씩 밖에 탐색 범위를 줄여나가지 못하기 때문에)
  + 반면 B tree 같은 경우 자식 노드가 여러개이고(데이터를 찾을 때 탐색 범위를 빠르게 좁힐 수 있다.), 노드 안에 데이터 또한 여러개이기 때문에(secondary storage에서 불러운 노드 안에 데이터가 여러개이기 때문에 데이터 갯수 만큼 storage에 접근할 필요가 없음) secondary storage에 접근하는 횟수가 AVL tree보다 적다. 
    + 또한 그렇기 때문에 leaf 노드까지의 거리가 짧다. 
    + block  단위에 대한 저장 공간 활용도가 B tree가 더 좋다.



#### B tree 계열을 DB 인덱스로 사용하는 이유

+ DB는 기본적으로 secondary storage에 저장된다.
  + 때문에 같은 기능을 수행하더라도 secondary storage에 더 적게 접근하는 방식이 더 좋은 방식이다.
  + 왜냐하면 secondary storage는 데이터를 처리하는 속도가 가장 느리기 때문이다. 
+ B tree index는 self-balancing BST에 비해 secondary storage 접근을 적게 한다. 
+ B tree 노드는 block 단위의 저장 공간을 알차게 사용할 수 있다.



#### Hash index를 쓰지 않는 이유

+ hash index는 삽입/ 삭제 / 조회의 시간 복잡도가 O(1)이지만
+ Equality(=) 조회만 가능하고 범위 기반 검색이나 정렬에는 사용될 수 없다는 단점이 있다.



---

### 파티셔닝 / 샤딩 / 레플리케이션



#### partitioning

+ database table을 더 작은 table들로 나누는 것



#### partitioning의 종류

+ vertical partitioning
  + column을 기준으로 table을 나누는 방식
  + 정규화도 vertical partitioning의 한 종류이다. 
  + vertical partitioning 이후 테이블의 schema가 달라짐 
+ horizontal partitioning
  + row를 기준으로 table을 나누는 방식 
  + horizontal partitioning의 경우 schema가 그대로 유지됨
  + 한 테이블의 데이터가 엄청 많아질 경우 horizontal partitioning을 한다.
  + hash based horizontal partitioning
    + <img width="1200" alt="스크린샷 2023-08-27 12 22 23" src="https://github.com/le-espiritu/TIL/assets/88477839/d29e535e-1565-4b23-8bee-80435f4df10a">
      + hash function을 통해 나온 hash 값을 기준으로 테이블을 partitioning 함
      + 이 때 hash function 의 키 값으로 사용된 값을 partition key라고 부름
      + 가장 많이 사용될 패턴에 따라 partition key를 정하는 것이 중요하다. (만약 탐색 조건이 channel_id 라면, channel_id는 partition key가 아니기 때문에 두 테이블에 대해 풀 스캔을 해줘야 한다. )
      + 데이터가 균등하게 분배될 수 있도록 hash function을 잘 정의하는 것도 중요
      + Hash-based horizontal partitioning은 한번 partition이 나눠져서 사용되면 이후에 partition을 추가하기 까다롭다.



#### sharding

+ horizontal partitioning처럼 동작 (row을 기준으로 테이블을 나눔)
+ 하지만 차이점은 sharding의 경우 나눠진 각 partition이 독립된  DB 서버에 저장된다.
+ <img width="1238" alt="스크린샷 2023-08-27 12 31 56" src="https://github.com/le-espiritu/TIL/assets/88477839/341da093-426d-4b80-93fc-8c377d8c700f">
+ <img width="1221" alt="스크린샷 2023-08-27 12 33 07" src="https://github.com/le-espiritu/TIL/assets/88477839/764e7de0-23f0-49a0-a179-48e4426908e4">
+ sharding은 DB 서버의 부하(DB 서버의 CPU, 메모리 사용량등..)를 분산하는 효과가 있다. 
+ sharding을 하게되면 partition key는 shard key라고 부르고, 각 partition을 shard라고 부른다.



#### replication

+ DB를 복제해서 여러 대의 DB 서버에 저장하는 방식
+  <img width="1219" alt="스크린샷 2023-08-27 12 42 35" src="https://github.com/le-espiritu/TIL/assets/88477839/38de6310-9ce1-460c-a3c9-21095ab23051">
+ 만약 master db에 문제가 생기면 빠르게 slave DB로 전환해 서비스를 이어 나갈수 있다.
  + 장애 상황이 발생했을때 서비스에 최대한 타격이 없을 수 있도록, 계속해서 서비스가 유지될 수 있도록 하는 구성 및 특성을 High availability (고가용성) 라고 한다.
+ 또한 read 트래픽 같은 경우 secondary DB로 분산할 수 있기 때문에 서버 부하(load)를 낮출 수도 있다.



---

### DBCP(DB connection pool)



#### 백엔드 서버와 DB 서버의 통신 (DBCP를 쓰지 않을 때)

+ <img width="707" alt="스크린샷 2023-08-27 17 08 43" src="https://github.com/le-espiritu/TIL/assets/88477839/a733722c-e8c5-424e-9b8b-dd3e5add8e10">
  + 백엔드 서버와 DB 서버는 각각 서로 다른 컴퓨터에서 동작을 한다.
  + 따라서 쿼리를 요청하고 응답을 받는 과정은 결국 네트워크 통신을 하는 과정이다.
  + 백엔드 서버와 DB 서버는 TCP 기반으로 네트워크 통신을 하는데, TCP는 연결 지향적인 특징을 지니기 때문에 쿼리 요청을 하기전에 서버(노드)끼리 connection을 맺는 작업을 한다.
  + 커넥션을 열거나(3way 핸드 쉐이킹) 커넥션을 닫는(4way 핸드 쉐이킹)  작업은 시간이 많이 소요되는 작업이다.
  + 따라서 위 작업에서는 매번 connection을 열고 닫는 시간적인 비용이 발생하고
  + 이는 서비스 성능에 좋지 않다.



#### DBCP의 개념과 원리

+ <img width="658" alt="스크린샷 2023-08-27 17 22 28" src="https://github.com/le-espiritu/TIL/assets/88477839/f7184c7a-ab9d-477e-9e8f-19480a1e87bc">
  + DB 커넥션을 미리 여러개 맺어두고 이를 pool처럼 관리한다.
  + get connection - 커넥션 풀에서 일이 없어서 놀고 있는 커넥션을 하나 가져옴
  + close connection - 커넥션을 실제로 종료하는것이 아니라 빌려온 커넥션을 다시 커넥션 풀에 반납함
  + DBCP에서는 connection을 재사용하기 때문에 connection을 열고 닫는 시간이 절약 된다. 
  + 따라서 api 요청을 받아서 처리를 하고, 응답을 하는데에 까지 걸리는 시간이 단축되어 더 좋은 성능을 발휘하게 된다.



#### DBCP 설정

+ DB connection은 backend server와 DB 서버 사이의 연결을 의미하기 때문에 backend server와 DB 서버 각각에서의 설정 방법을 잘 알고 있어야 한다.

+ **DB 서버 설정**

  + max_connections : client와 맺을 수 있는 최대 connection 수
    + 백엔드 서버에서 트래픽이 몰리면 이 트래픽을 분산시키기위해 신규 백엔드 서버를 추가로 더 투입할 수도있다.
    + 이때 max_connections 의 수가 하나의 백엔드 서버의 커넥션만 맺을 수 있도록 설정되었다면 문제가 될 수 있다.
    + 때문에 이런 상황을 염려하여 max_connections를 설정해야 한다.
  + Wait_timeout
    + connection이 inactive 할 때(놀고 있을때) 다시 요청이 오기까지 얼마의 시간을 기다린 뒤에 close 할 것인지를 결정

+ **백엔드 서버에서의 DBCP 설정**

  + minimumIdle

    + pool에서 유지하는 최소한의 idle connection 수 (connection에 연결 되었지만 요청이 올때까지 대기하고 있는 유휴자원)

  + maximumPoolSize

    + pool이 가질 수 있는 최대 connection 수
    + idle과 active(in-use) connection 합쳐서 최대 수 

  + maxLifetime

    + pool에서 connection의 최대 수명
    + maxLifetime을 넘기면 커넥션이 idle일 경우 pool에서 바로 제거, active일 경우 pool로 반환된 후 제거 
    + pool로 반환이 안되면 maxLifetime 동작 안함 
      + pool로 반환을 잘 시켜주는 것이 중요하다.
    + maxLifetime은 DB의 connection time limit(Wait_timeout)보다 몇 초 짧게 설정해야 한다.
      + 그래야 API 요청을 받아서 처리하는 와중에 db connection이 끊기는 불상사를 방지할 수 있다. 

  + connectionTimeout

    + pool에서 connection을 받기 위해 대기하는 시간 

  + 특징

    + 만약 idle connection 수가 minimumIdle보다 작고, 전체 connection 수도 maximumPoolSize보다 작다면 신속하게 추가로 connection을 만든다.

    + 추가로 connection을 만들다 maximumPoolSize를 충족하게 되면, minimumIdle 수가 충족되지 않더라도 더 이상 추가로 connection을 만들지 않는다.

    + 따라서 maximumPoolSize가 minimumIdle보다 더 우선순위를 갖는다.

    + minimumIdle의 기본 값은 maximumPoolSize와 동일

      + (pool size가 고정이라는 의미 - 예를 들어 minimumIdle의 수가 2이고, maximumPoolSize가 4일때 는 minimumIdle의 조건을 맞춰주기 위해 maximumPoolSize에 도달할때까지 connection이 새로 만들어지면서 pool size가 가변적이었다.)

      

---

### NoSQL

#### RDB 단점

+ 경직된 스키마와 유연한 확장성의 부족
  + 기존 RDB에 새로운 컬럼을 추가하고자 할때 schema를 변경해야 하는데 이는 부담스러운 작업이다.

+ RDB에서는 중복 제거를 위해 정규화를 진행한다.
  + 그러나 전체 데이터를 read할때 과도한 조인이 생겨날 수 있으며 이는 성능을 하락시킨다.
+ Scale-out이 쉽지 않다.
  + RDB는 기본적으로 한 대의 컴퓨터에 저장된다.
  + 때문에 트래픽이 몰려오면 DB서버에 부하가 걸릴 수 있다. (CPU, 메모리 사용량 증가)
  + 따라서 이 경우에는 scale-up(성능이 더 좋은 컴퓨터로 바꿔줌)을 통해 database의 성능을 향상시킬 수 있다.
  + 그러나 scale-up은 비용이 많이 들 수도 있다.
  + Scale-out 같은 경우 서버를 여러개 추가하는 형식인데 RDB는 scale-out에 유연한 DB는 아니다.
  
+ transaction의 ACID가 DB 서버의 퍼포먼스에 어느 정도 안좋은 영향을 끼칠수도 있다.



#### NoSQL 등장 배경

+ 2000년대 인터넷의 보급과 SNS의 발달로 기존 RDBMS로는 감당하기 힘든 대규모의 트래픽이 발생하게됨
+ 그래서 기존 DB보다 high-throughput의 DB 가 요구됨.
+ 또한 low-latency(응답시간)이 요구됨 
+ 다양한 사용자들이 다양한 데이터를 발생시키다보니, 비정형 데이터가 증가되고 이를 처리할 필요성이 생기게됨
+ 때문에 새로운 형태의 Database가 필요하다는 니즈가 생겨났고 그래서 NoSQL이 등장하게 됨



#### NoSQL의 일반적인 특징

+ **Flexible schema (유연한 스키마)**

  + 기존  RDB에서는 테이블을 생성하면서 동시에 컬럼의 이름과 타입, 제약사항을 명시해줘야 했었다.

  + 그러나 NoSQL(mongoDB)에서는 collection(RDB에서의 테이블)을 생성해주기만 하면 된다. (따로 컬럼의 이름이나 타입을 명시해줄 필요가 없다.)

    + ~~~sql
      db.createCollection("student")
      ~~~

  + 데이터를 입력할때도 유연한 스키마의 특징이 잘 나타난다.

    + ~~~sql
      db.student.insertOne({
        name:"easycode"
      })
      ~~~

    + ~~~sql
      db.student.insertOne({
        name:"hope",
        address:{
           country : "Korea",
           state : "Seoul",
           city : "Gangnam-gu",
           street : "blurblur"
        },
        certificate : ["AWS solution architect"]
      })
      ~~~

      + 여기서 name, address, certificate 등이 RDB에서의 컬럼에 해당된다고 볼 수 있고,
      + 보다 시피 스키마 형태가 유연하다. 

  + NoSQL(mongoDB)의 데이터 조회

    + ~~~sql
      db.student.find({name:"easycode"})
      ~~~

      + JSON 형태로 조건을 넣어 데이터를 조회함

    + ~~~sql
      {
      	"_id" : ObjectId("63be100da5829c4031c849af"),
      	"name" : "easycode"
      }
      ~~~

      + 조회 결과
      + RDB에서는 이런 각각의 조회 결과를 row나 tuple이라고 부르는데
      + NoSQL(mongoDB)에서는 document라고 부른다. 

  + NoSQL에서는 스키마가 유연하게 때문에 각각의 document가 서로 다른 형태로 저장된다.

  + 때문에 RDB에서는 스키마 관리를 RDBMS가 해줬었는데 NoSQL에서는 application레벨(개발자)에서 스키마 관리를 해줘야 한다.

+ **NoSQL에서는 중복데이터를 허용한다.(join 회피)**

  + 따라서 application 레벨에서 중복된 데이터들이 모두 최신 데이터를 유지할 수 있도록 관리해야 한다.

+ **NoSQL은 scale-out에 최적화 되어있다.**(중복 데이터가 허용되기 때문에)

  + 서버 여러 대를 추가하는것이 어렵지 않다.
  + NoSQL은 서버 여러 대로 하나의 클러스터를 구성하여 사용한다.

+ **ACID의 일부를 포기하고 high-throughput, low-latency를 추구한다.**

+ 금융 시스템처럼 consistency가 중요한 환경에서는 사용하기가 조심스럽다.



#### Redis

+ Redis는 key-value 형태로 데이터를 저장하고 관리하는 in-memory database, cache다.

  + ~~~
    redis> SET name easycode
    ~~~

    + 여기서 name이 key, easycode가 value인 형태

  + ~~~
    redis > GET name
    "easycode"
    ~~~

    + key를 통해 value를 조회할 수 있다.

+ data type : strings, lists, sets, hashes, sorted sets,....

+ Hash-based sharded cluster

+ High Availability(replication, automatic failover)

  + failover : 장애극복 



#### Redis를 cache로 활용한 예제

+ <img width="743" alt="스크린샷 2023-08-28 13 07 07" src="https://github.com/le-espiritu/TIL/assets/88477839/bc4f2c14-d170-4ae4-99df-d552c60bd723">

  + 클라이언트로부터 백엔드 서버로 요청이 들어오면 백엔드 서버는 이 요청에 따른 데이터를 DB로부터 받아와야 한다.

  + 이때 만약 요청 트래픽이 몰린다면 DB서버에 부하가 걸릴수도 있다.

  + 이를 방지하기위해 DB서버 앞단에 Redis를 cache로 배치한다. 

  + Redis를 배치하면 백엔드 서버는 클라이언트로부터 요청이 왔을때 바로 DB서버로 가는것이 아니라

  + 먼저 Redis에 해당 요청에 해당하는 데이터가 있는지 확인한다. 

  + 확인하고 없으면 DB서버로 부터 해당 데이터를 받아오고

  + 이 데이터를 key-value 형태로 레디스에 저장한다.(저장하면서 얼마동안 이 데이터를 저장해놓을지 시간을 설정한다.)

  +  추후 동일한 요청이 오면 백엔드 서버는 이번에도 먼저 Redis를 확인하고 Redis에 해당 데이터가 있으면 빠르게 클라이언트에 응답할 수 있다.

  + **Redis는 memory cache라서 DB에 비해 매우 빠르다.**

    

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
