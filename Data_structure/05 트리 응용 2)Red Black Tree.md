# 트리 응용 (AVL & RB) - Red Black Tree



## 2-1 Red Black Tree의 규칙

> 스스로 균형을 잡는 이진 트리 자료 구조인 레드 블랙 트리에 대해 알아보자



### Red Black Tree의 규칙

+ 레드 블랙 트리는 자가 균형 이진 탐색 트리로서, AVL 트리처럼 스스로 균형을 잡는 트리이다. 
+ 레드 블랙 트리에서는 다음의 규칙에 따라 정렬하여 균형을 맞춘다.



#### 규칙

1. 모든 노드는 빨간색이나 검은색이다.
2. 루트는 항상 검은색이다.
3. 새로 추가되는 노드는 항상 빨간색이다.
4. 루트에서 잎 노드로 가는 모든 경로에는 같은 수의 검은색 노드가 있어야 한다.
5. 어떤 경로에서도 빨간색 노드 2개가 연속으로 있어서는 안된다.
6. 모든 빈 노드는 검은색이라고 가정한다. (nulls are black)



### 균형을 맞추는 방법

1) 이모 노드가 검은색일 경우
   + 회전을 한다.
   + 회전을 하고 나면 부모 노드는 검은색, 두 자식 노드는 빨간색이 되어야 한다.
2) 이모 노드가 빨간색일 경우
   + 색상 전환을 한다.
   + 색상 전환을 하고 나면 부모 노드는 빨간색, 두 자식 노드는 검은색이 되어야 한다.



## 2-2 레드 블랙 트리



### 레드 블랙 트리 예시

![mceclip0 (3)](https://user-images.githubusercontent.com/88477839/175032479-18d4c7e7-d01d-4aef-a10d-2960de3450a4.png)

+ 1~10까지의 숫자들을 레드 블랙 트리 규칙에 따라 배열하면 위 사진과 같이 나타난다. 
+ 1부터 숫자들을 하나씩 추가하면서 규칙에 적합한지 확인해주면 된다. 
+ 규칙을 위반하면 회전과 색상 전환으로 규칙에 맞게 바꾸어 준다.

<img width="1257" alt="스크린샷 2022-06-22 21 28 11" src="https://user-images.githubusercontent.com/88477839/175029500-609e7d53-cf95-467d-9eeb-d2a17a2a100b.png">

+ 이모 노드가 빨간색이면 색상전환을 한다.
+ 이모 노드가 검은색이면 회전을 한다. 
  + 이모 노드가 없다면 null은 black이기때문에 이모 노드는 검은색이다.

<img width="1280" alt="스크린샷 2022-06-22 21 43 46" src="https://user-images.githubusercontent.com/88477839/175031957-97a02415-23e1-458e-b5f8-ae892edaadaa.png">

+ 레드 블랙 트리 규칙에 부합한다고 해서 항상 AVL트리 규칙에도 부합하는 것은 아니다.



## 2-3 레드 블랙 트리 클래스



### 레드 블랙 트리 클래스

+ 레드 블랙 트리 클래스는 다음과 같다. 

+ 불리언 값을 가진 **black** 이 참이면 검은색, 거짓이면 빨간색을 표시한다. 

+ 그리고 이모 노드를 알아내기 위해 left, right, parent 노드를 가리키는 포인터뿐만 아니라 불리언 값을 가진 **isLeftChild**를 사용한다.

  ~~~java
  public class RedBlackTree<K,V> implements RedBlack<K,V>{
    Node<K,V> root; // 키 밸류를 제네릭으로 표현 - 어떤 자료형이 키 밸류가 될지 모르니까
    int size;
    
    //내부 클래스
    class NOde<K,V>{
      K key;
      V value;
      Node<K,V>left, right, parent;
      boolean isLeftChild, black;
      
      public Node(K key, V value){ // 생성자, 생성자에는 제네릭을 쓰지 않는다.
        this.key = key;
        this.value = value;
        left = right = parent = null;
        black = false;
        isLeftChild = false;
      }
    }
  }
  ~~~



## 2-4 add 메소드

> 레드 블랙 트리의 add. 메소드에 대해 살펴보자



### add 메소드

+ add 메소드의 동작 방식은 AVL 트리와 동일하다. 단, isLeftChild가 추가되기 때문에 이를 고려해주어야 한다.

+ add 메소드에 대한 코드는 다음과 같다. 트리가 비어있으면 노드를 추가하고 비어있지 않다면 재귀 add 메소드를 호출한다.

  ~~~java
  public void add(K key, V vlaue){
    Node<K,V> node = new Node<K,V>(key, value);
    
    if(root == null){ //트리가 비어있을 경우
      root = node;
      root.black = true;
      size++;
      return;
    }
    add(root,node); // 트리에 비어있지 않은 경우는 재귀 메소드 사용
    size++;
  }
  
  // add 재귀함수, 내부클래스
  private void add(Node<K,V> parent, Node<K,V> newNode){
    //newNode의 data가 parent의 data보다 크면 트리의 오른쪽에 추가하면 된다.
    if(((Comparable<K>)newNode.key).compareTo(parent.key)>0){
      if(parent.right == null){
        parent.right = newNode;
        newNode.parent = parent;
        newNode.isLeftChild=false;
        return;
      }
      return add(parent.right, newNOde); // 함수 재귀 호출
    }
    
    // newNode의 data가 parent의 data보다 작거나 같으면 트리의 왼쪽에 추가한다.
    if(parent.left = null){
      parent.left = newNode;
      newNode.parent = parent;
      newNode.isLeftChild=true;
      return;
    }
    return add(parent.left, newNode);
    
    //레드 블랙 트리가 규칙에 맞게 잘 되어있는지 확인한다.
    checkColor(newNode);
  }
  ~~~

  

## 2-5 색상 확인 메소드

> 레드 블랙 트리 규칙을 만족하는지 확인하고 색상 전환을 하는 색상 확인 메소드에 대해 알아보자.



### 색상 확인 메소드

+ 노드를 트리의 규칙에 맞게 추가한 후, 2-1강에서 배운 레드 블랙 트리의 6가지 규칙을 만족하는지 확인해주어야 한다.

~~~java
public void checkColor(Node<K,V> node){
  //루트는 항상 검은색이므로 색을 확인할 필요가 없다.
  if(node == root)
    return;
  //빨간 노드 2개가 연속으로 나오는 경우 (레드 블랙 트리 규칙 위반)
  if(!node.black && !node.parent.black){
    correctTree(node);
  }
  // 부모 노드를 함수 재귀 호출로 계속 확인한다.
 	checkColor(node.parent);
}

public void correctTree(Node<K,V> node){
	// node의 부모 노드가 왼쪽 자식이면 이모 노드는 조부모 노드의 오른쪽 자식이다.
  if(node.parent.isLeftChild){
    //이모 노드가 검은색(이모 노드가 비어있는 경우 포함)
    if(node.parent.parent.right == null || node.parent.parent.right.black)
      //회전
      return rotate(node);
    
    //이모 노드가 빨간색인 경우
    if(node.parent.parent.right != null)
      //색상 변환
      node.parent.parent.right.black = true;
    node.parent.parent.black = false;
    node.parent.black = true;
    return;
  }
  
  // node의 부모 노드가 오른쪽 자식이면 이모 노드는 조부모 노드의 왼쪽 자식이다.
  // 위 코드와 동일하게 하도, 이모 노드를 node.parent.parent.left로 바꾼다.
  else{
    //이모 노드가 검은색 (이모 노드가 비어있는 경우 포함)
    if(node.parent.parent.left == null || node.parent.parent.left.black)
      //회전
      return rotate(node);
    //이모 노드가 빨간색인 경우
    if(node.parent.parent.left !=null)
      //색상 변환
      node.parent.parent.left.black = true;
    node.parent.parent.black = false;
    node.parent.black = true;
    return;
  }
}
~~~



## 2-6 Rotate 메소드

> 레드 블랙 트리 규칙을 만족하도록 회전을 해주는 Rotate 메소드에 대해 알아보자



### Rotate 메소드

+ 현재 노드와 부모 노드가 각각 오른쪽 자식인지 왼쪽 자식인지에 따라 필요한 회전이 달라진다. 각각의 경우에 대해 코딩하면 다음과 같다.

  ~~~java
  public void rotate(Node<K,V> node){
    // 현재 노드가 왼쪽 자식
    if(node.isLeftChild){
      // 부모 노드가 왼쪽 자식인 경우
      if(node.parent.isLeftChild){
        //조부모 노드를 우측회전
       	rightRotate(node.parent.parent);
        node.black = false;
        node.parent.black = true;
        if(node.parent.right !=null)
          node.parent.right.black = false;
        return;
      }
      //부모 노드가 오른쪽 자식인 경우
      //조부모 노드를 우측-좌측 회전
      rightLeftRotate(node.parent.parent);
      node.black = true;
      node.right.black=false;
      node.left.black = false;
      return;
    }
    //현재 노드가 오른쪽 자식일 경우
    else{
      //부모 노드가 왼쪽 자식일 경우 - 조부모 노드를 좌측-우측 회전
      if(node.parent.isLeftChild){
        leftRightRotate(node.parent.parent);
        node.black = true;
        node.right.black = false;
        node.left.black = false;
        return;
      }
      // 부모 노드가 오른쪽 자식일 경우 - 좌측회전
      leftRotate(node.parent.parent);
      node.black = false;
      node.parent.black = true;
      if(node.parent.left !=null) // 굳이 null체크를 해줄 필요가 있는지?? 의문.
        node.parent.left.black = false;
      return;
    }
  }
  ~~~

  

  

## 2-7 좌측 회전

> 레드 블랙 트리에서 좌측 회전을 하는 방법에 대해 알아보자