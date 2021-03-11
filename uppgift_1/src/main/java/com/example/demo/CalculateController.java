package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculateController {

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String helloWorld() {
		return "<h1>Hello</h1>";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/math")
	public String calculate(String equation) {
		CalcBean bean = new CalcBean();
		bean.setEquation(equation);
		return "<h1>" + bean.calc() + "</h1>";
	}

}
