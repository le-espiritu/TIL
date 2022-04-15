# 객체지향 JavaScript 구현



## 배열의 함수형 메소드



### for vs forEach

+ 이런 데이터가 있다고 하겠다.

  ~~~javascript
  var data = [{title : "hello",content : "간지철철", price : 12000},
              {title : "crong",content : "괜춘한 상품", price : 5500},
              {title : "codesquad",content : "쩌는상품", price : 1200}];
  ~~~

+ for문과 forEach를 사용하겠다.

  ~~~javascript
  for(var i =0; i<data.length;i++){
    console.log(data[i].title, data[i].price);
  }
  
  data.forEach(function(value){
    console.log(value.tile,value.price);
  })
  ~~~

  + forEach문은 전체 배열을 다 돈다.
  + forEach에는 더이상 i++이나 length를 판단하는 코드가 필요 없다. 그만큼 실수를 줄일 수 있고, 코드는 더 보기 좋다.



### map

+ map메서드는 함수에서 정의한 방법대로 모든 원소를 가공해서 새로운 배열을 반환한다.

  ~~~javascript
  var filteredData = data.map(function(v){
  	return v.price*1.1. //가격을 10% 인상 , v는 배열의 원소를 말함
  });
  
  //
  ~~~

  + map을 이용하면 원본 배열을 유지한 채 새로운 배열을 만들 수 있다.



### filter

+ filter메서드는 함수에서 정의한 조건에 맞는 원소만 추려서, 새로운 배열을 반환한다.

  ~~~javascript
  var filteredData = data.filter(function(v){
    return v.price>5000; // 5000원 이상만 추출
  }) 
  
  filteredData
  
  // 결과
  //[
  //	{title:"hello", content: "간지철철", price:12000},
  //	{title:"crong", content: "괜춘한 상품", price: 5500}
  //]
  ~~~



### map,filter 체이닝을 통해 활용하기

+ 가격이 5000원이 넘으면 가격을 문자열로 바꾸기

  ~~~javascript
  var filteredData = data.filter(function(v){
    return v.price>5000; // 5000원 이상만 추출
  }).map(function(v){
    var obj = {title : v.title, content:v.content, price:v.price+""};
    return obj;
  }) 
  ~~~

  

## 객체 리터럴과 this

> 자바스크립트 객체를 활용하면 비슷한 행위를 하는 코드를 묶어서 모듈화를 간단히 만들 수 있다.



### 자바스크립트 객체의 활용

+ 자바스크립트 객체는 사실 객체리터럴 그 자체이며 객체를 쉽게 만들 수 있다.

  ~~~javascript
  var healthObj = {
  	name : "달리기",
    lastTime : "PM10:12",
    showHealth : function(){
      console.log(this.name + "님, 오늘은 " + this.lastTime+ "에 운동을 하셨네요");
    }
  }
  
  healthObj.showHealth(); // .이 있으면 객체다. 그러니까 console도 객체다.
  ~~~

  + 위 코드에서 this는 healthObj를 가르킨다.



### this

+ 객체안에서의 this는 그 객체 자신을 가리킨다.

+ 참고로,  ES6에서는 객체에서 메서드를 사용할때 'function' 키워드를 생략할 수 있다.

  ~~~javascript
  const obj = {
    getName(){
      return this.name;
    },
    setName(name){
      this.name = name;
    }
  }
  
  obj.setName("crong");
  const result = obj.getName();
  ~~~



### this 좀 더 알아 보기

+ JavaScript에는 전역스크립트나 함수가 실행될 때 실행영역(Execution context)이 생성된다. 실제 실행은 stack공간에 올라가서 실행된다.

+ 모든 context에는 참조하고 있는 객체(thisBinding이라함)가 존재하는데,현재 context가 참조하고 있는 객체를 알기 위해서는 this를 사용할 수 있다.

+ 다시 말해, 함수가 실행될때 함수에서 가리키는 this 키워드를 출력해보면 context가 참조하고 있는 객체를 알 수 있다.

  ~~~javascript
  function get(){
    return this;
  }
  
  get(); // window. 함수가 실행될때의 컨텍스트는 window를 참조한다.
  new get(); // object. new키워드를 쓰면 새로운 object context가 생성된다.
  ~~~

  

## bind메소드로 this 제어하기

### this가 달라지는 경우

~~~javascript
var healthObj = {
  name : "달리기",
  lastTime : "PM10:12",
  showHealth : function(){
    setTimeout(function(){
      console.log(this.name+ "님, 오늘은 " + this.lastTime+ "에 운동을 하셨네요");
    }, 1000)
  }
}
healthObj.showHealth();
~~~

+ 위 코드는 비동기 상황에서 일어나는 것으로, showHealth는 바로 출력되지 않고, 1초 뒤에 결과를 출력한다.

+ 위 함수에서 this는 healthObj를 가리키는 것이 아니다.

  + 위 함수에서 this는 Window를 가리킨다.
  + showHealth는 이미 반환이 된 이후이기 때문이다.

+ ES6의 arrow함수를 사용하는 경우에 this는 healthObj를 그대로 가리키고 있다.

  ~~~javascript
  var healthObj = {
    name : "달리기",
    lastTime : "PM10:12",
    showHealth : function(){
      setTimeout(()=>{
        console.log(this.name + "님, 오늘은" + this.lastTime + "에 운동을 하셨네요");
      },500);
    }
  }
  
  healthObj.showHealth();
  //달리기님, 오늘은 PM10:12에 운동을 하셨네요
  ~~~

  + arrow함수는 그 함수가 속해있는 것의 컨텍스트를 유지하면서 동작된다.
  + 그래서 arrow함수에서는 bind를 쓸일이 별로 없다.

### bind method

+ bind()는 새로운 함수를 반환하는 함수이다.

+ bind함수의 첫번째 인자로 this를 주어, 그 시점의 this를 기억하고 있는 새로운(바인드된) 함수가 반환된다.

+ bind에 인자를 주지 않으면 global(window)객체가 바인딩 된다.

  ~~~javascript
  var healthObj={
    name:"달리기",
    lastTime:"PM10:12",
    showHealth:function(){
      setTimeout(function(){
        console.log(this.name+"님, 오늘은"+this.lastTime+"에 운동을 하셨네요");
      }.bind(this),1000) // 여기 bind(this)에서 this는 healthObj를 가르킴
    }
  }
  
  healthObj.showHealth();
  ~~~

  + setTimeout의 콜백 함수 에서 this가 healthObj를 가르키게 할려면 bind()를 사용하면 된다.

  + 위 코드에서 bind(this)는 콜백함수 바깥에 위치하고 있기 때문에 여기서 this는 healthObj를 가르키고 있는 것이다. 즉 아직은 showHealth가 실행되고 있는 공간인 것이다.

  + ~~~
    function(){
          console.log(this.name+"님, 오늘은"+this.lastTime+"에 운동을 하셨네요");
        }.bind(this)
    ~~~

    + 함수 뒤에 .을 붙이고 있다.
    + 함수도 .이 붙는 순간 객체로써 동작한다. (Function 객체로 변한다.)
    + bind()는 function native객체에 들어 있는 메서드이다.



