package com.study.mvcxml2.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
	private final CalculatorService calculatorService;
	
	@Autowired
	public MyService(CalculatorService calculatorService) {
		this.calculatorService=calculatorService;
	}
	
	public int execute(int value1, int value2) {
		int value=0;
		value = calculatorService.plus(value1, value2)*2;
		//value=value1+value2;
		return value;
	}

}
