package com.study.mvcxml2;

import org.junit.Assert;
import org.junit.Test;

public class Test03 {
	
	@Test
	public void calculate() {
		
		int a = 10;
		int b = 20;
		
		int c = 29;
		
		Assert.assertEquals(a+b, c);
	}

}

/* assert메소드라고 불리는 메소드(메소드 이름이 assert로 시작합니다.)를 이용해 값을 체크할 수 있다. */

/* 이 예제에서 기대되는 값은 a+b, 즉 30인데,
 * 실제 값은 c, 즉 29 이기 때문에 이 테스트는 실패로 나타난다.
 */
