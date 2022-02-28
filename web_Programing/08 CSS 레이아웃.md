# CSS - 레이아웃



### 엘리먼트가 배치되는 방식

+ 엘리먼트를 화면에 배치하는것을 layout작업 또는 Rendering 과정이라고도 한다.
+ 엘리먼트는 위에서 아래로 순서대로 블럭을 이루며 배치되는 것이 기본이다.
+ 웹사이트 배치는 다양하게 표현 가능해야 하기 때문에, 이를 다양한 방식으로 배치 할 수 있도록 css는 추가적인 속성을 제공한다.



### display (block, inline, inline-block)

+ #### 블록으로 쌓이는 엘리먼트 (dsiplay:block)

  + display 속성이 block이거나 inline-block인 경우 그 엘리먼트는 벽돌을 쌓듯이 쌓인다.
  + 화면에 위에서 아래로 쌓이듯이 채워진다.
  + \<div>는 display가 block속성이다.
    + Display : inline; 을 해주면 오른쪽으로 흐르게 할 수도 있다.

+ #### 옆으로 흐르는 엘리먼트 (display:inline)

  + display속성이 inline인 경우는 우측으로, 그리고 아래쪽으로 빈자리를 차지하며 흐른다.

  + inline속성의 엘리먼트는 높이와 넓이를 지정해도 반영이 되지 않는다.

    ~~~html
    <div>
      <span>나는 어떻게 배치되나요?</span>
      <span>좌우로 배치되는군요</span>
      <a>링크는요?</a>
      <strong>링크도 강조도 모우 좌우로 흐르는군요</strong>
    </div>
    ~~~

  + inline속성을 가진 태그들에 display :block; 을 해주면 블록형태로, ,위에서 아래로 쌓이게 된다.



### position(static, absolute, relative, fixed)

Positon 속성을 사용하면 상대적/ 절대적으로 어떤 위치에 엘리먼트를 배치하는 것이 수월하다. (다양한 배치, 특별한 배치를 하는 것이 다양해 진다.)

+ position 속성으로 특별한 배치를 할 수 있다.
  + Position 속성은 기본적으로 static이며 static은 그냥 순서대로 배치된다.
+ position : absolute는 기준점에 따라서 특별한 위치에 위치한다.
  + Top/left/right/bottom으로 설정한다.
  + absolute의 기준점은 상위 엘리먼트 중에 static이 아닌 position이 기준점이다.
  + 만약 상위 엘리먼트의 position이 static이면 기준점으로 삼지 않고 한단계 더 올라가서 기준점을 찾는다.
+ position : relative는 원래 자신이 위치해야 할 곳을 기준으로 이동한다.
+ position : fixed는 viewport(전체화면)좌측, 맨위를 기준으로 동작한다.



<img width="1280" alt="스크린샷 2022-02-28 13 12 42" src="https://user-images.githubusercontent.com/88477839/155922918-4f3e8bb4-7101-4f90-aac3-f33e5060904b.png">



### Float - 기본 배치에서 벗어나서 떠있기

+ float는 일반적인 배치에 따라서 배치된 상태에서 벗어난 형태로 특별히 배치된다.

+ 따라서 뒤에 block엘리먼트가 float된 엘리먼트를 의식하지 못하고 중첩되서 배치된다.

<img width="1173" alt="스크린샷 2022-02-28 15 25 01" src="https://user-images.githubusercontent.com/88477839/155935006-b487e768-141b-4fef-8a4f-551bb393a906.png">



### box-model - 하나의 블록엘리먼트는 box 형태이다.

+ 블록엘리먼트의 경우 box의 크기와 간견에 관한 속성으로 배치를 추가 결정한다.

+ margin, padding, border, outline으로 생성되는 것이다.

+ Box-shadow속성도  box-model에 포함지어 설명할 수 있다.

+ box-shadow는 border 밖에 테두리를 그릴 수 있는 속성이다.

+ 단축 표기법 - (아래 코드를 기준으로) 위는 25px, 오른쪽은 0px, 아래는 10px, 왼쪽은 3px

  ~~~css
  div{
  	padding : 25px 0px 10px 3px;
  }
  ~~~



### box-sizing과 padding

+ padding 속성을 늘리면 엘리먼트의 크기가 달라질 수 있다.

+ Box-sizing. 속성으로 이를 컨트롤 할 수 있다. 

+ Box-sizing속성을 border-box로 설정하면 엘리먼트의 크기를 고정하면서 padding값만 늘릴 수 있다.

  <img width="1169" alt="스크린샷 2022-02-28 15 43 45" src="https://user-images.githubusercontent.com/88477839/155936637-fac48245-e807-424e-ad4e-cc4392541ce2.png">



### layout 구현 방법

+ 전체 레이아웃은 float롤 잘 사용해서 2단, 3단 컬럼 배치를 구현한다.
+ 최근에는 css-grid나 flex속성등 layout을 위한 속성을 사용하기 시작했으며 브라우저 지원범위를 확인해서 사용하도록 한다.
+ 특별한 위치에 배치하기 위해서는 position : absolute를 사용하고, 기준점을 static이 아닌 relative로 설정한다.
+ 네비게이션과 같은 엘리먼트는 block엘리먼트를 inline-block 으로 변경해서 가로로 배치하기도 한다.(크기를 지정할 수 있으면서 좌우로 배치될 수 있게 한다.)
+ 엘리번트안의 텍스트의 간격과, 다른엘리먼트간의 간격은 padding과 margin속성을 잘 활용한다.





