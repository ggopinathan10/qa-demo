package com.qa.demo.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("qaValidatorService")
public class QAValidatorServiceImpl extends QAValidatorService{

	@Override
	public boolean isValidQuestion(String question) {
		return question.contains("?");
	}

	@Override
	public boolean isValidAnswer(String answer) {
		return answer.contains("<") && answer.contains(">");
	}
	
}
