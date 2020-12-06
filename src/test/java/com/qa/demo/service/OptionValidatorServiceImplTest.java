package com.qa.demo.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OptionValidatorServiceImplTest{

	public OptionValidatorServiceImpl optionValidatorService;
	
	private String[] validOptions = {"A", "B", "C"};
	
	private String[] inValidOptions = {"D", "1" , "="};
	
	
	@BeforeEach
	public void setup(){
		optionValidatorService = new OptionValidatorServiceImpl();
	}
	
	@Test
	public void testValidInputOption() {
		for(String option : validOptions) {
			assertEquals(true, optionValidatorService.isValidInputOption(option));
		}
	}
	
	@Test
	public void testInvalidInputOption() {
		for(String option : inValidOptions) {
			assertEquals(false, optionValidatorService.isValidInputOption(option));
		}	
	}

}
