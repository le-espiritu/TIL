# BFS 알고리즘

### 개념

+ BFS : Breadth-first-search (너비 우선 탐색)
+ BFS는 그래프 탐색의 한 방법이다.
+ 그래프 탐색 : 어떤것들이 연속해서 이어질때, 모두 확인하는 방법 

### BFS 알고리즘 유튜브 설명

+ https://www.youtube.com/watch?v=_hxFgg7TLZQ



### BFS 알고리즘 자바 구현 코드

+ BFS 는 자료구조중 큐를 활용한다.

+ 참고 자료 - https://scshim.tistory.com/241

+ 위 유튜브 강의를 참고하여 구현한 코드

  ~~~java
  
  class Graph{
    class Node{
      int data;
      LinkedList<Node> adjacent;
      boolean marked; // 방문했는지 마킹하는 플래그
      Node (int data){ // 노드의 생성자
        this.data = data;
        this.marked = false;
        adjacent = new LinkedList<Node>();
      }
    }
    Node[] nodes; // 그래프에는 노드들을 저장할 배열이 필요하다.
    Graph(int size){ // 그래프 생성자
      nodes = new Node[size];
      for(int i =0; i< size; i++){
        nodes[i] = new Node(i);
      }
    }
    
    void addEdge(int i1, int i2){// 두 노드의 관계를 저장하는 함수
      Node n1 = nodes[i1];
      Node n2 = nodes[i2];
      if(!n1.adjacent.contains(n2)){
        n1.adjacent.add(n2);
      }
      if(!n2.adjacent.contains(n1)){
        n2.adjacent.add(n1);
      }
    }
    
    void bfs(){ // 인자없이 호출되면 0부터 시작하도록 -> 메서드 오버로딩 활용
      bfs(0);
    }
    void bfs(int index){
      Node root = nodes[index];
      Queue<Node> queue = new Queue<Node>();
      queue.enqueue(root); // 큐에 데이터 추가
      root.marked = true; // 큐에 추가/방문 했다고 마킹해줌.
      
      while(!queue.isEmpty()){ // 큐가 비워질 때까지 반복
        Node r = queue.dequeue();
        for(Node n : r.adjacent){ // 큐에서 꺼내온 노드들의 인접한 노드들 
          if(n.marked == false){ // 큐에 방문한적 없든 노드들인지 확인 
            n.marked = true;
            queue.enqueue(n);
          }
        }
        visit(r); //큐에서 꺼낸 노드들(큐에 방문했었던 노드들)을 출력하는 함수 호출 
      }
      
    }
    
    
    void visit(Node n){ // 큐에 방문했던 노드들을 출력하는 함수 
      System.out.print(n.data + " ");
    }
    
  }
  ~~~

+ 위 코드를 테스트

  ~~~java
  public class Test{
    public static void main(String[] args){
      Graph g = new Graph(9);
      g.addEdge(0,1);
      g.addEdge(1,2);
      g.addEdge(1,3);
      g.addEdge(2,4);
      g.addEdge(2,3);
      g.addEdge(3,4);
      g.addEdge(3,5);
      g.addEdge(5,6);
      g.addEdge(5,7);
      g.addEdge(6,8);
      
      g.dfs();
    }
  }
  
  // 실행결과 -> 0,1,3,5,7,6,8,4,2 
  ~~~

  

