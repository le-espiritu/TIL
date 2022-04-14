# UI Component module



## 생성자 패턴

> 자바스크립트로 구현한 애플리케이션도 꽤 복잡해질 수 있습니다.
>
> 함수만으로 나누기 보다는, 어떠한 집합개념으로 모듈화를 할 필요가 있습니다.
>
> 객체지향 언어에서는 클래스라고 하는 것이 그것입니다.
>
> 자바스크립트도 객체 형태로 비슷한 속성과 행위를 묶어서 표현할 수 있는데, 좀 더 객체지향적인 방법으로 이를 표현할 수가 있습니다.
>
> 생성자 패턴을 통해서 그 방법을 이해해 보겠습니다.



### 자바 스크립트 객체 다시 이해하기

+ 아래와 같은 코드를 객체리터럴(Object literal)이라고 한다.

  ~~~javascript
  var healthObj = {
    name : "크롱",
    lastTime : "PM10:12",
    showHealth : function(){
      console.log(this.name+"님, 오늘은 " + this.lastTime + "에 운동을 하셨네요");
    }
  }
  
  healthObj.showHealth();
  ~~~

+ 만약 healthObj의 형태를 가진 여러개의 객체가 필요하다면 어떻게 할까? (예를 들어 크롱 대신 호눅스라는 이름)

  + healthObj2, healthObj3... 이렇게 구현해도 되지만, 비슷한 객체를 중복해서 만들어 두는건 이상하다.
  + 더군다나 각 객체마다 showHealth라는 메서드는 중복으로 들어가 있다.(메모리를 쓰는 차원에서도 효율적이지 않다. 또 코드의 중복으로 인해서 수정, 유지보수 할때 어려움을 겪을 수 있다.) 이를 개선할 필요가 있다.



### 객체를 동적으로 생성하는 방법

+ 객체를 동적으로 생성하는 방법은 아래처럼 함수를 이용하는 것이다.

  ~~~javascript
  function Health(name,lastTime){
    this.name=name;
    this.lastTime=lastTime;
    this.showHealth=function(){
      return this.name+"님 오늘은 " + this.lastTime+ "에 운동을 하셨네요";
      
    // return this; 가 숨겨져 있고, new 키워드가 부를 때는 이 return this가 내부에서 실행이 되는 것과 같이 동작한다. this는 어떤 객체.
    }
  }
    
  const h = new Health("달리기","10:12");
  
  // var o = Health("crong", "AM 10:10");
  //위와 같이 하면 아무 의미가 없고 o를 출력해도 undefined 가 나온다.
  //o는 Health의 반환값을 받아서 저장하는데 Health 함수에는 반환값이 지금 없다.
  // 하지만 new 키워드와 함께 사용하면 객체를 생성한다.
  ~~~

  + h는 객체이다. h안을 들여다 보면 어떻게 구성되어 있는지 알 수 있다.
  + Health함수를 한번 더 불러서 h2 객체를 만든다.

  ~~~javascript
  const h2 = new Health("걷기", "20:11");
  ~~~

  + 계속 이런식으로 객체를 만들어낼 수 있다.
  + Health 함수는 new키워드로 불리면서, 객체를 생성하는 함수역할을 한다.
  + 그래서 Health를 생성자(constructor)라고 한다.

  

+ 하지만 아직 문제가 하나 존재한다.

+ h와 h2에는 showHealth메서드가 여전히 중복해서 존재한다.

+ 이처럼 동일한 메서드가 여기저기 메모리 공간을 차지하는 것은 분명 자원 낭비이다.

  

### prototype으로 메서드를 같이 사용하기

+ 자바스크립트는 prototype이라는 공간을 통해서 객체간의 재사용 되는 것을 바라보게 할 수 있다.

![5-1-1_prototyp](https://user-images.githubusercontent.com/88477839/163306523-91505435-ee3f-4a0d-81de-92d485b693a8.jpeg)

+ prototype은 객체지향 언어의 상속(inheritance)와 비슷한 개념이다.

+ 위 그림에서 각 인스턴스(h,h2,h3)가 prototype이라는 같은 참조객체를 바라보고 있다.

+ 따라서 prototype의 어떤 속성을 변경하면 모든 인스턴스에게 공유된다.

  ~~~javascript
  function Health(name,lastTime){
    this.name=name;
    this.lastTime=lastTime;
  }
  
  Health.prototype.showHealth = function(){
      return this.name+"님 오늘은 " + this.lastTime+ "에 운동을 하셨네요";
    }
  
  const h = new Health("달리기","10:12");
  const h2 = new Health("걷기", "20:11");
  ~~~

  + 중복되는 showHealth 함수 코드를 prototype에 넣어놓는다.

    ~~~javascript
    h.showHealth === h2.showHealth;
    
    // => true
    ~~~

    + 같은 메모리 공간을 바라보고 있다.
    + 메모리 효율성이 좋다.
    + 보통 메서드들은 그래서 prototype에 많이 보관을 한다.



### 예제

+ 아래 코드를 보면 Health 함수 아래 prototype 객체가 존재하고, 이것에 showHealth 메서드를  속성으로 추가했다.

+ 이런 식으로 prototype 객체 안에 여러 가지 속성을 추가할 수 있다.

  ~~~javascript
  function Health(name, lastTime) {
    this.name = name;
    this.lastTime = lastTime;
  
  }
  
  Health.prototype.showHealth = function() {
      console.log(this.name + "," + this.lastTime);
  }
  
  const h = new Health("달리기", "10:12");
  console.log(h);  //크롬개발자도구를 열고 이 부분이 어떻게 출력되는지 확인해보세요
  h.showHealth();
  ~~~

+ 아래 코드처럼 여러 인스턴스를 만들어도 prototpye안의 showHealth는 같은 참조점을 바라보고 있는 것을 알 수 있다.

  ~~~javascript
  const h = new Health("달리기", "10:12");
  const h2 = new Health("걷기", "14:20");
  console.log(h.showHealth === h2.showHealth); //true
  ~~~

  

## 생성자패턴으로 TabUI만들기

> TabUI 동작 코드를 생성자패턴으로 구현해볼 수 있습니다.
>
> 이렇게 구현한 코드는 UI Component라고 말하곤 합니다.
>
> 생성자 패턴을 통해서 그 방법을 이해해 보겠습니다.



