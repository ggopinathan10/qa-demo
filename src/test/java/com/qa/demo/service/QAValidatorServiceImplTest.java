package com.qa.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QAValidatorServiceImplTest{

	public QAValidatorServiceImpl qaValidatorServiceImpl;
	
	private String INVALID_QUESTION = "what";
	
	private String VALID_QUESTION = "what?";
	
	private String INVALID_ANSWER = "hello";
	
	private String VALID_ANSWER = "<hello><hola><hi>";
	
	
	@BeforeEach
	public void setup() {
		qaValidatorServiceImpl = new QAValidatorServiceImpl();
	}
	
	@Test
	public void testValidQuestion() {
		assertNotNull(qaValidatorServiceImpl);
		assertTrue(qaValidatorServiceImpl.isValidQuestion(VALID_QUESTION));
	}
	
	@Test
	public void testInvalidQuestion() {
		assertEquals(false, qaValidatorServiceImpl.isValidQuestion(INVALID_QUESTION));
	}
	
	@Test
	public void testValidAnswer() {
		assertEquals(true, qaValidatorServiceImpl.isValidAnswer(VALID_ANSWER));
	}

	@Test
	public void testInvalidAnswer() {
		assertEquals(false, qaValidatorServiceImpl.isValidAnswer(INVALID_ANSWER));
	}
	
}
