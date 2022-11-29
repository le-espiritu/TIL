# DFS 알고리즘

### 개념

+ 깊이 우선 탐색
+ 그래프 탐색 : 어떤것들이 연속해서 이어질때, 모두 전부 다 확인하는 방법 



### DFS 알고리즘 유튜브 설명

+ https://www.youtube.com/watch?v=_hxFgg7TLZQ



### DFS 알고리즘 자바 구현 코드

+ DFS는 자료구조중 스택을 활용한다.

+ DFS를 구현할때 재귀호출을 이용하면 코드가 간결하고 세련되어진다.

+ 위 유튜브 강의를 참고하여 구현한 코드

  ~~~java
  class Graph{
      class Node{
          int data;
          LinkedList<Node> ajgacent;
          boolean marked;
          Node(int data){
              this.data = data;
              this.marked = false;
              adjacent = new LinkedList<Node>();
          }
      }
      Node[] nodes; // 그래프에는 노드들을 저장할 배열이 필요하다.
      Graph(int size){
          nodes = new Node[size];
          for(int i =0; i<size; i++){
              nodes[i] = new Node(i);
          }
      }
      
      
  }
  ~~~

  