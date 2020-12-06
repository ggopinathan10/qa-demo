package com.qa.demo.service;

public abstract class QAValidatorService extends InputValidatorService{

	abstract boolean isValidQuestion(String question);
	
	abstract boolean isValidAnswer(String answer);
}
