# DFS 알고리즘

### 개념

+ DFS : Depth-first search(깊이 우선 탐색)
  + 자식을 우선으로 탐색하는 방법 (ex- 자식의 자식의 자식......)

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
        	// 링크드리스트가 아니라 셋으로 해줘도 되지 않을까??  라는 의문이 들었지만
        	// dfs나 bfs를 구현하여 인접노드를 순회하는 과정에서
        	// 좀더 간편한 코드를 사용하기 위해(확장된 for문) 아마도 링크드리스트 사용한것 같음 
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
    	
    	// 두 노드의 관계를 정해주는 함수
    	// 두 노드를 간선으로 이어준다.
    	void addEdge(int i1, int i2){ 
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        
        // 서로 인접한 노드로 추가해준다.
        if(!n1.adjacent.contains(n2)){
          n1.adjacent.add(n2);
        }
        if(!n2.adjacent.contains(n1)){
          n2.adjacent.add(n1);
        }
        
      }
    
    	void dfs(){
        dfs(0); // dfs()를 인자없이 호출하면 0번 인덱스부터 호출
      }
    
    	void dfs(int index){
        Node root = nodes[index];
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        root.marked = true;
        while(!stack.isEmpty()){
          Node r = stack.pop();
          for(Node n : r.adjacent){
            if(n.marked==false){
              n.marked = true;
              stack.push(n);
            }
          }
          visit(r);
        }
      }
    
    	// 재귀호출을 활용한 dfs 함수 구현
    	void dfsR(Node r){
        if (r == null) return; // 받은 Node가 null일때는 그냥 나간다. 
        r.marked = true;
        visit(r); // 자식들을 호출하기전에 데이터를 출력함 
        for (Node n : r.adjacent){
          if(n.marked == false){
            dfsR(n);
          }
        }
      }
    	void dfsR(int index){
        Node r = nodes[index];
        dfsR(r);
      }
    	void dfsR(){
        dfsR(0);
      }
    
    	void visit(Node n){
        System.out.print(n.data + " ");
      }
  }
  ~~~

+ 위 코드를 테스트

  ~~~java
  public class Test{
    public static void main(String[] args){
      // 그래프를 만들고 노드간 간선을 이어준다.
      Graph g = new Graph(9);
      g.addEdge(0, 1);
      g.addEdge(1, 2);
      g.addEdge(0, 3);
      g.addEdge(2, 4);
      g.addEdge(2, 3);
      g.addEdge(3, 4);
      g.addEdge(3, 5);
      g.addEdge(5, 6);
      g.addEdge(5, 7);
      g.addEdge(6, 8);
      
      g.dfs()
    }
  }
  
  // dfs(0) 결과 
  // 0 1 3 5 7 6 8 4 2
  ~~~

  

### 재귀 함수

+ DFS는 재귀함수를 활용하여 구현할 수 있다.

+ 재귀함수란?
  + 자기 자신을 다시 호출하는 함수
  + 주의할점
    + 재귀 함수가 종료되는 시점 반드시 명시
    + 재귀 함수의 깊이가 너무 깊어지면 Stack over flow
      + 함수를 호출하고 관리하는 메모리는 Stack으로 이루어져있다. 
      + 함수에서는 다른 함수를 호출할수도 있고, 해당함수의 호출이 종료되면, 그 함수를 호출했던 함수로 돌아가야하기 때문에 LIFO구조의 Stack으로 이루어진것이다. 
      + 그래서 이 Stack이 넘쳤다, 메모리가 넘쳤다 라는 의미에서 Stack over flow란 표현 사용.
  + DFS, 백트래킹에서 주로 사용



### 시간 복잡도

+ O(V+E)
+ Vertex (node) + edge(간선)