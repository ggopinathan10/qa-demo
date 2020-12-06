package com.qa.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.qa.demo.dao.QuestionDao;
import com.qa.demo.entity.AnswerEntity;
import com.qa.demo.entity.QuestionEntity;

@ExtendWith(MockitoExtension.class)
public class QuestionEntityServiceImplTest{
	
	@InjectMocks
	public QuestionEntityServiceImpl questionEntityServiceImpl;
	
	@Mock
	private QuestionDao questionDao;
	
	@Mock
	private QAValidatorServiceImpl qaValidatorService;
	
	private String QUESTION = "What is Peters favorite food?";
	
	private String QUESTION_ANSWER = "What is Peters favorite food?<Pizza><Spaghetti><Ice cream>";

	
	private QuestionEntity buildQuestionEntity() {
		Set<AnswerEntity> answers = new HashSet<>();
		answers.add(new AnswerEntity("hello"));
		QuestionEntity questionEntity = new QuestionEntity(1, QUESTION, answers);
		return questionEntity;
	}
		
	
	@Test
	public void testGetAnswersSuccess() {
		QuestionEntity questionEntity = buildQuestionEntity();
		when(questionDao.findByQuestion(QUESTION)).thenReturn(Optional.ofNullable(questionEntity));
		QuestionEntity qe = questionEntityServiceImpl.getQuestionWithAnswers(QUESTION);
		assertNotNull(qe.getAnswers());
		assertTrue(!qe.getAnswers().isEmpty());
	}
	
	@Test
	public void testGetAnswersFailure(){
		when(questionDao.findByQuestion(QUESTION)).thenReturn(Optional.ofNullable(null));
		QuestionEntity qe = questionEntityServiceImpl.getQuestionWithAnswers(QUESTION);
		assertNull(qe);
	}

	
	@Test
	public void testAddNewQuestionWithAnswer(){
//		QuestionEntity questionEntity = buildQuestionEntity();
//		when(qaValidatorService.isValidQuestion(QUESTION_ANSWER)).thenReturn(true);
//		when(qaValidatorService.isValidAnswer(QUESTION_ANSWER)).thenReturn(true);
//		when(questionDao.findByQuestion(QUESTION)).thenReturn(Optional.ofNullable(null));
////		when(questionDao.save(questionEntity)).thenReturn(new QuestionEntity());
//		assertTrue(questionEntityServiceImpl.addNewQuestionWithAnswer(QUESTION_ANSWER));
		
	}
	
	@Test
	public void testAddNewQuestionWithAnswerFaliure(){
		when(qaValidatorService.isValidQuestion(QUESTION_ANSWER)).thenReturn(true);
		when(qaValidatorService.isValidAnswer(QUESTION_ANSWER)).thenReturn(true);
		when(questionDao.findByQuestion(QUESTION)).thenReturn(Optional.ofNullable(null));
		assertFalse(questionEntityServiceImpl.addNewQuestionWithAnswer(QUESTION_ANSWER));
	}
}
