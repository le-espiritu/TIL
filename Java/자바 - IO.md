# Java IO



## Stream

+ 스트림이란 실제의 입력이나 출력이 표현된 데이터의 이상화된 흐름을 의미한다.
+ 스트림은 한 방향으로만 통신할 수 있으므로, 입려과 출력을 동시에 처리할 수 없다.
+ 따라서 사용 목적에 따라 스트림은 입력스트립(InputStream), 출력스트림(OutputStream)으로 구분된다.
+ 스트림은 기본적으로 바이트 단위로 데이터를 전송한다.



## System.in

+ System.in은 키보드와 같은 사용자로부터 입력을 받는 표준입력스트림 객체를 나타낸다.
+ 그래서 System.in의 리턴값은 InputStream이다.
+ InputStream은 데이터를 byte단위로 읽어오는 byte stream이다.



## Reader / Writer

+ InputStream, OutputStream은 기본적으로 바이트(바이너리)의 흐름을 받아들이거나 내보내는 객체이다.

+ Reader와 Writer는 이러한 바이너리 스트림을 문자로 바꿔서 작업을 한다.

+ 하위객체로 InputStreamReader가 있고, OutputStreamWriter가 있다.

  

## InputStreamReader

+ Reader 클래스의 하위 클래스로, 문자 스트림을 읽기 위한 클래스이다.

+ 인자로 InputStream을 취해서, Reader 스트림 형태로 변환한다.

+ 사용 예

  ~~~java
  InputStreamReader ips = new InputStreamReader(System.in);
  // System.in의 리턴값은 InputStream이다.
  ~~~



## BufferedReader

+ 인자로 취한 Rader 스트림에 버퍼링기능을 추가한 입력스트립 클래스이다.

+ Reader객체의 하위 클래스이지만 단독으로 바로 InputStream을 받아서 쓰지는 않는다.

+ 다른 하위 객체들( ex) InputStreamReader)을 인수로 받아들여 그  Reader를 Buffering할 수 있데 도와주는 역할을 한다.

+ 데이터를 일정량사이즈로 한번에 읽어온 후 버퍼에 보관한다.

+ 사용자가 요구시, 버퍼에서 읽어오게 된다.

+ 속도를 향상시키고, 시간의 부하를 줄일 수 있다.

+ 사용 예

  ~~~java
  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  ~~~



## 예문

+ BufferedWriter

  + ~~~java
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.OutputStreamWriter;
    
    public class Output2{
      public static void main(String[] args) throw IOExcetption{
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        bw.write("출력에 대해");
        bw.write("설명하고 있어요.");
        bw.flush();
        
        bw.write("출력에 대해 \n");
        bw.write("설명하고 있어요.");
        bw.flush();
      }
    }
    
    //Console
    // 출력에대해 설명하고 있어요.출력에 대해
    // 설명하고 있어요.
    ~~~

  + BufferedWriter 객체를 생성한 뒤 write()를 사용하여 출력한다.

  + 하지만 BufferedWriter는 write()를 실행한다고 바로 출력 되지는 않는다.

  + write()를 통해 출력할 값을 버퍼에 저장시켰다가 flush()를 통해 버퍼에 저장되어 있던 값들을 출력시켜 준다.

  + BufferedWriter는 대량의 데이터들을 출력해줄때 속도가 System.out보다 빠르다.

+ BufferedReader

  + ~~~java
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    
    public class Input2{
      public Static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println(br.readLine());
      }
    }
    ~~~



## 참고 자료들

+ https://jjunii486.tistory.com/52
+ https://jforj.tistory.com/11
+ http://www.tcpschool.com/java/java_io_stream



