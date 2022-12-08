# MST

### 개념

+ MST : Minimum Spanning Tree

+ Spanning Tree : 모든 노드가 연결된 트리

+ MST : 최소의 비용으로 모든 노드가 연결된 트리 

+ MST 푸는 방법 : Kruskal or Prim

  + Kruskal : 전체 간선 중 작은것부터 연결

  + Prim : 현재 연결된 트리에 이어진 간선중 가장 작은것을 추가 

    + 프림 알고리즘은 무향 연결 그래프가 주어질 때, '최소 스패닝 트리' 라고 부르는 서브 그래프를 찾는 알고리즘이다.

    

+ Prim 구현방법

  + **step 0)** 임의의 정점을 선택하여 비어있는 T에 포함시킨다. (이제 T는 노드가 한 개인 트리. )
  + **step 1)** T 에 있는 노드와 T 에 없는 노드 사이의 간선 중 가중치가 최소인 간선을 찾는다.
  + **step 2)** 찾은 간선이 연결하는 두 노드 중, T 에 없던 노드를 T에 포함시킨다. 

  ​        (step 1에서 찾은 간선도 같이 T에 포함된다.)

  + **step 3)** 모든 노드가 T 에 포함될 때 까지, 1,2 를 반복한다.
  + 위 과정을 통해 만들어진 최후의 T는 MST가 된다.
  + 참고 설명 
    + https://www.weeklyps.com/entry/%ED%94%84%EB%A6%BC-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Prims-algorithm
    + https://4legs-study.tistory.com/112

  

+ Prim을 구현하기 위해 heap자료구조를 활용한다.

  + 최대값, 최소값을 빠르게 계산하기 위한 자료구조
  + 이진 트리 구조
  + 처음에 저장할때부터 최대값 or 최소값 결정하도록 