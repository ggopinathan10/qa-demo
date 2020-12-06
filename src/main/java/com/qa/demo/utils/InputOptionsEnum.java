package com.qa.demo.utils;

public enum InputOptionsEnum {

	A("A"),B("B"),C("C");
	
	private String option;
	
	InputOptionsEnum(String option){
		this.option = option;
	}

	public String getOption() {
		return option;
	}
	
	
}
