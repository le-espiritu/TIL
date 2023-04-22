package com.study.mvcxml2;

import org.junit.Test;

public class Test01 {
	
	@Test
	public void standalon() {
		System.out.println("junit test 실행");
		System.out.println("이 예제는 별다른 성공 실패 유무 확인 코드가 없기 때문에 실행만 되면 무조건 성공으로 분류된다. ");
	}
	
}

/*
 * src/test/java와 src/test/resources는 
 * 빌드시 제외되기 때문에 마음대로 테스트를 만들어 수행할 수 있다.*/

/*
 * 프로그래밍 언어마다 테스트를 위한 프레임워크가 존재한다.
 * 자바언어의 경우는 테스트를 위한 프레임워크가 JUnit이다.
 * 
 * 
 * @Test는 JUnit에서 제공하는 Annotation이며 
 * JUnit으로 실행 시 독립적으로 기능을 수행하는 테스트케이스가 된다. 
 * 메소드는 반드시 반환형이 void이며 매개변수가 없어야 한다. @Test는 여러 개 만들 수 있다.
 */

/*
 * 테스트 케이스는 성공과 실패로 결과가 나뉜다. 
 * 또한 소요 시간 등이 나오기 때문에 
 * 다양한 정보를 통해 단위 기능을 테스트할 수 있도록 지원한다.
 */