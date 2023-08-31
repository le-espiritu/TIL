# CS - 네트워크



### 네트워크와 인터넷 개념



#### 홈 네트워크

+ IP 주소 - 인터넷에 연결되기 위해 필요한 인터넷 상의 주소
+ 모뎀(modem) - 실제로 인터넷과 물리적으로 연결되기 위한 장치이며, 네트워크 통신에 필요한 신호 변환 장치
+ 공유기 (home router)
  + 여러 기기들을 인터넷에 연결될 수 있도록 하는 장치
  + 하나의 IP주소로도 이 공유기에 연결된 여러 기기들이 동시에 인터넷을 사용하는 것이 가능하게 함
  + 공유기에 연결된 기기들은 같은 네트워크 소속 
+ 스위치 (switch)
  + 같은 네트워크 내의 기기들이 서로 통신할 수 있도록 하는 장치
  + 보통 공유기의 랜(LAN)포트 수가 부족할 때 사용
  + 스위치 허브 혹은 그냥 허브라고도 불림



#### 네트워크(Network)란?

+ 컴퓨터나 기타 기기들이 리소스를 공유하거나 데이터를 주고 받기 위해 유선 혹은 무선으로 연결된 통신 체계



#### LAN(local area network)

+ 집, 학교, 회사 건물 등 제한된 범위 내에서 컴퓨터나 기타 기기들을 연결해서 데이터나 리소스를 공유할 수 있게 하는 네트워크
+ LAN을 구성하는 두 가지 중요한 기술
  + Ethernet - 유선 통신
    + 이더넷은 LAN을 위해 개발된 근거리 유선 네트워크 통신망 기술이다.
    + LAN, WAN 등의 네트워크 환경에서 각 기기들이 전송매체를 통해 데이터를 주고받을 수 있도록 만들어진 기술 
    + 이더넷은 네트워크에 연결된 각 기기들이 48비트 길이의 고유의 MAC 주소를 가지고 이 주소를 이용해 상호간에 데이터를 주고 받을 수 있도록 만들어졌다
  + wireless LAN - 무선 통신 (wi-fi 라고도 부름)



#### WAN(wide area network)

+ 여러 LAN이나 다른 종류의 네트워크들을 하나로 묶어서 멀리 떨어진 기기들도 통신이 가능하도록 만든 네트워크
+ 훨씬 더 넓은 범위를 커버하는 네트워크
+ Ex ) 은행의 ATM, wireless WAN, Internet



#### Internet

+ The network of networks(홈네트워크, LAN까지 모두 포함)
+ the world's largest WAN
+ global network
+ 일반 사용자나 회사, 기관 등이 인터넷을 사용할 수 있도록 인터넷 연결 서비스를 제공하는 존재가 필요한데, 이 존재가 ISP이다.



#### ISP (internet service provider)

+ 일반 사용자나 회사, 기관 등이 인터넷을 사용할 수 있도록 인터넷 연결 서비스를 제공하는 존재 (ISP들이 모여서 인터넷의 코어 부분을 이룸)
+ ISP는 자신들의 네트워크를 가지고 있음
+ 내가 가입한 ISP를 통해 어떻게 인터넷에 있는 다른 컴퓨터와 통신할 수 있는 걸까?
  + <img width="747" alt="스크린샷 2023-08-28 19 56 41" src="https://github.com/le-espiritu/TIL/assets/88477839/bb80c5ae-8c2b-4e8d-bc9d-458af0f3f7c6">
  + ISP에서 ISP로 데이터를 전달하면서 통신할 수 있다.
+ 역할과 규모에 따라서 ISP들도 tier가 나뉜다. 
  + Tier 1 ISP 
    + 위 그림에서 주황색 ISP
    + 국제 범위의 네트워크 보유
    + 인터넷의 모든 네트워크 접근 가능
    + 인터넷 중추 역할 (backbone)
    + 트래픽 전송 비용 없음
  + Tier 2 ISP
    + 위 그림에서 하늘색 ISP
    + 국가 / 지방 범위 네트워크 보유
    + 우리나라의 LG U플러스나 kt, sk 브로드밴드들이 이에 해당 
    + 일반 사용자나 기업 대상 서비스
    + 인터넷의 모든 영역에 연결되기 위해 tier 1 ISP에 비용을 내고 트래픽 전송
  + Tier 3 ISP
    + 위 그림에서 연두색 ISP
    + 작은 지역 범위 서비스 제공
    + 일반 사용자나 기업 대상 서비스
    + 상위 ISP에게 비용을 내고 인터넷 트래픽을 구매해서 이를 통해 서비스 
    + 우리나라에서는 잘 존재하지 않지만 미국같이 땅이 넓은 나라에서는 존재함.



#### ISP 네트워크 간의 연결

+ <img width="741" alt="스크린샷 2023-08-28 20 12 04" src="https://github.com/le-espiritu/TIL/assets/88477839/93245f15-118c-4368-ad80-9d096009a0d3">
  + 라우터를 통해 서로 다른 ISP가 관리하는 네트워크들이 연결됨 
  + 라우터란 목적하는 네트워크에 데이터를 보내는 장치이다.



#### 네트워크를 이루는 여러 장치들을 이르는 표현

+ **노드** - 네트워크를 이루는 각각의 장치
+ **End system, 호스트 (host)**
  + 노드들 중에서도 네트워크 끝에 있는 노드를 말함
  + 네트워크를 사용하기 위해 연결된 노드
  + 클라이언트와 서버로 나뉨
+ **클라이언트**
  + 다른 호스트의 데이터나 리소스를 요청하는 호스트
+ **서버**
  + 다른 호스트에게 서비스를 제공하는 호스트
  + 요청에 따라 데이터나 리소스를 제공



---

### 프로토콜과 OSI 7 layer



#### 네트워크의 기능

+ 애플리케이션 목적에 맞는 통신 방법 제공
+ 신뢰할 수 있는 데이터 전송 방법 제공
+ 네트워크 간의 최적의 통신 경로 결정
+ 목적지로 데이터 전송
+ 노드 사이의 데이터 전송



#### 네트워크 프로토콜

+ 네트워크 통신을 하기 위해서 통신에 참여하는 주체들이 따라야 하는 형식, 절차, 규약
+ 통신 기능이 제대로 동작하기 위해서는 참여자들 사이에서 약속된 통신 방법이 있어야 한다.
+ 위의 네트워크의 기능들은 네트워크 프로토콜을 통해서 동작하게 된다.
+ 네트워크의 기능들은 단 하나의 프로토콜로 구현할 수 없으며 네트워크 기능별로 모듈화를 해야 한다.
+ 각 기능이 계층별로 동작하기 때문에 계층별로 모듈화 해줘야하고
+ 따라서 네트워크 기능들을 계층 구조(layered architecture)로 모델링하게 됨
  + OSI model (7 layer)
    + 범용적인 네트워크 구조
  + TCP/IP stack (4 layer)
    + 인터넷에 특화된 네트워크 구조 



#### OSI 7 layer

+ <img width="571" alt="스크린샷 2023-08-28 21 58 16" src="https://github.com/le-espiritu/TIL/assets/88477839/4b68026f-f9ba-40f4-8646-8a51b2a3c9b9">
+ 각 레이어에 맞게 프로토콜이 세분화돼서 구현
+ 각 레이어의 프로토콜은 하위 레이어의 프로토콜이 제공하는 기능을 사용하여 동작
+ **application layer**
  + 애플리케이션 목적에 맞는 통신 방법 제공
  + HTTP, DNS, SMTP, FTP
+ **presentation layer**
  + 애플리케이션 간의 통신에서 메시지 포맷 관리
  + 데이터를 보내는 쪽에서 인코딩을 해서 보내면 받는 쪽에서는 디코딩을 해야하고
  + 데이터를 보내는 쪽에서 암호화를 해서 보내면 받는 쪽에서는 복호하를 해야하고
  + 데이터를 보내는 쪽에서 압축을 해서 보내면 받는 쪽에서는 압축을 풀어야 한다.
+ **session layer**
  + 애플리케이션 간의 통신에서 세션을 관리
  + RPC (remote procedure call)
+ **transport layer**
  + 애플리케이션 간의 통신 담당
  + 실제로 목적지 애플리케이션으로 데이터를 전송하는 일이 일어남
  + TCP 프로토콜 - 안정적이고 신뢰할 수 있는 데이터 전송 보장
  + UDP 프로토콜 - 데이터를 전송하는 필수 기능만 제공
+ **network layer**
  + 호스트 간의 통신 담당 (IP 프로토콜)
  + 목적지 호스트로 데이터 전송(IP주소를 활용하여)
  + 네트워크 간의 최적의 경로 결정
+ **data link layer**
  + 직접 연결된 노드 간의 통신 담당
  + MAC 주소 기반 통신 (ARP)
    + IP 주소를 MAC 주소로 변환해줘야 하는 일이 발생하는데 이때 사용하는 프로토콜이 ARP란 프로토콜이다. 
+ **physical layer**
  + 물리적인 매개체를 통해서 (케이블 등) 데이터를 bits 단위로 전송



---

### World Wide Web



#### World Wide Web

+ 여러 정보들을 링크를 통해 효율적으로 접근할 수 있는 인터넷 상에서 동작하는 전 지구적인 정보 시스템
+ 줄여서 부를 때는 web이라고 부르고
+ 줄여서 쓸 때는 www 혹은 w3라고 씀
+ 1989년 Tim Berners-Lee가 CERN에서 발명 



#### hypertext

+ 문서간의 링크 



#### 팀 버너스리가 CERN에서 개발한 Enquire의 문제점

+ Enquire는 hypertext로(링크로) 문서간 효율적인 접근이 가능했다. (기존에는 tree-based여서 문서 접근이 효율적이지 않았음)
+ 그러나 CERN에서 누구나 쓸 수 있는 범용 SW가 아니었고
+ 다른 시스템의 문서와 연결이 되지 않았다. (external links 안됨)
+ 파일들이 하나의 machine에 있어야 했다.
+ **따라서 새로운 시스템이 필요했다.**
  + hypertext(hypermedia) 시스템
  + CERN의 상황을 고려한 통합 정보 시스템
    + 원격 접근이 가능해야 한다.
    + 다양한 시스템에서도 동일 정보에 접근 가능해야한다.
    + 중앙 통제나 조정 없이도 연결 가능해야 한다.
    + 이미 존재하는 정보에도 적용 가능해야 한다.
    + 결국 이 새로운 시스템이란 **distributed hypertext system** (분산된 하이퍼 텍스트 시스템)
      + 이 새로운 시스템을 구상하는 과정에서 client / server model 제안 



#### world wide web의 등장

+ Internet 기반으로 distributed hypertext system 개발
+ Client/server 아키텍쳐 구체화 (protocol)
+ 결과
  + 최초의 웹 브라우저 등장
  + 최초의 서버 등장
  + 최초의 웹 사이트 등장
  + hypertext markup language (HTML) 개발
    + 하이퍼텍스트를 브라우저상에서 어떻게 보여줄지 정의하기위해
  + hypertext transfer protocol (HTTP) 개발
  + universal document identifier (UDI, 오늘날의 URL) 등장 



#### 인터넷과 wolrd wide web

+ 인터넷은 글로벌 네트워크, 글로벌 인프라이다.
+ 즉, 인터넷은 하드웨어에 가깝다면 world wide web은 소프트웨어에 가깝다.



#### HTTP

+ web의 토대가 되는 프로토콜 
+ Hypertext(hypermedia)와 같은 정보를 주고 받기 위해 사용되는 프로토콜 



---

### 프로토콜 표준 스펙에서의 Socket, Port, TCP connection



#### OSI 7 layer 모델과 TCP/IP stack

+ <img width="654" alt="스크린샷 2023-08-29 13 14 14" src="https://github.com/le-espiritu/TIL/assets/88477839/43edce98-d441-4a3d-9a60-97ed05e0e496">



#### TCP/IP stack의 application과 system

+ <img width="636" alt="스크린샷 2023-08-29 13 17 44" src="https://github.com/le-espiritu/TIL/assets/88477839/383304e6-83b6-4aa4-9cb5-c488da3b4765">
+ <img width="578" alt="스크린샷 2023-08-29 13 18 30" src="https://github.com/le-espiritu/TIL/assets/88477839/34b3f039-398e-4521-ab63-d03ee41f1543">



#### Port 개념

+ <img width="668" alt="스크린샷 2023-08-29 13 22 35" src="https://github.com/le-espiritu/TIL/assets/88477839/2e228dbc-b55a-4ca0-aa6a-820c4f602e91">
  + 프로세스가 네트워크 통신을 하고 싶다면 (인터넷 상에 있는 또다른 프로세스와 데이터를 주고 받고 싶다면 ) 프로세스와 연결된 데이터를 주고받을수 있는 통로가 필요할 것이다. 이 통로가 Port이다.
  + 이 통로(Port)를 통해 프로세스가 시스템으로 데이터를 보내주면 시스템이 지원하는 네트워크 기능을 사용해서 데이터를 인터넷상에 있는 또다른 프로세스에게 전달하게 된다.



#### 인터넷 프로토콜

+ internet protocol은 데이터를 하나의 호스트에서 목적하는 호스트까지 라우팅하는 역할을 한다.
+ 하지만 internet protocol은 unreliable(신뢰할 수 없는) 프로토콜이다.
+ 즉 데이터가 유실될 수도 있고, 데이터를 받는쪽에서 순서가 바뀌어서 데이터를 받게 될수도 있다.



#### TCP(transmission control protocol)

+ 프로세스 간의 통신에서는 데이터를 안정적으로 주고 받아야 하기 때문에 인터넷 프로토콜 위에서 신뢰성 있게 동작할 프로토콜이 필요했다.
+ 그래서 개발된 프로토콜이 바로 TCP이다.



#### TCP connection 개념

+ 프로세스 간의 안정적이고 논리적인(<->물리적인) 통신 통로
+ 프로세스 간 데이터를 주고 받기위해 먼저 connection을 열고(3-way 핸드쉐이크), 데이터를 주고 받고, connection을 닫는다.(4-way 핸드쉐이크)
+ 데이터를 안정적으로 주고 받기 위해서 데이터를 주고 받기 전에 먼저 connection을 수립한다.
+ connection 기반으로 동작하는 프로토콜을 connection-oriented라고 한다.



#### port 식별 : port number 개념

+ 인터넷 상에서 어떻게 port를 유니크하게 식별할까?
+ port(number) 정의 : 16 bits로 이루어진 숫자(0~65535)
+ port number만으로는 유니크하게 식별할 수 없다.
+ internet address(IP)로 각 host를 유니크하게 식별할 수 있다.
+ internet address + port number 조합으로 인터넷 상의 port를 유니크하게 식별할 수 있다.



#### Socket 개념

+ internet address + port number
+ 인터넷 상에 존재하는 각 port를 유니크하게 식별하기 위한 주소(namespace)
+ 각 socket은 인터넷 상에서 유니크하다.
+ 최종적으로 socket은 protocol + IP + port number로 유니크하게 식별된다.



#### TCP connection과 socket의 관계

+ 각 connection을 유니크하게 식별할 수 있어야 한다.
+ 한 쌍의 socket은 connection을 유니크하게 식별한다.
  + <Src socket , dest socket>
    + <src internet addr, src port, dest internet addr, dest port>
+ 하나의 socket은 동시에 여러 connection들에서 사용될 수 있다.



#### UDP(user datagram protocol)

+ connectionless : 연결을 맺지 않고 바로 데이터를 주고 받는 다.
+ unreliable : internet protocol을 거의 그대로 사용한다.
+ UDP에서도 socket 개념을 사용한다.



---

### 네트워크 프로그래밍 관점에서의 socket



#### 시스템 관점에서의 TCP/IP stack

+ <img width="618" alt="스크린샷 2023-08-29 18 17 08" src="https://github.com/le-espiritu/TIL/assets/88477839/88a525b0-aeb4-4796-b25b-2c48f4d871ee">



#### 실제로 시스템이 동작하는 관점에서의 socket

+ <img width="615" alt="스크린샷 2023-08-29 18 20 18" src="https://github.com/le-espiritu/TIL/assets/88477839/af966881-ce02-46ce-9176-6ff176f885bf">
  + 애플리케이션이 시스템의 기능을 함부로 쓸 순 없다.
  + 대신 시스템은 애플리케이션이 네트워트 기능을 사용할 수 있도록 **프로그래밍 인터페이스**를 제공한다.
  + 이 프로그래밍 인터페이스가 바로 **socket**이며
  + 애플리케이션은 socket을 통해 데이터를 주고 받는다.
  + 개발자는 socket programming을 통해 네트워크 상의 다른 프로세스와 데이터를 주고 받을 수 있도록 구현한다.
  + 대부분의 시스템(운영체제)은 socket 형태로 네트워크 기능 제공
  + 보통 socket을 직접 조작해서 통신 기능을 구현할 일은 적다.
  + application layer의 프로토콜은 보통 라이브러리나 모듈 형태로 해당 기능이 제공되는데, 이때 내부를 열어보면 소켓을 활용해서 프로토콜을 구현했음을 알 수 있다.



#### 실제 구현 / 동작 관점에서의 소켓

+ Port(number)는 socket을 식별하기 위해 부여되는 숫자이다.
+ socket은 <protocol, IP address, port number>로 정의 된다.
+ UDP에서 socket은 <protocol, IP address, port number>로 유니크하게 식별되지만
+ TCP에서 sodcket은 <protocol, IP address, port number>로 유니크하게 식별되지 않는다.



#### 실제 구현 / 동작 관점에서 TCP 소켓이 식별되는 방식

+ 서버쪽에는 connection을 맺는 요청을 기다리는 listening socket이 있다. ( connection 연결 요청 담당 )
+ 이 listening socket을 통해 클라이언트와 connection을 맺고, 서버쪽에서는 또다른 소켓을 만든다.
+ 클라이언트쪽에서는 새롭게 만든 서버쪽의 소켓과 함께, 성립된 connection위에서 데이터를 주고 받게된다.
+ 이때 listening socket과 통신을 위해 새롭게 만들어진 소켓의 IP address와 Port number가 동일하다. 
+ 때문에 connection이 성립된 이후의 소켓은
  + <src IP, src port, dest IP, dest port>로 socket을 식별한다.



#### Port number

+ 16 bits로 이러우진 숫자 ( 0~65535 )
+ 0~1023 : well-known ports, system ports
  + Ex) HTTP(80), HTTPS(443), DNS(53)
+ 1024 ~ 49151 : registered ports (IANA에 등록된 번호)
  + Ex) MySQL DB(3306), Apache tomcat server(8080)
+ 49152 ~ 65535 : dynamic ports(등록 안된 번호, 임시로 혹은 자동 할당될 때 사용)



---

### TCP / IP 참고 자료들

- [https://coding-factory.tistory.com/613](https://coding-factory.tistory.com/613)
- [https://www.joinc.co.kr/w/Site/Network_Programing/Documents/IntroTCPIP](https://www.joinc.co.kr/w/Site/Network_Programing/Documents/IntroTCPIP)
- [https://velog.io/@rosewwross/TCPIP](https://velog.io/@rosewwross/TCPIP)
- [https://velog.io/@haero_kim/물-흐르듯-읽어보는-TCPIP](https://velog.io/@haero_kim/%EB%AC%BC-%ED%9D%90%EB%A5%B4%EB%93%AF-%EC%9D%BD%EC%96%B4%EB%B3%B4%EB%8A%94-TCPIP)
- [https://velog.io/@conatuseus/2019-09-10-2009-작성됨-xsk0ds2eqf](https://velog.io/@conatuseus/2019-09-10-2009-%EC%9E%91%EC%84%B1%EB%90%A8-xsk0ds2eqf)



### IP 주소와 MAC주소

+ https://jin-network.tistory.com/46
+ https://jhnyang.tistory.com/404