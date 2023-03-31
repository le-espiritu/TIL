# 01-2 패키지 매니저 homebrew



### Homebrew

+ homebrew는 어떤 프로그램을 설치해주는 일종의 인스톨러 같은 역할을 해주는 프로그램이다.



### 사용법

+ 터미널에 먼저 homebrew를 설치한다. 

  + homebrew 홈페이지에 들어가서 설치하는 코드를 복사하여 터미널에 입력한다.

+ brew help - homebrew 사용법에대해 알려준다.

+ brew search - homebrew 저장소에 해당 프로그램이 있는지 없는지 찾아주는 명령어

  ~~~shell
  brew search 프로그램명
  brew search htop
  ~~~

+ brew install - 프로그램 설치 명령어

  ~~~
  brew install 프로그램명
  brew install htop
  ~~~

+ brew list - homebrew를 통해 설치한 프로그램들의 리스트들을 보여줌

  ~~~
  brew list
  ~~~

+ brew uninstall - 프로그램 삭제

  ~~~
  brew uninstall 프로그램명
  brew uninstall htop
  ~~~

+ brew upgrade - 프로그램 버전을 업그레이드 해준다.

  ~~~
  brew upgrade  => 설치된 모든 프로그램들을 업그레이드 해준다. 
  brew upgrade 프로그램명  => 특정 프로그램만 업그레이드 해준다. 
  brew upgrade htop
  brew upgrade vim
  ~~~

+ brew update - homebrew로 설치할 수 있는 프로그램 목록들을 최신화시켜준다.

  ~~~
  brew update
  ~~~

  