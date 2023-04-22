package com.study.mvcxml2;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.study.mvcxml2.test.CalculatorService;

public class Test03_2 {
	
	CalculatorService calculatorService;
	
	@Before
	public void init() {
		this.calculatorService=new CalculatorService();
	}
	
	@Test
	public void plus() throws Exception {
		
		//given
		int a = 5;
		int b = 10;
		
		//when
		int result = calculatorService.plus(a, b);
		
		//then
		Assert.assertEquals(15, result); // result(실제값)와 15(기대값)가 같을 경우에만 성공
	}
	
	
	@Test
	public void minus() {
		
		//given
		int value1 = 10;
		int value2 = 5;
		
		//when
		int result = calculatorService.minus(value1, value2);
		
		//then
		Assert.assertEquals(5, result);
	}
	
	
	@Test
	public void multiple() {
		
		//given
		int value1 = 5;
		int value2 = 10;
		
		//when
		int result = calculatorService.multiple(value1, value2);
		
		//then;
		Assert.assertEquals(50, result);
	}

}

/*
 * 해당 예제는 일반 자바 객체를 테스트하는 예제이다.
 * 테스트를 진행하기전 @Before을 통해 테스트하고자 하는 객체를 new 연산자로 초기화 하고 (여기서는 CalculatorService가 컴포넌트(빈)이 아니라는 가정하에)
 *  @Test에서 객체의 메소드들을 테스트한다.
 *  
 *  만약 일반 자바 객체가 아니라 스프링 빈을 테스트 하고자 한다면 어떻게 해야할까?
 *  이에 대한 예제는 Test05.java 예제를 참고하면 된다.  
 */

/* assert메소드라고 불리는 메소드(메소드 이름이 assert로 시작합니다.)를 이용해 값을 체크할 수 있다. */

/*
 * @Test는 JUnit에서 제공하는 Annotation이며 
 * JUnit으로 실행 시 독립적으로 기능을 수행하는 테스트케이스가 된다. 
 * 메소드는 반드시 반환형이 void이며 매개변수가 없어야 한다. @Test는 여러 개 만들 수 있다.
 */