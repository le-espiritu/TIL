package com.study.mvcxml2.test;

import org.springframework.stereotype.Component;

@Component
public class TestComponent {
	
	public void run() {
		System.out.println("테스트 컴포넌트 run 메소드 실행");
	}
	
	public void run2() {
		System.out.println("테스트 컴포넌트 run2 메소드 실행");
	}
	
	public void run3() {
		System.out.println("테스트 컴포넌트 run3 메소드 실행 for @ Around!!!!!!!");
	}
	
}
