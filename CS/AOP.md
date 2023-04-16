# AOP



### AOP란?

+ Aspaect Oriented Programming : 관점 지향 프로그래밍
+ 어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 모듈화 하는 프로그래밍 기법
  + 핵심적인 관점 : 비즈니스 로직
  + 부가적인 관점 : 핵심 로직을 실행하기 위해 행해지는 데이터베이스 연결, 로깅, 파일 입출력, 트랜잭션과 같은 부가적인지만 필수적인 부분



### AOP는 흩어진 관심사를 모듈화 할 수 있는 프로그래밍 기법이다.

+ Crosscutting Concnerns : 흩어진 관심사
+ 여러 곳에서 나타나는 공통적인 코드 부분들을 흩어진 관심사라고 하고 AOP는 흩어진 관심사를 모듈화한다는 의미이다. 
  + 예를 들어 A,B,C 라는 클래스들이 있고 이 클래스 안에서 핵심 로직은 아니지만 반드시 필요한 부가적인 코드가 동일하게 존재한다면, 이 각 클래스에서 동일하게 존재하는 부가적인 코드를 모듈화 하는 것이다.



### 스프링 AOP

+ 스프링에서 제공하는 스프링 AOP는 프록시 기반의 AOP 구현체이다.
  + 프록시(Proxy)는 대신, 대리 한다는 의미이다.
+ A라는 클래스 타입의 Bean을 만들 때 A 타입의 Proxy Bean을 만들어 Proxy Bean이 Aspect 코드를 추가하여 동작한다.
+ 프록시 패턴
  + 프록시 패턴에는 interface가 존재하고 Client는 이 interface 타입으로 Proxy 객체를 사용한다.
  + Proxy 객체는 기존의 타겟 객체를 참조한다.
  + Proxy 객체와 기존의 타겟 객체의 타입은 같고, Proxy는 원래 할 일을 가지고 있는 Real Subject를 감싸서 Client의 요청을 처리한다.



### AOP 참고 자료

+ AOP 개념
  + https://code-lab1.tistory.com/193
+ Spring AOP 사용하기
  + https://dev-jejeb.tistory.com/27
+ AOP가 필요한 이유
  + https://gmoon92.github.io/spring/aop/2019/02/09/why-used-aop.html