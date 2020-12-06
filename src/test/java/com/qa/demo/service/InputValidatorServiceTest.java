package com.qa.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputValidatorServiceTest {
	
	public InputValidatorService inpuService;
		
	private String VALID_INPUT = "test";
	
	private String INVALID_INPUT;
	
	@BeforeEach
	public void setup() {
		inpuService = new InputValidatorService();
	}
	
	public String generateString() {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<=256;i++) {
			sb.append("a");
		}
		return sb.toString();
	}

	@Test
	public void testEmptyInput() {
		assertFalse(inpuService.isNotEmpty(null));
		assertFalse(inpuService.isNotEmpty(""));
	}
	
	@Test
	public void testNotEmptyInput() {
		assertTrue(inpuService.isNotEmpty(VALID_INPUT));
	}
	
	@Test
	public void testValidCharSize() {
		assertTrue(inpuService.isValidMaxCharsSize(VALID_INPUT));
	}
	
	@Test
	public void testInvalidCharSize() {
		INVALID_INPUT = generateString();
		assertFalse(inpuService.isValidMaxCharsSize(INVALID_INPUT));	
	}
	
}
