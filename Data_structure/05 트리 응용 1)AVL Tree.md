# 트리 응용 (AVL & RB) - AVL Tree



## 1-1 AVL 트리 소개

> 스스로 균형을 잡는 이진 트리 자료 구조인 AVL트리에 대해 살펴보자



### AVL트리

+ AVL 트리는 스스로 균형을 잡는 이진 탐색 트리이다.

+ AVL 트리에서는 왼쪽과 오른쪽의 높이 차이는 항상 1보다 작거나 같아야 한다.

+ 높이의 차이가 1보다 커지면 규칙을 어기게 되는 것이다.

  ![mceclip0 (2)](https://user-images.githubusercontent.com/88477839/173573574-7065bcd8-42a2-4b3f-8055-2f4701d4a560.png)

  + 문제를 일으킨 노드의 조부모 노드를 회전시킨다.



## 1-2 노드

> AVL트리의 노드에 대해 살펴보자



### 노드

+ **AVL트리의 노드**를 만드는 코드는 다음과 같다. left, right 노드뿐만 아니라, 기능을 간단하게 구현하기 위한 **부모 노드에 대한 포인터도 있다.**

  ~~~java
  // 이너클래스
  class Node<T>{
    T data;
    Node<T> left;
    Node<T> right;
    Node<T> parent;
    
    //생성자
    public Node(T obj){
      data = obj;
      parent = left = right = null;
    }
  }
  ~~~



## 1-3 add 메소드

> AVL트리의 add 메소드에 대해 살펴보자



### add 메소드

+ AVL 트리의 클래스 생성자, add 메소드에 대한 코드이다. 

+ 클래스를 생성 후, 트리가 비어있으면 노드를 추가하고 비어있지 않다면 add 메소드를 재귀로 호출한다.

  ~~~java
  // AVL 클래스의 생성자
  public AVLTree(){
    root = null;
    currentSize=0;
  }
  
  //add메소드
  public void add(E obj){
    Node<E> node = new Node<E>(obj);
    //트리가 비어있을 경우
    if(root == null){
      root = node;
      currentSize++;
      return;
    }
    
    //트리에 노드가 있을 경우 add 메소드를 재귀로 호출
    add(root, node);
    
  }
  ~~~



## 1-4 재귀 add 메소드



### 재귀 add 메소드

~~~java
public void add(Node<E> parent, Node<E> newNode){
  // newNode의 data가 parent의 data보다 크면 트리의 오른쪽에 추가한다.
  if(((Comparable<E>)newNode.data).compareTo(parent.data)>0){
    if(parent.right == null){
      parent.right = newNode;
      newNode.parent = parent;
      currentSize++;
    }
    else
      add(parent.right,newNode);
  }
  else{ // newNode의 data가 parent의 data보다 작거나 같으면 트리의 왼쪽에 추가한다.
    if(parent.left == null){
      parent.left = newNode;
      newNode.parent = parent;
      currentSize++;
    }
    else
      add(parent.left, newNode);
  }
  checkBalance(newNode); // AVL트리가 규칙에 맞게 잘 되어있는지 확인한다. 
}
~~~



## 1-5 균형 확인 메소드



### 균형 확인 메소드

+ AVL 트리에서는 왼쪽과 오른쪽의 높이 차이가 항상 1보다 작거나 같아야 한다. 

+ 따라서, 노드를 추가하였을 때 높이의 차이가 1보다 커지면 회전을 하여 트리의 균형을 맞춰주어야 한다.

  ~~~java
  public void checkBalance(Node<E> node){
    // 높이 차이가 1초과 혹은 -1 미난 이면 AVL 트리 규칙 위반이다.
    if((height(node.left)- height(node.right)>1) || (height(node.left)-height(node.right)< -1)){
      rebalance(node);
    }
    
    // 만약 위 if문 조건에 부합하지 않거나 rebalance 메소드 실행이 끝나면
    // 부모 노드의 높이 차이를 계속 확인해서 루트까지 가야 한다.
    if(node.parent = null) // 만약 부모가 null이라면 현재 node가 루트라는 뜻이기 때문에 더 이상 재귀 함수를 사용할 필요 없이 리턴을 해준다.
      return;
    
    checkBalance(node.parent);
  }
  ~~~

  
