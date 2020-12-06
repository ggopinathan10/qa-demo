package com.qa.demo.service;

public class InputValidatorService {

	private int MAX_INPUT_CHARS_SIZE = 255;
	
	public boolean isNotEmpty(String input) {
		return (null != input && !input.isEmpty());
	}
	
	public boolean isValidMaxCharsSize(String input) {
		return (input.length() <= MAX_INPUT_CHARS_SIZE);
	}
	
	public void setMaxCharSize(int size) {
		this.MAX_INPUT_CHARS_SIZE = size;
	}
	
}
