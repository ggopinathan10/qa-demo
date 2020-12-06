package com.qa.demo.service;

import java.util.Set;

import com.qa.demo.entity.AnswerEntity;
import com.qa.demo.entity.QuestionEntity;

public interface QuestionService {

	QuestionEntity getQuestionWithAnswers(String question);
	
	boolean addNewQuestionWithAnswer(String questionAndAnswer);
	
}
