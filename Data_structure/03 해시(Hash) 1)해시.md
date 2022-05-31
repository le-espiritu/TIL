# 해시(Hash)



## 1-1 해시 소개



### 해시

![mceclip1](https://user-images.githubusercontent.com/88477839/170809778-d1e9fc20-edbb-47cf-b190-d5611cfa6c84.png)



+ 연결 리스트의 단점은 리스트에서 무언가를 찾고 싶을 때 무조건 모든 요소를 살펴봐야 한다는 것이다. 
+ 이러한 단점을 해결하여, **데이터를 빠르게 추가하거나 제거하도록 한 데이터 구조**가 해시이다.
+ 해시는 **키, 그리고 그와 연관된 값**을 가지고 있다. 
+ 모든 요소를 살펴본 후 동일한 노드를 찾는 연결 리스트와 달리, 해시에서는 키가 주어지면 바로 그와 연관된 값을 찾을 수 있다.
+ 원하는 요소를 찾을 때, 연결 리스트의 시간복잡도는 O(n)이고(모든 요소를 다 살펴봐야하기 때문에),  해시의 시간 복잡도는 O(1)이다.



## 1-2 해시 함수



+ **Java의 Hash Code란,** 객체를 식별할 수 있는 유니크한 값을 말한다. Object의 hashCode()메소드를 통해 반환된 해시코드는 메모리에 생성된 객체의 주소를 정수로 변환한 형태를 얘기하는데, 이 정수는 중복되지 않는 고유의 값이다.



### 해시 함수

+ 해시 함수를 작성할 때 아래와 같은 점들을 고려해야 한다.

  1. 데이터의 속성
     + 예를 들어, CSSC 아이디가 있다면 CSSC 부분을 제거해야 한다.
  2. 연산이 빨라야 한다.

  3. 두 요소가 "같다면" 같은 값을 반환해야 한다.

  4. 같은 실행 환경일 경우 같은 객체라면 같은 값이 나와야 한다.

  5. 코드를 새로 실행하면 객체가 같더라도 다른 값이 나올 수 있다.
     + Object 클래스의 toString, equals, hashCode와 같은 메소드들은 오버라이딩을 하지 않으면, 기본적으로 메모리 위치를 기반으로 코드가 실행된다. 
     + 객체는 코드가 새로 실행될 때마다 메모리 상의 다른 위치에 할당되기 때문에, toString, equals, hashCode 같은 메소드들을 오버라이딩 하지 않으면, 입력으로 들어온 객체가 같더라도 다른 값(재실행되어 변경된 메모리 주소값)을 리턴하게 된다.

  6. 코드에서 최대한 충돌이 일어나지 않도록 해야 한다.




## 1-3 해시 충돌

> 해시를 만들 때 발생할 수 있는 문제점인 해시 충돌에 대해 살펴보자



### 해시 충돌

![mceclip0](/Users/helloacompany.asia/Downloads/mceclip0.png)



+ **서로 다른 값을 가진 키가 일치**하는 경우를 해시 충돌이라고 한다.
+ 예를 들어, 위 사진에서는 전화번호를 3분할한 것의 합을 키로 지정하였다.(folding 과정) 그런데 키가 2386으로 같아 해시 충돌이 발생한다.



## 1-4 해시 함수에서 문자열

> 문자열을 해시로 나타내는 방법을 살펴보자



### 해시 함수에서의 문자열

+ 문자열 "this"를 해시로 나타내려면 어떻게 해야 할까?

+ 문자는 유니코드로 변환하여 숫자 형태로 나타낼 수 있다.

  + 유니코드에서 t 는 116이다.
  + 유니코드에서 h 는 104이다.
  + 유니코드에서 i 는 105이다.
  + 유니코드에서 s 는 115이다.

  

+ 따라서 각 문자를 숫자로 변환한 후 그 숫자들을 합한다면, 문자열을 숫자로 나타낼 수 있을 것이다.

+ 그런데  이렇게 변환한 숫자들을 합할 경우, this뿐만 아니라 hits, tish 등 다른 문자열도 같은 숫자로 표현되는 **해시 충돌**이 발생한다.

+ 어떤 상수 g를 문자의 위치만큼 제곱한 뒤 그 수를 해당 문자의 숫자형태에 곱하고, 이렇게 해서 나온 자릿수들을 다 더하면 문제가 해결된다.

  + 이를 위 예제에 대입하여 공식으로 표현하면 116 + g(104 + g(105 + g(115))) 이다.
  + 위 공식을 풀어서 보면 116\*g^0 + 104\*g^1 + 105\*g^2 + 115\*g^3 이다.

  

#### 문자열을 해시로 나타내는 함수

~~~java
public int hashCode(String s){
  int g =31;
  int hash=0;
  for(int i=s.length()-1; int>=0; i--)
    hash=g*hash+s.charAt(i);
  return hash; // 나중에 배열의 인덱스로 사용하기 위해 이 정수가 필요하다.
}
~~~

+ ~~~java
  hash=g*hash+s.charAt(i);
  
  // 이 코드에 대한 부가 설명을 풀어서 하자면,  
  // 116 + g(104 + g(105 + g(115))) 에서
  
  // g*hash는 g(115), g(105+g(115)), g(104 + g(105 + g(115))) 등과 같고
  // +charAt(i)는 105+ , 105+, 104+ 등과 같다.
  ~~~



## 1-5 해시 크기 최적화

> 해시 충돌을 방지하기 위해 해시 크기를 최적화하는 방법을 살펴보자



### 해시 크기 최적화

+ **해시 충돌**을 **방지**하기 위해 해시의 크기를 최적화한다.
  + 예시 1: 해시의 크기를 홀수로 설정하여 % 연산자를 사용했을 때 나머지가 생겨 다양한 결과가 나오게 한다.
  + 예시 2: 해시의 크기를 소수로 설정하여 나머지가 다양한 숫자가 나오게 한다.



## 1-6 양수로 반환



### 양수로 변환

+ 다음과 같이 연산하면 값을 **해시(테이블)에 포함되는 양수로** 나타낼 수 있다. (Java에서는 음수를 표현하기 위해 2의 보수를 활용한다. 첫 숫자가 0이면 양수고 1이면 음수다.) 

+ 이 방법을 사용하여 data를 배열의 어느 위치에 넣을 것인지 결정한다.

~~~java
public int hashCode(String s){ // 스트링을 매개별수로 받아 정수를 반환하는 해시코드 메소드가 있다고 한다면..
	...
}


// data의 index 결정
int hashVal = data.hashCode(s); // data에서 hashCode()를 재정의했다고 가정하고 예시 코드를 작성한듯.
hashVal = hashVal & 0x7FFFFFFF;
hashVal = hashVal % tableSize;
~~~

+ 값을 양수로 변환해야 하는 이유는 배열의 인덱스는 음수가 될 수 없기 때문이다.



## 1-7 LoadFactor 메소드

> 해시가 얼마나 차있는지 알리는 LoadFactor에 대해 살펴보자



### LoadFactor 메소드

+ LoadFactor(적재율)는 **해시에 데이터가 얼마만큼 있는지** 알려준다. 
+ 적재율은 **λ**( 람다)로 표기하고 항목 수를 자료 구조의 크기만큼 나눈 값이다.
+ λ(람다)의 크기에 따라 해시 충돌이 일어나지 않도록 해시의 크기를 조절한다.
  + λ=0 이면 자료 구조가 비어있다는 뜻이다.
  + λ=0.5 이면 자료 구조가 반이 차있다는 뜻이다.
  + λ = 1 이면 자료 구조가 완전히 차 있다는 뜻이다.



## 1-8 충돌 해결

> 해시 충돌 해결 방법에 대해 알아보자



### 해시 충돌을 해결하는 방법

	1. 선형 조사법(linear probing)
    + 채우려는 공간이 이미 차 있다면, 비어있는 칸을 찾을 때까지 **다음 칸**을 확인(+1)하는 방법이다. 
    + 비어있는 칸을 찾아 그 곳에 채운 후 위치가 바뀌었다는 사실을 알려야 한다.
	2. 2차식 조사법(quadratic probing)
    + 다음 칸 대신 **1부터 순서대로 제곱하여 더한 칸**( 1^2,  2^2, ... )을 확인하는 방법이다. 
    + 테이블의 끝을 넘어간다면 % 연산을 해서 다시 테이블의 범위 안에 들어오게 한다.
	3. 이중 해싱(double hashing)
    + **hashCode 함수가 2개** 있어 채우려는 공간이 이미 차 있다면 두 hashCode의 결과를 더한 값을 테이블 내의 위치가 되게 하는 방법이다.
    + 이중 해싱은 아예 다른 해시 함수를 사용할 수 있기 때문에 데이터를 더 골고루 넣을 수 있다. 
      + 선형 조사법과 2차식 조사법은 더하는 값(1, 2, 3, ... 또는 1^2, 2^2, 3^2, ...)에 규칙성이 있는 반면에, 이중 해싱은 두번째 해시 함수가 리턴하는 값이 임의적이기 때문에 배열의 더 다양한 위치에 값을 저장할 수 있다.
    + 하지만 해시 함수가 2개 필요하다는 단점이 있다.



## 1-9 체이닝 (Chaining)

> 해시와 연결 리스트를 합친 체이닝에 대해 살펴보자



### 체이닝(Chaining)

![mceclip2](https://user-images.githubusercontent.com/88477839/171085026-137f1ca8-a3ce-411f-95ae-631dfbd10cfc.png)



+ 해시에는 사실상 배열 한 개로 구성되어 있다.
+ 체이닝(Chaining)은 배열 **요소마다 연결 리스트를 만들어** 수많은 데이터를 수용할 수 있게 하는 방법이다. 
  + 때문에 체이닝은 충돌을 피하기 위한 방법중 하나이다.
  + 해시가 같다고 하더라도 연결리스트로 요소를 추가하면 되기 때문이다.
+ 체인 해시는 가장 안정적이고 보편적으로 사용되는 자료 구조 중 하나이다.
  + 파이썬의 딕셔너리가 해시 체이닝을 이용하여 구현되었다.



+ 체이닝을 하면 수용 가능한 요소 개수에 제한이 없어지고 배열의 크기 조정도 자주 할 필요가 없어진다. 
+ 상수 시간( O(1) )으로 어 떤 요소든 추가하고 제거하고 찾을 수 있다.
+ 체이닝에서 적재율 λ는 항목의 개수(연결리스트 요소들의 개수)를 가능한 체인 개수(배열 요소 개수)로 나눈 값이다. 
+ 체인 1개에 여러 항목을 넣을 수 있어 λ는 1보다 큰 수가 될 수 있다.



+ 하지만 hashCode가 같은 숫자만 반환하여 하나의 체인이 너무 길어지면 결국 연결 리스트와 시간 복잡도가 같아지는 문제가 발생한다. ( O(n) )
+ 따라서 연결리스트가 길지 않고 짧은게 좋다.



## 1-10 재해싱

> 해시의 크기 조정 과정인 재해싱에 대해 살펴보자



### 재해싱

+ 체인 해시에서 해시가 너무 많이 차면 **크기 조정**을 해야 한다.

  1. 크기가 2배인 배열을 만든다.

  2. 아래 코드에 따라 data의 index를 다시 결정하여 연결 리스트의 요소들을 옮긴다.

     ~~~java
     // data의 index 결정
     int idx = x.hashCode();
     idx = idx & 0x7FFFFFFF;
     idx = idx % tableSize; // 여기서 tableSize는 새롭게 2배로 만든 배열의 사이즈
     ~~~

+ 연결 리스트의 위치를 그대로 하여 옮기면 정보를 다시 찾거나 제거하려 할 때 문제가 발생한다. 

+ 정보의 위치를 지정할 때 다른 정보는 그대로인데, tableSize만 바뀌기 때문이다. 

+ 그래서 각 요소의 위치를 초기화한 후, **처음부터 다시 위치를 지정**해주어야 한다.



## 1-11 해시 클래스 , 1-12 내부 클래스

> 체인 해시에서 키와 값을 지정해주는 내부 클래스인 해시 클래스에 대해 살펴보자



### 해시 클래스

+ 체인 해시는 해시 요소(연결리스트 요소, hashElement)마다 키와 값이 들어있다. 

+ 키와 값을 저장하기 위한 내부 클래스는 다음과 같다.

  ~~~java
  // 해시 클래스
  public class Hash<K,V> implements HashInterface<K,V>{
    class HashElement<K,V>implements Comparable<HashElement<K,V>{
      //키와 값 정의
      K key;
      V value;
      public HashElement(K key, V value){
        this.key = key;
        this.value = value;
      }
      
      //compareTo 함수 오버라이딩인듯
      public int compareTo (HashElement<K,V> o )
        return (((Comparable<K>)this.key).compareTo(o.key))
    }
    
    //전역 변수
    int numElements, tableSize; // numElements는 해시 요소의 개수
    double maxLoadFactor; // 현재 적재율이 maxLoadFactor 값을 넘어가면 크기 조정을 해줌
    LinkedList<HashElement<K,V>>[] harray; // 해시 배열, 테이블, 연결 리스트들의 배열
    // 해시엘리먼트가 들어있는 연결리스트들이 들어 있는 배열.
  }
  ~~~



## 1-13 생성자

> 해시를 구현하는 생성자에 대해 살펴보자



+ 해시를 구현하는 생성자를 만들어보자

  ~~~java
  public class Hash<K,V>implements HashInterface<K,V>{
    LinkedList<HashElement<K,V>>[] harray;
    
    //해시 구현
    public Hash(int tableSize){
      this tableSize = tableSize;
      harry = (LinkedList<HashElement<K,V>>[])new LinkedList[tableSize]; // 형변환
      //자바에서 제네릭으로 배열을 만드는 것은 어렵다.
      // 그래서 우선 객체로 배열을 만든 뒤 형변환을 해준다.
      // 이상황에서는 제네릭이 없는 연결 리스트의 배열을 만들고 형변환 해준다.
      
      
      //연결 리스트 체이닝
      for(int i=0; i<tableSize; i++)
        harray[i] = new LinkedList<HashElement<K,V>();
      maxLoadFactor = 0.75;
      numElements=0;
      
    }
  }
  ~~~

+ ~~~java
  // 해시 클래스
  public class Hash<K,V> implements HashInterface<K,V>{
    class HashElement<K,V>implements Comparable<HashElement<K,V>{
      //키와 값 정의
      K key;
      V value;
      public HashElement(K key, V value){
        this.key = key;
        this.value = value;
      }
      
      //compareTo 함수 오버라이딩인듯
      public int compareTo (HashElement<K,V> o )
        return (((Comparable<K>)this.key).compareTo(o.key))
    }
    
    //전역 변수
    int numElements, tableSize; // numElements는 해시 요소의 개수
    double maxLoadFactor; // 현재 적재율이 maxLoadFactor 값을 넘어가면 크기 조정을 해줌
    LinkedList<HashElement<K,V>>[] harray; // 해시 배열, 테이블, 연결 리스트들의 배열
    // 해시엘리먼트가 들어있는 연결리스트들이 들어 있는 배열.
  
    public Hash(int tableSize){
      this tableSize = tableSize;
      harry = (LinkedList<HashElement<K,V>>[])new LinkedList[tableSize];
      
      //연결 리스트 체이닝
      for(int i=0; i<tableSize; i++)
        harray[i] = new LinkedList<HashElement<K,V>();
      maxLoadFactor = 0.75;
      numElements=0;
      
    }
  }
  ~~~



## 1-14 생성자 복습



~~~java
public class Hash<K,v> implement HashInterface<k,v>{
  LinkedList<HashElement<K,V>>[] harray;
  int tableSize, numElemnts;
  double maxLoadFactor;
  
  public Hash(int tableSize){
    this.tableSize = tableSize;
    maxLoadFactor = 0.75;
    numElements =0;
    
    harray = (LinkedList<HashElement<K,V>>[]) new LinkedList[tableSize];
    
    for(int i =0; i<tableSize; i++)
      harray[i] = new LinkedList<HashElement<K,V>>();
  }
  
}

~~~

+ 타입 소거 때문에 자바에서 제네릭으로 배열을 못 만든다.
  + 자바의 배열에는 타입을 정해줘야 한다.



## 1-15 add와 remove 메소드

> 해시에 내용을 추가하거나 제거하는 방법에 대해 살펴보자



### add

+ 해시에 내용을 추가하는 add 메소드이다. 
+ 크기가 너무 커지거나 작아질 경우, add 메소드에서 **크기를 조절**해주어야 한다.

~~~java
public boolean add(K key, V value){
  //resize
  if(loadFactor() > maxLoadFactor)
    resize(tableSize*2);
  
  //키와 값을 저장해 놓을 object he정의
  HashElement<K,V> he = new HashElement(key,value);
  
  //he의 index찾기
  int hashVal = key.hashCode();
  hashVal = hashVal & 0x7FFFFFFF;
  hashVal = hashVal % tableSize;
  
  // add he
  harray[hashVal].add(he);
  
  numElements++;
  return true;
}
~~~



### remove

+ remove 메소드에서는 크기 조정을 걱정할 필요도 없고 객체를 생성할 일도 없다.

~~~java
public boolean remove(K key, V value){ // remove(HashElement he)가 맞을 듯
  //index 찾기
  int hashVal = key.hashCoe(); // he.key.hashCoe(); 가 맞을 듯
  hashVal = hashVal & 0x7FFFFFFF;
  hashVal = hashVal % tableSize;
  
  //해당하는 index의 키와 값 제거
  harray[hashVal].remove(he);
  
  numElements--;
  return true;
}
~~~



### 1-16 getValue 메소드

> 해시에서 키와 값을 찾는 getValue 메소드에 대해 살펴보자



### getValue

+ 키의 값을 찾는 getValue 메소드이다. 
+ 키의 index가 무엇인지 찾고, 해시에서 그 index에 해당하는 연결리스트를 찾는다.
+ 그리고 그 연결리스트에서 매개변수 key에 해당하는 HashElement를 찾을 때까지 반복한다. 
+ 그리고 key의 값이 동일하면 그 때 키의 값을 반환한다.

~~~java
public V getValue(K key){
  //해당하는 인덱스 찾기
  int hashVal = key.hashCode();
  hashVal = hashVal & 0x7FFFFFFF;
  hashVAl = hashVal % tableSize;
  
  for(HashElement<K,V)he : harray[hashVal]){ // harray[hashVal]는 연결리스트임
    if(((Comparable<K>)key).compareTo(he,key)==0){
      return he.value;
    }
  }
  return null;
}
~~~



### getValue()의 시간 복잡도

+ 해시 충돌이 드물게 발생해서 연결 리스트의 요소가 1~2개일 경우에는 key에 대응되는 value를 O(1)의 시간복잡도로 찾을 수 있다.
+ 그런데, hashCode의 리턴값 또는 나머지 연산의 결과값이 중복되어 해시 충돌이 빈번하게 발생하는 경우 연결 리스트의 길이가 늘어나면서 key값 비교에 걸리는 시간이 증가할 수 있다.
+ 따라서, getValue의 시간복잡도는 Best Case: O(1), Worst Case: O(n) 이다.



## 1-17 resize

> 해시의 크기를 조정하는 resize에 대해 살펴보자

