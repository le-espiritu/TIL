# CS - 데이터베이스



### 참고 자료

+ https://www.nossi.dev/interview/cs/db



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
