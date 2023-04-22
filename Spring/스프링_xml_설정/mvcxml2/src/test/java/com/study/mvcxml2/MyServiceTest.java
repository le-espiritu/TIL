package com.study.mvcxml2;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.study.mvcxml2.test.CalculatorService;
import com.study.mvcxml2.test.MyService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class) //Junit 확장 클래스 MockitoJunitRunner를 이용해 테스트 클래스를 실행
public class MyServiceTest {

	@InjectMocks //목 객체를 사용하는 MyService 객체를 생성하여 초기화하라는 의미
	MyService myService;
	
	@Mock // calculatorService가 목 객체를 참조하도록 함 
	CalculatorService calculatorService;
	
	@Test
	public void excute()throws Exception{
		int value1 =5;
		int value2 = 1;
		given(calculatorService.plus(value1, value2)).willReturn(15);
		
		int result = myService.execute(value1, value2);
		
		verify(calculatorService).plus(anyInt(), anyInt());
		Assert.assertEquals(30, result);
	}
	
	
}

/* 
 * 빈과 빈 사이에는 다양한 관계가 있다.
 * Mockito를 이용하면 그 다양한 관계를 목(Mock) 객체를 이용하여 끊고,
 * 테스트 하고자 하는 객체에만 집중하여 단위 테스트(unit test)를 할 수 있다.
 * 
 *  예를 들어 MyService에 대해서 테스트하고자 했는데
 *  MyService가 CalculatorService를 주입받아서 
 *  CalculatorService의 메소드를 사용하는 경우
 * 	CalculatorService의 코드에서 오류가 난다면 
 * 	MyService에서 오류가 난것인지, CalculatorService에서 오류가 난것인지
 * 	명확히 분간하기가 힘들다.
 *	이럴때 Mockito를 활용한다. 
 *
 *	Mockito를 활용하면 내가 원하는 동작을 하는 가짜 객체(Mock 객체)를 생성하여 사용할 수 있다.
 *	MyService가 사용하는 CalculatorService를 사용하는 대신
 *	Mock객체의 CalculatorService를 사용함으로써
 *	MyService의 내용만 테스트를 해볼 수 있다. 
 *
 *	Mockito를 사용하기 위해서는 Mockito 의존성 라이브러리를 추가한다. */

/* 위 코드에서
 * calculatorService는 가짜 객체입니다. 
 * 이 가짜 객체가 동작하는 방법을 규정할 수 있는 것이 given()메소드입니다.
 * calculatorService.plus(value1,value2)을 호출하면 plus메소드가 15를 반환하도록 하라는 의미를 가집니다. 
 * 
 * verify메소드는 파라미터로 들어온 객체의 plus메소드가 호출된 적이 있는지 검증합니다.
 * 좀 더 자세히 살펴보자면 plus(anyInt(), anyInt())는 어떤 정수든지 2개를 파라미터로 넣어서
 * plus()메소드를 호출했는지를 검증합니다. 
 * myService.execute()메소드 내부적으로 plus 메소드를 호출했다면 해당 메소드는 검증을 성공한 것입니다.
 * 만약, execute()메소드에서 plus(anyInt(), anyInt())를 호출하지 않았다면 오류가 발생하게 됩니다.
 */


