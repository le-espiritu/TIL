# Maven



## Maven 이란?

> 프로젝트가 복잡해질수록 사용하는 라이브러리가 많아지게 되고, 프로젝트에 참여하는 사용자가 많아질수록 라이브러리의 관리는 어려워진다. 또한 프로젝트가 복잡해질수록 소스를 컴파일하고 배포하는 것도 점점 어려줘진다.
>
> 이러한 문제를 해결하기 위해 다양한 도구들이 존재하는데 Maven도 그 중 하나이다.



### Maven 이란?

+ Maven은 지금까지 애플리케이션을 개발하기 위해 반복적으로 진행해왔던 작업들으르 지원하기 위하여 등장한 도구다.
+ Maven을 사용하면 빌드(Build), 패키징, 문서화, 테스트와 테스트 리포팅, git, 의존성관리, svn등과 같은 형상관리서버와 연동(SCMs), 배포 등의 작업을 손쉽게 할 수 있다.
  + build란 소스 파일로부터 프로그램을 만들어 내는 전 과정
+ Maven을 이해하려면 **CoC(Convention over Configuration)**라는 단어를 먼저 이해해야 한다.
+ CoC란 일종의 관습을 말하는데, 예를 들어 프로그램의 소스파일은 어떤 위치에 있어야 하고, 소스가 컴파일된 파일들은 어떤 위치에 있어야 하고 등을 미리 정해놨다는 것이다.
+ Maven을 사용한다는 것은 어쩌면 이러한 관습, 즉 CoC에 대해서 알아나가는 것이라고도 말 할 수 있다.



### Maven을 사용할 경우 얻게 되는 이점은?

+ 의존성 라이브러리 관리
  + 관련된 라이브러리가 많아질수록 파일을 다운로드 하여 lib폴더에 복사하여 사용하는 방식응 상당히 불편해진다.
  + Maven을 사용하면 설정 파일에 몇 줄 적어줌으로써 직접 다운로드 받거나 하는 것을 하지 않아도 라이브러리르 사용할 수 있다.
+ Maven을 사용하게 되면 Maven에 설정한 대로 모든 개발자가 일관된 방식으로 빌드를 수행할 수 있게 된다.
+ Maven은 또한 다양한 플로그인을 제공해줘서, 굉장히 많은 일들을 자동화시킬 수 있다.



### Maven 기본

+ Archetype을 이용하여 Maven 기반 프로젝트를 생성할 경우 생성된 프로젝트 하위에 pom.xml 파일이 생성된다.

+ Pom : Project Object Model

+ Pom.xml 파일을 살펴보면 다음과 같다.

  ~~~xml
  <project xmlns="http://maven.apache.org/POM/4.0.0"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <groupId>kr.or.connect</groupId>
      <artifactId>examples</artifactId>
      <packaging>jar</packaging>
      <version>1.0-SNAPSHOT</version>
      <name>mysample</name>
      <url>http://maven.apache.org</url>
      <dependencies>
          <dependency>
              <groupId>junit</groupId>
              <artifactId>junit</artifactId>
              <version>3.8.1</version>
              <scope>test</scope>
          </dependency>
      </dependencies>
  </project>
  ~~~

  + project : pom.xml 파일의 최상위 루트 엘리먼트 이다.
  + modelVersion : pom model의 버전이다.
  + groupId : 프로젝트를 생성하는 조직의 고유 아이디를 결정한다. 일반적으로 도메인 이름을 거꾸로 적는다.
  + artifactId : 해당 프로젝트에 의하여 생성되는 artifact의 고유 아이디를 결정한다.
    + Maven을 이용하여 pom.xml을 빌드할 경우 다음과 같은 규칙으로 artifact가 생성된다.
    + artifactId-version.packaging
    + 위 예의 경우 빌드할 경우 examples-1.0-SNAPSHOT.jar 파일이 생성된다.
    + **Examples**(artifactId)-**1.0-SNAPSHOT**(version).**jar**(packaging)
  + Packaging : 해당 프로젝트를 어떤 형태로 packaging 할 것인지 결정한다. Jar,war,ear등이 해당된다.
  + version : 프로젝트의 현재 버전. 프로젝트가 개발 중일 때는 SNAPSHOT을 접미사로 사용한다.  Maven의 버전 관리 기능은 라이브러리 관리를 편하게 한다.
  + Name: 프로젝트의 이름이다.
  + Url : 프로젝트 사이트가 있다면 사이트 URL을 등록하는 것이 가능하다.

+ Maven을 이용할 경우 얻게 되는 큰 이점 중 하나는 Dependency Management 기능이다.

+ 위 pom.xml 파일에서 \<dependencies/> 엘리먼트가 Dependency Management 기능의 핵싱이라고 할 수 있다.

  + 해당 엘리먼트 안에 필요한 라이브러리를 지정하게 된다.



## Maven을 이용한 웹 어플리케이션 작성하기



<img width="522" alt="maven01" src="https://user-images.githubusercontent.com/88477839/158337768-2959631b-a776-4b0d-ac04-9ab42d767821.png">

+ 이클립스를 실행하고, 이클립스의 메뉴 중 File - New - Project를 선택합니다.
+ 프로젝트 위자드(Wizard)가 뜨면, Maven아래의 Maven Project를 선택한 후 Next버튼을 클릭합니다.

<img width="612" alt="maven02" src="https://user-images.githubusercontent.com/88477839/158337930-2da9296a-568a-47d9-8e0b-6506a3ccd457.png">

+ Maven프로젝트가 기존 워크스페이스 경로에 생성되도록 합니다.
+ Next버튼을 클릭합니다.

<img width="614" alt="maven03" src="https://user-images.githubusercontent.com/88477839/158338181-66555458-bd10-4041-aedc-555a243f0d79.png">

+ 아키타입(Archetype)을 선택합니다. 
  + Show the last version of Archetype only 체크를 해제 해주면 1.0 버전을 찾을 수 있음
+ 아키타입이란 일종의 프로젝트 템플릿(Template)이라고 말할 수 있습니다.

+ 어떤 아키타입을 선택했느냐에 따라서 자동으로, 여러 가지 파일들을 생성하거나 라이브러리를 셋팅해주거나 등의 일을 해줍니다.
+ Maven을 이용하여 웹 어플리케이션을 개발하기 위해서 maven-archetype-webapp를 선택한 후 Next 버튼을 클릭합니다.

<img width="613" alt="maven04" src="https://user-images.githubusercontent.com/88477839/158338885-84a30bb8-e2d4-459e-8261-27be7585b4de.png">

+ Group Id는 보통 프로젝트를 진행하는 회사나 팀의 도메인 이름을 거꾸로 적습니다.

+ Artifact Id는 해당 프로젝트의 이름을 적습니다.

+ 버전은 보통 기본값(0.0.1-SNAPSHOT)으로 설정합니다.

+ package이름은 group id와 Artifact Id가 조합된 이름이 됩니다.

+ Group Id를 kr.or.connect이고 Artifact Id가 mavenweb으로 설정했기 때문에 package이름은 kr.or.connect.mavenweb이 됩니다.

+ finish버튼을 클릭합니다.

  

<img width="388" alt="maven05" src="https://user-images.githubusercontent.com/88477839/158345455-01049900-720d-4d59-ba84-5851c7aa4138.png">

+ 프로젝트가 생성된 프로젝트의 디렉토리 구조입니다.
+ 디렉토리의 구조를 보기 위해서 이클립스의 Navigator view를 통해서 확인하였습니다.
+ Maven으로 생성된 프로젝트의 경우 자바 소스는 src/main/java 폴더에 생성됩니다.
+ 웹 어플리케이션과 관련된 html, css등은 src/main/webapp 폴더에서 작성해야 합니다.
+ 그런데, 생성된 프로젝트를 보면 src/main/java 폴더가 보이지 않습니다.
+ 필요한 폴더는 별도로 만들어줄 필요가 있습니다.

![_](https://user-images.githubusercontent.com/88477839/158346648-acb25567-6783-4517-a259-4c72bebba496.png)

<img width="481" alt="maven06" src="https://user-images.githubusercontent.com/88477839/158346801-4e417ee2-f236-4d60-b05c-a66466fbe31d.png">

<img width="662" alt="maven07" src="https://user-images.githubusercontent.com/88477839/158346986-a8c82878-50be-4959-9292-fade4aae89ad.png">

+ 프로젝트를 선택하고, 우측버튼을 눌러 properties를 선택합니다.
+ 그리고, Java Compiler메뉴를 선택합니다.
+ 선택을 해보면 기본적으로 JDK 1.5 버전을 사용하는 것을 알 수 있습니다.
+ Maven으로 프로젝트를 생성하면 기본적으로 JDK 1.5를 사용하게 됩니다.
+ JDK8을 사용하도록 하려면 Maven설정 파일인 pom.xml파일을 수정해야 합니다.
+ pom.xml파일을 더블클릭하면 다음과 같이 보입니다.

<img width="868" alt="maven08" src="https://user-images.githubusercontent.com/88477839/158347343-4fda4f09-1aee-40c5-9a08-542b5d2822a7.png">

+ 아래쪽의 pom.xml 탭을 선택하면 소스가 보입니다

~~~xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>kr.or.connect</groupId>
  <artifactId>mavenweb</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>mavenweb Maven Webapp</name>
  <url>http://maven.apache.org</url>
  
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <build>
    <finalName>mavenweb</finalName>
  </build>
</project>
~~~

+ 자동으로 juint 3.8.1 라이브러리를 추가하고 있습니다.
+ junit은 테스트를 위한 라이브러리입니다.
+ 위의 내용에 다음의 코드를 입력합니다.

~~~xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>kr.or.connect</groupId>
  <artifactId>mavenweb</artifactId>
  <packaging>war</packaging>
  <version>0.0.1-SNAPSHOT</version>
  <name>mavenweb Maven Webapp</name>
  <url>http://maven.apache.org</url>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
  <build>
    <finalName>mavenweb</finalName>
        <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.6.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
  </build>
</project>
~~~

+ 코드를 입력하였으면, 저장합니다.
+ 수정 후 다시 프로젝트 프로퍼티의 자바 컴파일러 항목을 보면 여전히 1.5 입니다.

<img width="796" alt="maven09" src="https://user-images.githubusercontent.com/88477839/158348955-314f5bcd-7bbf-44b1-87e6-4d13a7d1f3ae.png">

+ 프로젝트 프로퍼티를 선택한 후 Maven메뉴 아래의 Java EE Integration을 선택합니다.
+ 보이는 것처럼 Enable Project Specific Settings 앞의 체크박스를 선택합니다.
+ 그리고 아래의 Apply and Close버튼을 클릭합니다.
+ 그리고, 다시 프로퍼티의 자바 컴파일러 버전을 확인하도록 하겠습니다.
+ JDK 1.8이 사용되는 것을 알 수 있습니다.
+ Maven의 설정을 바꾸면, 이클립스 프로젝트 설정이 연동되게 된 것입니다.
+ 이번엔 webapp폴더 아래의 index.jsp를 열어보도록 하겠습니다.

<img width="529" alt="maven10" src="https://user-images.githubusercontent.com/88477839/158349400-5af0913c-1877-4efe-aa65-87432f61c574.png">

+ HttpServlet을 찾을 수 없다는 오류 메시지가 보입니다.
+ 앞에서 Dynamic Web Application을 만들 때는 WAS Runtime설정을 하면서 Tomcat을 지정했었습니다.
+ WAS Runtime이 지정되면 Tomcat에 있는 라이브러리를 이클립스에서 사용할 수 있게 됩니다.
+ Tomcat안에 있는 서블릿 라이브러리가 사용되면서 문제가 없게 됩니다.
+ 실행시에도 WAS 위에서 실행되기 때문에 WAS의 서블릿 라이브러리를 사용하게 됩니다.
+ Maven프로젝트로 생성했을 경우에는 WAS 런타임이 지정을 안 했기 때문에 서블릿 라이브러리를 찾을 수 없습니다.
+ dependencies 엘리먼트 아래에 다음을 추가합니다.

~~~xml
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>javax.servlet-api</artifactId>
  <version>3.1.0</version>
  <scope>provided</scope>
</dependency>
~~~

+ 위의 내용을 보면 scope에 provided라는 항목이 있는데 servlet라이브러리를 컴파일 시에만 사용되고 배포 시에는 사용되지 않는다는 것을 의미합니다.

  scope는 다음과 같은 4가지가 있습니다.

  - **compile** : 컴파일 할 때 필요. 테스트 및 런타임에도 클래스 패스에 포함됩니다. scope 을 설정하지 않는 경우 기본값입니다.
  - **runtime** : 런타임에 필요. JDBC 드라이버 등이 예가 됩니다. 컴파일 시에는 필요하지 않지만, 실행 시에 필요한 경우입니다.
  - **provided** : 컴파일 시에 필요하지만, 실제 런타임 때에는 컨테이너 같은 것에서 제공되는 모듈. servlet, jsp api 등이 이에 해당. 배포 시 제외됩니다. 
  - **test** : 테스트 코드를 컴파일 할 때 필요. 테스트 시 클래스 패스에 포함되며, 배포 시 제외됩니다.

+ 위의 내용을 추가하고 index.html을 가보면 오류가 발생하지 않는 것을 알 수 있습니다.

<img width="815" alt="maven11" src="https://user-images.githubusercontent.com/88477839/158351157-5883edb9-14d1-4a87-b0e3-cc0eb965f1cd.png">

+ 해당 프로젝트를 실행해 보도록 하겠습니다.
+ 프로젝트를 선택한 후 우측버튼을 클릭하여 Run on Server를 선택합니다.
+ 해당 웹 어플리케이션을 실행할 Runtime을 지정하고, 항상 해당 런타임을 사용하겠다는 아래쪽 체크박스도 선택한 후 Finish버튼을 클릭합니다.

<img width="540" alt="maven12" src="https://user-images.githubusercontent.com/88477839/158351467-610dc8a3-9c24-4ecf-9f18-050d185fe646.png">

+ 브라우저가 실행되면서 index.html이 보여지는 것을 확인할 수 있습니다.
+ 이번엔 pom.xml 파일에 JSTL라이브러리를 추가하도록 하겠습니다.

~~~xml
<dependency>
  <groupId>javax.servlet</groupId>
  <artifactId>jstl</artifactId>
  <version>1.2</version>
</dependency>
~~~

+ JSTL은 Tomcat이 기본으로 제공하지 않기 때문에, 컴파일할 때도 배포할 때도 사용돼야 합니다.
+ 그래서 scope에 이번엔 provided가 있지 않습니다.
+ webapp폴더에 앞에서 작성했던 jstl02.jsp 를 붙여넣기를 하도록 하겠습니다.
+ 라이브러리가 변경되었으니, 다시 run on server를 합니다.
+ 실행해도 결과가 아무것도 나오지 않는 것을 확인할 수 있습니다.
+ 프로젝트 프로퍼티를 선택한 후, Project facets 항목을 보면 다이나믹 웹 모듈의 버전이 2.3입니다.
+ 다이나믹 웹 모듈의 2.4부터 EL이 기본으로 사용할 수 있도록 설정되기 때문에 2.3일 경우에는 EL표기법의 결과가 출력되지 않습니다.
+ 앞의 프로젝트처럼 다이나믹 웹 모듈 3.1이 되도록 설정해보도록 하겠습니다.
+ 먼저 WEB-INF의 web.xml 파일을 열어보도록 하겠습니다.

~~~xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
</web-app>
~~~

+ 위의 내용을 다음과 같이 수정합니다.

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1">
  <display-name>Archetype Created Web Application</display-name>
</web-app>
~~~

+ 프로젝트아래의 .settings/org.eclipse.wst.common.project.facet.core.xml 파일을 엽니다.
+ 이클립스 navigator에서 보면 보입니다.

![스크린샷 2022-03-15 19.06.24](/Users/helloacompany.asia/Desktop/스크린샷 2022-03-15 19.06.24.png)

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<faceted-project>
  <fixed facet="wst.jsdt.web"/>
  <installed facet="jst.web" version="2.3"/>
  <installed facet="wst.jsdt.web" version="1.0"/>
  <installed facet="java" version="1.8"/>
</faceted-project>
~~~

+ 을 아래와 같이 수정합니다.

~~~xml
<?xml version="1.0" encoding="UTF-8"?>
<faceted-project>
  <fixed facet="wst.jsdt.web"/>
  <installed facet="jst.web" version="3.1"/>
  <installed facet="wst.jsdt.web" version="1.0"/>
  <installed facet="java" version="1.8"/>
</faceted-project>
~~~

+ 프로젝트 프로퍼티의 Project facets항목을 보면 다이나믹 웹 모듈이 3.1로 바뀐 것을 볼 수 있습니다.

+ 이제 jstl02.jsp를 run on server로 실행합니다.

+ 실행을 하지만 오류가 나면서 실행이 안 되는 경우가 있을 수 있습니다.

+ 이클립스의 버그로, 수정되기 전의 데이터와 수정된 데이터가 섞여서 실행되기 때문입니다.

+ 이 경우 웹 어플리케이션을 깔끔히 초기화하고 실행하는 것이 좋을 수 있습니다.

  1. 기존 tomcat을 종료합니다.
  2. 혹시 바뀌지 않았다면 프로젝트를 선택하고, 우측버튼을 눌러서 Maven 메뉴 아래의 update project를 선택한 후 확인하세요.
  3. Servers view에서 기존 Tomcat Runtime을 삭제
  4. Project 메뉴의 Clean선택
  5. 프로젝트 익스플로러에서 Server 삭제

+ 위와 같은 과정을 거친 후 Run on Server로 실행해보세요.

+ 결과가 잘 나오는 것을 확인할 수 있습니다.

+ 지금까지 배웠던 내용 중에서 가장 복잡한 것 같은데요.

  다이나믹 웹 모듈을 2.3에서 3.1로 바꾸는 것은 프로젝트가 한번 만들어지면, 그 이후부터는 그 프로젝트가 더 이상 사용되지 않을 때까지 계속 사용되기 때문에 자주 개발자가 해야 할 일은 아닙니다.

  그 이후부터는 pom.xml에 원하는 기능을 추가하면서 개발을 진행하면 됩니다.



