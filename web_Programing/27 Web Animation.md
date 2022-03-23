# Web Animation



## 웹 애니메이션 이해와 setTimeout 활용



### 애니메이션

+ 애니메이션은 반복적인 움직임의 처리입니다.
+ 웹 UI 애니메이션은 자바스크립트로 다양하게 제어할 수 있지만, 규칙적이고 비교적 단순한 방식으로 동작하는 방식은 CSS3의 transition과 transform속성을 사용해서 대부분 구현 가능합니다.
  + 그뿐만 아니라 자바스크립트보다 더 빠른 성능을 보장한다고 합니다.
  + 특히 모바일 웹에서는 CSS를 사용한 방법이 훨씬 더 빠릅니다.



### FPS

+ FPS (frame per second)는 1초당 화면에 표현할 수 있는 정지화면(frame) 수입니다.

+ 매끄러운 애니메이션은 보통 60fps 입니다.

+ 따라서 16.666밀리세컨드 간격으로 frame 생성이 필요한 셈이죠. (1000ms / 60fps = 16.6666ms)

+ 앞서 말한 것처럼, 애니메이션은 CSS의 transition속성으로 CSS 속성을 변경하거나, JavaScript로 CSS 속성을 변경할 수 있습니다.

  결국 이렇게 정의할 수 있습니다.

  - 간단하고 규칙적인 거 → CSS로 변경
  - 세밀한 조작이 필요한 거 → JavaScript로 변경

+ 성능만 봐서는 대체로 CSS로 변경하는 것이 빠릅니다.

+ 하지만 성능 비교를 통해서 가장 빠른 방법을 찾는 과정이 필요하기도 합니다.



### JavaScript 애니메이션

+ 자바스크립트로 애니메이션을 구현하려면, 규칙적인 처리를 하도록 구현하면 됩니다.

  다음과 같은 방법이 있습니다.

  - setInterval
  - setTimeout
  - requestAnimationframe
  - Animations API



### setInterval()

~~~javascript
const interval = window.setInterval(()=>{
  console.log('현재시각은',new Date());
},1000/60);

window.clearInterval(interval); // =>setInterval()을 멈추는 명령어
~~~

+ 주어진 시간에 따라서 함수 실행이 가능하다.(반복)

+ 하지만 지연 문제가 발생할 수 있다.

+ 아래 그림을 보면 제때 일어나야 할 이벤트 콜백이 지연되고, 없어지고 하는 것을 볼 수 있다.

+ 따라서 setInterval을 사용한다고 해서 정해진 시간에 함수가 실행된다고 보장할 수 없다.

  ![3-4-1_setInterval](https://user-images.githubusercontent.com/88477839/159616470-62265dbc-35ca-407e-ba88-c9f94ebf2a3f.png)

+ 일반적으로 setInterval을 사용하는 애니메이션 구현을 잘 하지 않는다.



### setTimeout

+ setTimeout으로 recursive하게 구현하기 (recursive - 반복되는)

  ~~~javascript
  setTimeout(()=>{
    console.log('현재시각은', new Date());
  },500);
  ~~~

  + 애니메이션을 구현하려면 재귀호출을 하면 된다.

  ~~~javascript
  let count = 0;
  function animate(){
    setTimeout(()=>{
      if(count >= 20) return;
      console.log('현재시각은', new Date());
      count++;
      animate(); // 자기 자신을 부름 => setTimeout이라는 콜백 함수는 콜 스택에서 비워지게 된다. => stackoverflow 같은 현상이 일어나지 않는다.
    },500);
  }
  animate();
  ~~~

+ setTimeout도 엄밀하게는 어떤 이유로 인해 제때에 원하는 콜백함수가 실행되지 않을 수 있다.

+ 결국 setinterval과 같은 문제가 발생할 수도 있긴하다.

+ 하지만 그럼에도 setTimeout은 매 순간 timeout을 조절할 수 있는 코드구현으로 컨트롤 가능한 부분이 있다는 점이 setinterval과 큰 차이라고 할 수 있다.



## requestAnimationFrame 활용

> setTimeout이나 setInterval을 사용해서 연속적인 함수 호출로 애니메이션을 구현하는 방법은 약간의 delay가 발생하는 문제가 있다.
>
> 이들 함수는 애니메이션을 위해서 생겨난 기능은 아니다.
>
> 애니메이션 구현을 위해서는 끊김없이 부드럽게 처리가 돼야 하는데, 다행히도 이를 위한 메서드를 브라우저가 제공하고 있다.



### requestAnimationFrame

+ setTimeout은 animation을 위한 최적화된 기능이라 보기 어렵다.
+ animation주기를 16.6 미만으로 하는 경우 불필요한 frame 생성이 되는 등의 문제가 생긴다.
+ 그래서 대안으로 requestAnimationFrame이 탄생되었다.

~~~javascript
var count = 0;
var el = document.querySelector(".outside");
es.style.left="0px";

function run(){
  if(count>70)return;
  count=count+1;
  el.style.left = parseInt(el.style.left)+count+"px";
  requestAnimationFrame(run);
}

requestAnimationFrame(run);
~~~

+ 먼저 requestAnimationFrame을 한번 실행시켜줘야 한다.
+ 그 이후에 특정 조건이 될 때까지(함수의 탈출 조건) 계속 함수를 연속적으로 호출하는 것이다.
+ 이렇게 requestAnimationFrame함수를 통해서 원하는 함수를 인자로 넣어주면, 브라우저는 인자로 받은 그 비동기 함수가 실행될 가장 적절한 타이밍에 실행시켜준다.
+ 우리는 어느 정도 브라우저를 믿고 이 함수를 전달해주는 것이다.
+ canvas, svg를 사용하면서 그래픽 작업을 하게 될 때 복잡한 애니메이션을 다룰 필요가 생길 수 있다.
  + 자바스크립트로 복잡한 애니메이션처리를 처리해야 할 때 requestAnimationFrame은 꽤 유용하게 쓰일 수 있다.



## CSS3 transition 활용



