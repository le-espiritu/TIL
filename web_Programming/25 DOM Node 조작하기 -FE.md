# DOM API활용



## DOM Node 조작하기

### DOM 조작하기 API

documet. 으로 사용할 수 있는 APIs : [링크 바로가기](https://www.w3schools.com/jsref/dom_obj_document.asp)

element. 으로 사용할 수 있는 APIs : [링크 바로가기](https://www.w3schools.com/jsref/dom_obj_all.asp)

 

### DOM 엘리먼트 오브젝트

**몇 가지 유용 DOM 엘리먼트 속성**

- **tagName** : 엘리먼트의 태그명 반환 (tagName이 아니라 nodeName인듯)
- **textContent** : 노드의 텍스트 내용을 설정하거나 반환
- **nodeType** : 노드의 노드 유형을 숫자로 반환

 

**DOM 탐색 속성**

- childNodes
  - 엘리먼트의 자식 노드의 노드 리스트 반환(텍스트 노드, 주석 노드 포함)
  - [childNodes 예제](https://jsbin.com/qabuciz/edit?html,js,console,output)
- firstChild
  - 엘리먼트의 첫 번째 자식 노드를 반환
  - [firstChild 예제](https://jsbin.com/fuconuk/1/edit?html,js,console,output)
- firstElementChild
  - 첫 번째 자식 엘리먼트를 반환
  - [firstElementChild 예제](https://jsbin.com/retoses/2/edit?html,js,console,output)
- parentElement
  - 엘리먼트의 부모 엘리먼트 반환 
  - [parentElement 예제](https://jsbin.com/jonumig/2/edit?html,js,console,output)
- nextSibling
  - 동일한 노드 트리 레벨에서 다음 노드를 반환 
  - [nextSibling 예제](https://jsbin.com/jonumig/6/edit?html,js,console,output)
- nextElementSibling
  - 동일한 노드 트리 레벨에서 다음 엘리먼트 반환
  - [nextElementSibling 예제](https://jsbin.com/podawep/2/edit?html,js,console,output)

 

**DOM 조작 메소드**

- removeChild()
  - 엘리먼트의 자식 노드 제거 
  - [removeChild 예제](https://jsbin.com/lexobe/13/edit?html,js,console,output)
- appendChild()
  - 마지막 자식 노드로 자식 노드를 엘리먼트에 추가
  - [appendChild 예제](https://jsbin.com/wunocen/5/edit?html,js,console,output)
- insertBefore()
  - 기존 자식노드 앞에 새 자식 노드를 추가
  - [insertBefore 예제](https://jsbin.com/xogutix/5/edit?html,js,output)
- cloneNode()
  - 노드를 복제
  - [cloneNode 예제](https://jsbin.com/puyeled/3/edit?html,js,output)
- replaceChild()
  - 엘리먼트의 자식 노드 바꿈
  - [replaceChild 예제](https://jsbin.com/rumadi/8/edit?html,js,output)
- closest()
  - 상위로 올라가면서 가장 가까운 엘리먼트를 반환
  - [closest 예제](https://jsbin.com/rumadi/13/edit?html,js,console,output)

 

**HTML을 문자열로 처리해주는 DOM 속성 / 메소드**

- innerText
  - 지정된 노드와 모든 자손의 텍스트 내용을 설정하거나 반환
  - [innerText 예제](https://jsbin.com/sukihiw/6/edit?html,js,output)
- innerHTML
  - 지정된 엘리먼트의 내부 html을 설정하거나 반환
  - [innerHTML 예제](https://jsbin.com/sutejo/3/edit?html,js,output)
- insertAdjacentHTML()
  - HTML로 텍스트를 지정된 위치에 삽입
  - [insertAdjacentHTML() 예제](https://jsbin.com/puwoqov/4/edit?html,js,output)

